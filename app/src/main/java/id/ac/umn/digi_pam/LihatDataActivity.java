package id.ac.umn.digi_pam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class LihatDataActivity extends AppCompatActivity {

    private TextView tvNamaHariLihatData;
    private TextView tvDateMonthYearLihatData;
    private EditText etInputBulanLihatData;
    private EditText etInputTahunLihatData;
    private Button btnSubmitLihatData;

    private String inputBulan;
    private String inputTahun;
    private int inputBulanInt;
    private int inputTahunInt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_data);

        etInputBulanLihatData = findViewById(R.id.etInputBulanLihatData);
        etInputTahunLihatData = findViewById(R.id.etInputTahunLihatData);

        // Setting display hari dan tanggal:
        tvNamaHariLihatData = findViewById(R.id.tvNamaHariLihatData);
        tvDateMonthYearLihatData = findViewById(R.id.tvDateMonthYearLihatData);

        Calendar cal = Calendar.getInstance();
        String hari = CustomDateConvertor.dayOfWeekConvertor(
                cal.get(Calendar.DAY_OF_WEEK));
        String bulan = CustomDateConvertor.monthConvertor(
                cal.get(Calendar.MONTH));
        int tanggal = cal.get(Calendar.DAY_OF_MONTH);
        int tahun = cal.get(Calendar.YEAR);

        tvNamaHariLihatData.setText(hari + ",");
        tvDateMonthYearLihatData.setText(tanggal + " " + bulan + " " + tahun);

        // Setting button:
        btnSubmitLihatData = findViewById(R.id.btnSubmitLihatData);
        btnSubmitLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputBulan = etInputBulanLihatData.getText().toString().trim();
                inputTahun = etInputTahunLihatData.getText().toString().trim();

                // Cek apakah input kosong:
                if (inputBulan.isEmpty()) {
                    Toast.makeText(LihatDataActivity.this
                        , "Input bulan harus diisi!"
                        , Toast.LENGTH_LONG).show();
                    return;
                }

                if (inputTahun.isEmpty()) {
                    Toast.makeText(LihatDataActivity.this
                        , "Input tahun harus diisi!"
                        , Toast.LENGTH_LONG).show();
                    return;
                }

                // Cek apakah input berupa angka
                try {
                    inputBulanInt = Integer.parseInt(inputBulan);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(LihatDataActivity.this
                        , "Input Bulan harus berupa bilangan bulat!"
                        , Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    inputTahunInt = Integer.parseInt(inputTahun);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(LihatDataActivity.this
                        , "Input Tahun harus berupa bilangan bulat!"
                        , Toast.LENGTH_LONG).show();
                    return;
                }

                // Cek apakah input bulan di dalam rentang 1 - 12:
                if (inputBulanInt < 1 || inputBulanInt > 12) {
                    Toast.makeText(LihatDataActivity.this
                        , "Input bulan harus di antara 1 - 12!"
                        , Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(LihatDataActivity.this
                        , DaftarDataPamActivity.class);

                intent.putExtra("inputBulan", inputBulanInt);
                intent.putExtra("inputTahun", inputTahunInt);

                startActivity(intent);
            }
        });
    }
}
