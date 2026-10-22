package com.example.litera;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView boNaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        boNaView = findViewById(R.id.bottom_navigation);

//        getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new SatuFragment()).commit();

        boNaView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_linear) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new LinearFragment()).commit();
                return true;
            } else if (itemId == R.id.nav_relative) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new RelativeFragment()).commit();
                return true;
            } else if (itemId == R.id.nav_const) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new ConstraintFragment()).commit();
                return true;
            } else if (itemId == R.id.nav_scrollview) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new ScrollviewFragment()).commit();
                return true;
            } else if (itemId == R.id.nav_dashboard) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.frl_dashboard, new SatuFragment()).commit();
                return true;
            }
            return false;
        });
    }
}