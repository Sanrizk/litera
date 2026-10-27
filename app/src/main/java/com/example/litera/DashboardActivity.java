package com.example.litera;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView boNaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String userName = getIntent().getStringExtra("USER_NAME");
        if (userName != null && !userName.isEmpty()) {
            Toast.makeText(this, "Selamat Datang, " + userName + "!", Toast.LENGTH_SHORT).show();
        }

        boNaView = findViewById(R.id.bottom_navigation);

        // Default fragment loaded: ProfileFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new ProfileFragment()).commit();
        boNaView.setSelectedItemId(R.id.profile);

        boNaView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.profile) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new ProfileFragment()).commit();
                return true;
            } else if (itemId == R.id.nav_dashboard) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new ProfileFragment()).commit();
                return true;
            }
            return false;
        });
    }
}
