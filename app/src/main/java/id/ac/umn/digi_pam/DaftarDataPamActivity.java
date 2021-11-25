package id.ac.umn.digi_pam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DaftarDataPamActivity extends AppCompatActivity {

    private TextView tvBulan;
    private TextView tvTahun;
    private TextView tvDataPamTidakDitemukan;
    RecyclerView rvDaftarData;
    DaftarDataPamAdapter adapter;
    ArrayList<ListPam> listDataPam = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_data_pam);

        Intent intent = getIntent();
        int bulan = intent.getIntExtra("inputBulan", 0);
        int tahun = intent.getIntExtra("inputTahun", 0);

        tvBulan = findViewById(R.id.tvBulan);
        tvTahun = findViewById(R.id.tvTahun);
        tvDataPamTidakDitemukan = findViewById(R.id.tvDataPamTidakDitemukan);
        tvDataPamTidakDitemukan.setVisibility(View.GONE);

        tvBulan.setText(CustomDateConvertor.monthConvertor(bulan - 1));
        tvTahun.setText(String.valueOf(tahun));

        // Ambil data - data dari firestore:
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                            listDataPam.add(listPam);
                        }

                        if (!listDataPam.isEmpty()) {
                            rvDaftarData = findViewById(R.id.rvDaftarData);
                            adapter = new DaftarDataPamAdapter(
                                DaftarDataPamActivity.this, listDataPam);

                            rvDaftarData.setAdapter(adapter);
                            rvDaftarData.setLayoutManager(
                                new LinearLayoutManager
                                        (DaftarDataPamActivity.this));
                        }
                        else {
                            tvDataPamTidakDitemukan.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        Toast.makeText(DaftarDataPamActivity.this
                            , "Terjadi kesalahan! Silahkan reset aplikasi."
                            , Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}