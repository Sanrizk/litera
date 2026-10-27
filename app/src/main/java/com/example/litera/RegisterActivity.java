package com.example.litera;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etRegName, etRegEmail, etRegPassword, etRegConfirmPassword;
    private Button btnRegister;
    private TextView tvBackToLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        etRegName = findViewById(R.id.etRegName);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPassword);
        etRegConfirmPassword = findViewById(R.id.etRegConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        tvBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registerUser() {
        String name = etRegName.getText().toString().trim();
        String email = etRegEmail.getText().toString().trim();
        String password = etRegPassword.getText().toString().trim();
        String confirmPassword = etRegConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            etRegName.setError("Nama tidak boleh kosong");
            etRegName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("Email/Username tidak boleh kosong");
            etRegEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password tidak boleh kosong");
            etRegPassword.requestFocus();
            return;
        }

        if (password.length() < 4) {
            etRegPassword.setError("Password minimal 4 karakter");
            etRegPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            etRegConfirmPassword.setError("Konfirmasi password tidak cocok");
            etRegConfirmPassword.requestFocus();
            return;
        }

        if (dbHelper.checkUserExists(email)) {
            Toast.makeText(this, "Email/Username sudah terdaftar! Silakan gunakan yang lain.", Toast.LENGTH_LONG).show();
            return;
        }

        User newUser = new User(name, email, password);
        long result = dbHelper.insertUser(newUser);

        if (result > 0) {
            Toast.makeText(this, "Pendaftaran berhasil! Silakan login.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Gagal mendaftar. Silakan coba lagi.", Toast.LENGTH_SHORT).show();
        }
    }
}
