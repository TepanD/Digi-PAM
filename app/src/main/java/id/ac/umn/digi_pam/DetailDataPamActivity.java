package id.ac.umn.digi_pam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailDataPamActivity extends AppCompatActivity {
    TextView tvNoPam;
    TextView tvJumlahPemakaian;
    TextView tvAlamat;
    TextView tvTanggalInput;
    TextView tvEmailPenginput;
    ImageView ivDetailDataPamImg;
    Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_pam);
        getSupportActionBar().hide();

        tvNoPam = findViewById(R.id.tvNoPam);
        tvJumlahPemakaian = findViewById(R.id.tvJumlahPemakaian);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvTanggalInput = findViewById(R.id.tvTanggalInput);
        tvEmailPenginput = findViewById(R.id.tvEmailPenginput);
        ivDetailDataPamImg = findViewById(R.id.ivDetailDataPamImg);
        btnKembali = findViewById(R.id.btnKembali);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ListPam lp = (ListPam) bundle.getSerializable("dataPam");

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference dataPamImgRef =storageRef.child(lp.getPhotoPath());

        final long ONE_MEGABYTE = 1024 * 1024;
        dataPamImgRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                String noPamText = tvNoPam.getText().toString().trim()
                    + " " + lp.getNomorPam();
                tvNoPam.setText(noPamText);

                String jumlahPemakaianText = tvJumlahPemakaian.getText()
                    .toString().trim() + " " + lp.getJumlahPemakaian();
                tvJumlahPemakaian.setText(jumlahPemakaianText);

                String alamatText = tvAlamat.getText().toString().trim()
                    + " " + lp.getAlamat();
                tvAlamat.setText(alamatText);

                String tanggalInputText = tvTanggalInput.getText().toString()
                    .trim() + " " + lp.getTanggal() + " "
                    + CustomDateConvertor.monthConvertor(lp.getBulan() - 1)
                    + " " + lp.getTahun();
                tvTanggalInput.setText(tanggalInputText);

                String emailPenginputText = tvEmailPenginput.getText()
                    .toString().trim() + " " + lp.getEmailPenginput();
                tvEmailPenginput.setText(emailPenginputText);

                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0
                    , bytes.length);
                ivDetailDataPamImg.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DetailDataPamActivity.this
                    , "Download foto gagal!"
                    , Toast.LENGTH_LONG).show();
            }
        });
    }
}