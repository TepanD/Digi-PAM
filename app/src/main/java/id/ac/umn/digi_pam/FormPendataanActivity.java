package id.ac.umn.digi_pam;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FormPendataanActivity extends AppCompatActivity {

    private ImageButton imgBtnGetPhoto;
    private Bitmap photoImage;
    private int photoImageByteCount;
    private String currentPhotoPath;

    EditText etInputNomorPam;
    EditText etInputJumlahPemakaian;
    EditText etInputAlamat;
    Button btnTambah;
    Button btnGetFromGallery;

    private String noPam;
    private String jumlahPemakaian;
    private String alamat;
    private int tanggal;
    private int bulan;
    private int tahun;

    private String documentId;

    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private FirebaseUser user;

    private ArrayList<ListPam> dataPamAlreadyInDb;
    private boolean isAbleToUploadData;

    private AppCompatButton btnHome;
    private AppCompatButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_pendataan);
        getSupportActionBar().hide();

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToDaftarData = new Intent(FormPendataanActivity.this, LihatDataActivity.class);
                startActivity(GoToDaftarData);
                finish();
            }
        });

        Calendar cal = Calendar.getInstance();
        tanggal = cal.get(Calendar.DAY_OF_MONTH);
        bulan = cal.get(Calendar.MONTH) + 1;
        tahun = cal.get(Calendar.YEAR);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();

        dataPamAlreadyInDb = new ArrayList<>();
        db.collection("dataPam")
            .whereEqualTo("bulan", bulan)
            .whereEqualTo("tahun", tahun)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document: task.getResult()) {
                            ListPam listPam = document.toObject(ListPam.class);
                            dataPamAlreadyInDb.add(listPam);
                        }
//                        Log.d("DEBUG", "Jumlah data di " +
//                                "dataPamAlreadyInDb = " + dataPamAlreadyInDb.size());
                        isAbleToUploadData = true;
                    }
                    else {
                        isAbleToUploadData = false;
                        Toast.makeText(FormPendataanActivity.this
                            , "Terjadi kesalahan! Silahkan reset" +
                                "aplikasi."
                            , Toast.LENGTH_LONG).show();
                    }
                }
            });

//        Log.d("DEBUG", "tanggal = " + tanggal);
//        Log.d("DEBUG", "bulan = " + bulan);
//        Log.d("DEBUG", "tahun = " + tahun);

        photoImage = null;

        TextView tvTanggalSaatIni = findViewById(R.id.tvTanggalSaatIni);
        tvTanggalSaatIni.setText(tanggal + "/" + bulan + "/" + tahun);

        etInputNomorPam = findViewById(R.id.etInputNomorPam);
        etInputJumlahPemakaian = findViewById(R.id.etInputJumlahPemakaian);
        etInputAlamat = findViewById(R.id.etInputAlamat);
        imgBtnGetPhoto = findViewById(R.id.imgBtnGetPhoto);
        btnGetFromGallery = findViewById(R.id.btnGetFromGallery);
        btnTambah = findViewById(R.id.btnTambah);

        // Pengganti StartActivityForResult:
        ActivityResultLauncher<Intent> photoCaptureLauncher
        = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent resultIntent = result.getData();

//                        if (resultIntent == null) {
//                            Toast.makeText(
//                                FormPendataanActivity.this
//                                , "Image dari kamera tidak ditemukan!"
//                                , Toast.LENGTH_LONG).show();
//
//                            return;
//                        }

                        try {
                            File tempPhotoFile = new File(currentPhotoPath);
                            Uri selectedImageUri = Uri.fromFile(tempPhotoFile);

                            if (Build.VERSION.SDK_INT < 28) {
                                photoImage = MediaStore.Images.Media
                                    .getBitmap(
                                            FormPendataanActivity.this
                                                    .getContentResolver()
                                            , selectedImageUri
                                    );
                            } else {
                                ImageDecoder.Source source
                                    = ImageDecoder.createSource(
                                    FormPendataanActivity.this
                                            .getContentResolver()
                                    , selectedImageUri);

                                photoImage = ImageDecoder
                                    .decodeBitmap(source);
                            }

                            if (photoImage != null) {
                                photoImageByteCount
                                    = photoImage.getByteCount();

                                // Cek apakah ukuran gambar terlalu besar:
                                if (photoImageByteCount > 1024000000) {
                                    Toast.makeText(
                                        FormPendataanActivity.this
                                        , "Ukuran maksimal gambar = 1024 MB!"
                                        , Toast.LENGTH_LONG).show();

                                    photoImage = null;
                                    return;
                                }

                                imgBtnGetPhoto.setImageBitmap(photoImage);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        );

        imgBtnGetPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent
                    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Toast.makeText(
                        FormPendataanActivity.this
                        , "Error dalam setup camera temporary path!"
                        , Toast.LENGTH_LONG).show();
                }

                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(
                        FormPendataanActivity.this
                        , "id.ac.umn.digi_pam.android" +
                        ".fileprovider"
                        , photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT
                        , photoURI);

                    photoCaptureLauncher.launch(takePictureIntent);
                }
            }
        });

        ActivityResultLauncher<Intent> getFromGalleryLauncher
                = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent resultIntent = result.getData();

                            if (resultIntent == null) {
                                Toast.makeText(
                                    FormPendataanActivity.this
                                    , "Image di gallery tidak ditemukan!"
                                    , Toast.LENGTH_LONG).show();

                                return;
                            }

                            try {
                                Uri selectedImageUri = resultIntent.getData();

                                if (Build.VERSION.SDK_INT < 28) {
                                    photoImage = MediaStore.Images.Media
                                        .getBitmap(
                                            FormPendataanActivity.this
                                                .getContentResolver()
                                            , selectedImageUri
                                        );
                                } else {
                                    ImageDecoder.Source source
                                        = ImageDecoder.createSource(
                                        FormPendataanActivity.this
                                                .getContentResolver()
                                        , selectedImageUri);

                                    photoImage = ImageDecoder
                                        .decodeBitmap(source);
                                }

                                if (photoImage != null) {
                                    photoImageByteCount
                                        = photoImage.getByteCount();

                                    // Cek apakah ukuran gambar terlalu besar:
                                    if (photoImageByteCount > 1024000000) {
                                        Toast.makeText(
                                            FormPendataanActivity.this
                                            , "Ukuran maksimal gambar = 1024 MB!"
                                            , Toast.LENGTH_LONG).show();

                                        photoImage = null;
                                        return;
                                    }

                                    imgBtnGetPhoto.setImageBitmap(photoImage);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        btnGetFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getFromGallery = new Intent();
                getFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                getFromGallery.setType("image/*");

                getFromGalleryLauncher.launch(getFromGallery);
            }
        });

        btnTambah.setOnClickListener(view -> {
            noPam = etInputNomorPam.getText().toString().trim();
            jumlahPemakaian = etInputJumlahPemakaian.getText().toString()
                .trim();
            alamat = etInputAlamat.getText().toString().trim();

            Log.d("Input Form", "noPam: " + noPam);
            Log.d("Input Form", "jumlahPemakaian: "
                + jumlahPemakaian);
            Log.d("Input Form", "alamat: " + alamat);
            Log.d("Input Form", "tanggal: " + tanggal);

            if (validateInput() && isAbleToUploadData) {
                documentId = CustomDataPamIdGenerator
                    .generateDataPamId(tahun, bulan, noPam);

                ListPam pam = new ListPam(noPam, jumlahPemakaian, alamat
                    , tanggal, bulan, tahun, user.getEmail()
                    , "photos/" + documentId + ".jpg");

                db.collection("dataPam")
                    .document(documentId)
                    .set(pam)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(FormPendataanActivity.this
                                    , "Add Pendataan Sukses!"
                                    , Toast.LENGTH_LONG).show();
                            dataPamAlreadyInDb.add(pam);
                            uploadPamPhotoImage();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Form Pendataan Error"
                                , "Error adding document", e);

                            Toast.makeText(FormPendataanActivity.this
                                , "Add Pendataan Gagal!"
                                , Toast.LENGTH_LONG).show();
                        }
                    });
            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
            .format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
          imageFileName,            /* Prefix */
          ".jpg",              /* Suffix */
          storageDir                /* Directory */
        );

        // Save a file: Path for use with ACTION_VIEW
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private boolean validateInput()
    {
        // Cek apakah input kosong:
        if (noPam.isEmpty()) {
            Toast.makeText(FormPendataanActivity.this
                , "Input Nomor PAM harus diisi!"
                , Toast.LENGTH_LONG).show();
            return false;
        }
        else if (jumlahPemakaian.isEmpty()) {
            Toast.makeText(FormPendataanActivity.this
                , "Input Jumlah Pemakaian harus diisi!"
                , Toast.LENGTH_LONG).show();
            return false;
        }
        else if (alamat.isEmpty()) {
            Toast.makeText(FormPendataanActivity.this
                , "Input Alamat harus diisi!"
                , Toast.LENGTH_LONG).show();
            return false;
        }
        else if (photoImage == null) {
            Toast.makeText(FormPendataanActivity.this
                , "Input Foto harus diisi!"
                , Toast.LENGTH_LONG).show();
            return false;
        }

        // Cek apakah format input benar:
        try {
            Integer.parseInt(noPam);
        }
        catch (NumberFormatException e) {
            Toast.makeText(FormPendataanActivity.this
                    , "Input Nomor PAM harus berupa bilangan bulat!"
                    , Toast.LENGTH_LONG).show();
            return false;
        }

        try {
            Integer.parseInt(jumlahPemakaian);
        }
        catch (NumberFormatException e) {
            Toast.makeText(FormPendataanActivity.this
                    , "Input Jumlah Pemakaian harus berupa bilangan " +
                        "bulat!"
                    , Toast.LENGTH_LONG).show();
            return false;
        }

        // Cek apakah ukuran gambar terlalu besar:
        if (photoImageByteCount > 1024000000) {
            Toast.makeText(FormPendataanActivity.this
                    , "Ukuran maksimal gambar = 1024 MB!"
                    , Toast.LENGTH_LONG).show();
            return false;
        }

        // Cek apakah data pada bulan dan tahun sekarang ini sudah ada
        // sebelumnya:
        for (ListPam listPam: dataPamAlreadyInDb) {
            if (listPam.getBulan() == bulan
                && listPam.getTahun() == tahun
                && listPam.getNomorPam().equals(noPam)) {

                Toast.makeText(FormPendataanActivity.this
                        , "Data PAM sudah ada di database!"
                        , Toast.LENGTH_LONG).show();
                return false;
            }
        }

        return true;
    }

    private void uploadPamPhotoImage()
    {
        StorageReference photoRef = storageRef.child("photos/" + documentId
        + ".jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photoImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = photoRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FormPendataanActivity.this
                    , "Upload foto gagal!"
                    , Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(
                new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(FormPendataanActivity.this
                    , "Upload foto berhasil!"
                    , Toast.LENGTH_LONG).show();

                resetInput();
            }
        });
    }

    private void resetInput()
    {
        etInputNomorPam.setText("");
        etInputJumlahPemakaian.setText("");
        etInputAlamat.setText("");
        photoImage = null;
        photoImageByteCount = 0;

        Drawable drawable = getDrawable(getResources()
            .getIdentifier("android:drawable/ic_menu_camera"
                , null, null));
        imgBtnGetPhoto.setImageDrawable(drawable);
    }
}
