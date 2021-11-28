package id.ac.umn.digi_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailDataPamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_pam);
        getSupportActionBar().hide();
    }
}