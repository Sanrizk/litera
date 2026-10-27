package com.example.litera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserManager.db";
    private static final int DATABASE_VERSION = 3;

    // USERS TABLE
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void ensurePasswordColumnExists(SQLiteDatabase db) {
        try {
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COLUMN_PASSWORD + " TEXT DEFAULT ''");
        } catch (Exception ignored) {
            // Column already exists
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ensurePasswordColumnExists(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ensurePasswordColumnExists(db);
    }

    // ==================== USER OPERATIONS ====================

    // CREATE
    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ensurePasswordColumnExists(db);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        return db.insert(TABLE_USERS, null, values);
    }

    // READ ALL
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        ensurePasswordColumnExists(db);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        if (cursor.moveToFirst()) {
            int idIdx = cursor.getColumnIndex(COLUMN_ID);
            int nameIdx = cursor.getColumnIndex(COLUMN_NAME);
            int emailIdx = cursor.getColumnIndex(COLUMN_EMAIL);
            int passIdx = cursor.getColumnIndex(COLUMN_PASSWORD);

            do {
                int id = idIdx != -1 ? cursor.getInt(idIdx) : 0;
                String name = nameIdx != -1 ? cursor.getString(nameIdx) : "";
                String email = emailIdx != -1 ? cursor.getString(emailIdx) : "";
                String password = passIdx != -1 ? cursor.getString(passIdx) : "";

                list.add(new User(id, name, email, password));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // CHECK USER LOGIN
    public boolean checkUser(String emailOrName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ensurePasswordColumnExists(db);
        String selection = "(" + COLUMN_EMAIL + " = ? OR " + COLUMN_NAME + " = ?) AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {emailOrName, emailOrName, password};

        try {
            Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
            int count = cursor.getCount();
            cursor.close();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // CHECK IF USER EXISTS
    public boolean checkUserExists(String emailOrName) {
        SQLiteDatabase db = this.getReadableDatabase();
        ensurePasswordColumnExists(db);
        String selection = COLUMN_EMAIL + " = ? OR " + COLUMN_NAME + " = ?";
        String[] selectionArgs = {emailOrName, emailOrName};

        try {
            Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
            int count = cursor.getCount();
            cursor.close();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // GET USER BY LOGIN
    public User getUserByLogin(String emailOrName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ensurePasswordColumnExists(db);
        String selection = "(" + COLUMN_EMAIL + " = ? OR " + COLUMN_NAME + " = ?) AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {emailOrName, emailOrName, password};

        User user = null;
        try {
            Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                int idIdx = cursor.getColumnIndex(COLUMN_ID);
                int nameIdx = cursor.getColumnIndex(COLUMN_NAME);
                int emailIdx = cursor.getColumnIndex(COLUMN_EMAIL);
                int passIdx = cursor.getColumnIndex(COLUMN_PASSWORD);

                int id = idIdx != -1 ? cursor.getInt(idIdx) : 0;
                String name = nameIdx != -1 ? cursor.getString(nameIdx) : "";
                String email = emailIdx != -1 ? cursor.getString(emailIdx) : "";
                String pwd = passIdx != -1 ? cursor.getString(passIdx) : "";

                user = new User(id, name, email, pwd);
            }
            cursor.close();
        } catch (Exception ignored) {
        }
        return user;
    }

    // UPDATE
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ensurePasswordColumnExists(db);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        return db.update(TABLE_USERS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    // DELETE
    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
