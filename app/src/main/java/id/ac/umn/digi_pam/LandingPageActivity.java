package id.ac.umn.digi_pam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {
    private ImageButton btnMenu;
    private ImageButton btnMore;
    private ImageButton btnAdd;
    private ImageButton btnHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        getSupportActionBar().hide();
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
                Intent GoToPendataan = new Intent(LandingPageActivity.this, LihatDataActivity.class);
                startActivity(GoToPendataan);
            }
        });
    }
}
