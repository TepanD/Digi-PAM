package id.ac.umn.digi_pam;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FormPendataanActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private ImageButton imgBtnGetPhoto;
    private Bitmap photoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_pendataan);

        EditText edit_noPam = findViewById(R.id.Input_NoPam);
        EditText edit_Besaran = findViewById(R.id.Input_BesarPengunaan);
        EditText edit_Alamat = findViewById(R.id.Input_Alamat);
        EditText edit_Tanggal = findViewById(R.id.Input_Tanggal);
        Button btn = findViewById(R.id.btnTambah);

        imgBtnGetPhoto = findViewById(R.id.imgBtnGetPhoto);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Pengganti StartActivityForResult:
        ActivityResultLauncher<Intent> photoCaptureLauncher
        = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent resultIntent = result.getData();

                            if (resultIntent != null) {
                                Bundle extras = resultIntent.getExtras();
                                photoImage = (Bitmap) extras.get("data");
                                imgBtnGetPhoto.setImageBitmap(photoImage);
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

                photoCaptureLauncher.launch(takePictureIntent);
            }
        });

        btn.setOnClickListener(view -> {

            String noPam = edit_noPam.getText().toString();
            String jumlahPemakaian = edit_Besaran.getText().toString();
            String alamat = edit_Alamat.getText().toString();
            String tanggal = edit_Tanggal.getText().toString();
            ListPam pam = new ListPam(noPam, jumlahPemakaian, alamat, tanggal);

            Log.d("Input Form: ", "noPam: " + noPam);
            Log.d("Input Form: ", "jumlahPemakaian: " + jumlahPemakaian);
            Log.d("Input Form: ", "alamat: " + alamat);
            Log.d("Input Form: ", "tanggal: " + tanggal);

//            db.collection("dataPam")
//                .add(pam)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("Form Pendataan: ", "DocumentSnapshot " +
//                            "written with ID: " + documentReference.getId());
//                        Toast.makeText(FormPendataanActivity.this
//                            , "Add Pendataan Sukses!"
//                            , Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("Form Pendataan Error: "
//                            , "Error adding document", e);
//                        Toast.makeText(FormPendataanActivity.this
//                            , "Add Pendataan Gagal!"
//                            , Toast.LENGTH_LONG).show();
//                    }
//                });
        });
    }
}
