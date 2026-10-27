package com.example.litera;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnOk, btnCancel;
    private TextView textDaftar, textLupaPassword;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        dbHelper = new DatabaseHelper(this);

        // Seed default demo user if table is empty
        if (dbHelper.getAllUsers().isEmpty()) {
            dbHelper.insertUser(new User("Admin Litera", "admin", "admin123"));
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnOk = findViewById(R.id.dashboard);
        btnCancel = findViewById(R.id.btnCancel);
        textDaftar = findViewById(R.id.daftar);
        textLupaPassword = findViewById(R.id.lupaPassword);

        // Login Action
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

        // Cancel Action (Clear fields)
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etUsername.setText("");
                etPassword.setText("");
            }
        });

        // Register Action
        textDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Forgot Password Action
        textLupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LupaPasswordActivity.class);
                intent.putExtra("message", "Atur Ulang Kata Sandi");
                startActivity(intent);
            }
        });
    }

    private void performLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Username / Email tidak boleh kosong");
            etUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password tidak boleh kosong");
            etPassword.requestFocus();
            return;
        }

        boolean isValid = dbHelper.checkUser(username, password);

        if (isValid) {
            User user = dbHelper.getUserByLogin(username, password);
            String displayName = (user != null && user.getName() != null) ? user.getName() : username;

            Toast.makeText(this, "Login Berhasil! Selamat datang, " + displayName, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            intent.putExtra("USER_NAME", displayName);
            intent.putExtra("USER_EMAIL", user != null ? user.getEmail() : username);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Username/Email atau Password salah!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
