package com.example.litera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LupaPasswordActivity extends AppCompatActivity {
    TextView textIntent;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);
        Toast.makeText(this, getIntent().getStringExtra("message"), Toast.LENGTH_SHORT).show();

        textIntent = findViewById(R.id.aturUlangPassword);
        textIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unpam.ac.id"));
                startActivity(intent);
            }
        });
    }
}