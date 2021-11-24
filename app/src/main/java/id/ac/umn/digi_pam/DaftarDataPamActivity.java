package id.ac.umn.digi_pam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DaftarDataPamActivity extends AppCompatActivity {

    private TextView tvBulan;
    private TextView tvTahun;
    RecyclerView rvDaftarData;
    DaftarDataPamAdapter adapter;
    ArrayList<ListPam> listDataPam = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_data_pam);

        Intent intent = getIntent();
        String bulan = intent.getStringExtra("bulanFilter");
        String tahun = intent.getStringExtra("tahunFilter");

        tvBulan = findViewById(R.id.tvBulan);
        tvTahun = findViewById(R.id.tvTahun);

        tvBulan.setText(bulan);
        tvTahun.setText(tahun);


    }
}