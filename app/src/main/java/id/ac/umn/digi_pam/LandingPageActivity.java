package id.ac.umn.digi_pam;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class LandingPageActivity extends AppCompatActivity {
    private TextView tvNamaHari;
    private TextView tvDateMonthYear;

    private ImageButton btnMenu;
    private ImageButton btnMore;
    private ImageButton btnAdd;
    private ImageButton btnHome;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        getSupportActionBar().hide();

        // Setting display hari dan tanggal:
        tvNamaHari = findViewById(R.id.tvNamaHari);
        tvDateMonthYear = findViewById(R.id.tvDateMonthYear);

        Calendar cal = Calendar.getInstance();
        String hari = CustomDateConvertor.dayOfWeekConvertor(
                cal.get(Calendar.DAY_OF_WEEK));
        String bulan = CustomDateConvertor.monthConvertor(
                cal.get(Calendar.MONTH));
        int tanggal = cal.get(Calendar.DAY_OF_MONTH);
        int tahun = cal.get(Calendar.YEAR);

        tvNamaHari.setText(hari + ",");
        tvDateMonthYear.setText(tanggal + " " + bulan + " " + tahun);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToLandingPage = new Intent(LandingPageActivity.this, LandingPageActivity.class);
                startActivity(GoToLandingPage);
            }
        });
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToPendataan = new Intent(LandingPageActivity.this, FormPendataanActivity.class);
                startActivity(GoToPendataan);
            }
        });
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToDaftarData = new Intent(LandingPageActivity.this, LihatDataActivity.class);
                startActivity(GoToDaftarData);
            }
        });

        btnAdd.setImageAlpha(220);
    }
}
