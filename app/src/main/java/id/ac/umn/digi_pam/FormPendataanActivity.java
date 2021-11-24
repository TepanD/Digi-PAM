package id.ac.umn.digi_pam;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormPendataanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_pendataan);
        EditText edit_noPam = findViewById(R.id.Input_NoPam);
        EditText edit_Besaran = findViewById(R.id.Input_BesarPengunaan);
        EditText edit_Alamat = findViewById(R.id.Input_Alamat);
        EditText edit_Tanggal = findViewById(R.id.Input_Tanggal);
        DAOListPam dao =new DAOListPam();
        Button btn = findViewById(R.id.btnTambah);

        btn.setOnClickListener(view -> {
            ListPam data = new ListPam(edit_noPam.getText().toString(),edit_Besaran.getText().toString(),edit_Alamat.getText().toString(),edit_Tanggal.getText().toString());
            dao.add(data).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Input telah berhasil!!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
}
