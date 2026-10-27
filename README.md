# 📚 Litera - Aplikasi Manajemen Profil & Membaca Buku

**Litera** adalah aplikasi Android berbasis **Java** dan **SQLite** yang dirancang untuk memberikan pengalaman intuitif bagi pengguna dalam mengelola akun, preferensi membaca, serta pengingat jadwal membaca buku.

---

## 📹 Video Demo Aplikasi

Saksikan demonstrasi penggunaan aplikasi **Litera** melalui tautan YouTube berikut:

[![Video Demo Litera](https://img.youtube.com/vi/A39Hu_FoTk8/maxresdefault.jpg)](https://youtu.be/A39Hu_FoTk8)

👉 **[Klik di sini untuk menonton Video Demo Litera di YouTube](https://youtu.be/A39Hu_FoTk8)**

---

## ✨ Fitur Utama

### 🔐 1. Autentikasi Pengguna (SQLite Database)
- **Halaman Login**: Autentikasi kredensial (Username/Email & Password) yang tervalidasi secara *real-time* ke database SQLite lokal.
- **Halaman Registrasi (Daftar Akun Baru)**: Pendaftaran user baru dengan verifikasi input (field tidak boleh kosong, kesesuaian password) dan pengecekan duplikasi akun.
- **Lupa Password**: Tautan bantuan untuk mengatur ulang kata sandi pengguna.

### 👤 2. Dashboard & Profil Pengguna
- **Navigasi Bawah (Bottom Navigation Bar)**: Perpindahan antar menu secara responsif menggunakan `BottomNavigationView` dan `Fragment`.
- **Kategori Bacaan Favorit**: Memilih kategori bacaan favorit seperti *Sci-Fi*, *Fiction*, *Science*, dan *History*.
- **Pengingat Waktu Membaca**: Fitur penentuan waktu pengingat (*TimePicker*) dan tanggal pengingat (*DatePicker*) untuk meningkatkan kebiasaan membaca harian.

---

## 🛠️ Teknologi & Library

- **Bahasa Pemrograman**: Java
- **Target SDK**: Android 14 (API Level 34 / 35)
- **UI & Layout**: Material Design 3, ConstraintLayout, LinearLayout, BottomNavigationView, Fragment
- **Database**: SQLite (`SQLiteOpenHelper`)
- **Build System**: Gradle 8.x
- **IDE**: Android Studio

---

## 📁 Struktur Proyek

```text
com.example.litera
 ├── MainActivity.java           // Halaman utama Login & Navigasi
 ├── RegisterActivity.java       // Halaman Pendaftaran Akun Baru
 ├── SplashActivity.java         // Halaman Splash Screen awal
 ├── DashboardActivity.java      // Halaman Dashboard & Bottom Navigation
 ├── LupaPasswordActivity.java   // Halaman Lupa Password
 ├── ProfileFragment.java        // Fragment Profil Pengguna & Preferensi Membaca
 ├── DatabaseHelper.java         // Pengelola Database SQLite (Table Users)
 └── User.java                   // Class Model Data User
```

---

## 🚀 Cara Menjalankan Aplikasi

1. **Clone repositori ini**:
   ```bash
   git clone https://github.com/username/Litera.git
   ```
2. **Buka proyek di Android Studio**:
   - Pilih `Open an Existing Project` lalu pilih folder proyek `Litera`.
3. **Sinkronisasi Gradle**:
   - Tunggu hingga Android Studio selesai mengunduh dependensi dan melakukan Gradle Sync.
4. **Jalankan Aplikasi**:
   - Hubungkan perangkat Android fisik atau jalankan Android Emulator.
   - Klik tombol **Run** (`Shift + F10`) di Android Studio.

---

## 📝 Kredensial Demo Default

Aplikasi secara otomatis menyediakan akun demo bawaan saat pertama kali dijalankan:
- **Username/Email**: `admin`
- **Password**: `admin123`

---

## 📄 Lisensi

Proyek ini dibuat untuk tujuan pembelajaran dan pengembangan aplikasi Android.
