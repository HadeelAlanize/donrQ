package com.example.MAproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.BoringLayout;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "DonorQuests.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT, name TEXT , " +
                "mobile TEXT, city TEXT , address TEXT, gender TEXT, bloodType TEXT, birthDate TEXT, " +
                "donationCount INT default 0)");

        MyDatabase.execSQL("create Table usersDonation(email TEXT, bloodTypePatient TEXT, hospitalName TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists users");
        MyDatabase.execSQL("drop Table if exists usersDonation");
    }

    public Boolean insertUserDonation(String email, String bloodTypePatient, String hospitalName) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("bloodTypePatient", bloodTypePatient);
        contentValues.put("hospitalName", hospitalName);
        long result = MyDatabase.insert("usersDonation", null, contentValues);
        return result != -1;
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", username);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        return result != -1;
    }

    public int updateDonationCount(String email) {
        int count = 0;
        // Read the donation count
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            count = cursor.getInt(9);
        }
        // Increment donation count
        count = count + 1;
        // store it again in database
        SQLiteDatabase writeDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("donationCount", count);
        writeDatabase.update("users", contentValues, "email = ?", new String[]{email});
        return count;
    }

    public int getAchievementsCount(String email) {
        int count = 0;
        // Read the donation count
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            count = cursor.getInt(9);
        }
        return count;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public Boolean updateDonor(String username,  String name  ,String mobile ,String city  ,String address ,String gender ,String bloodType ,String birthDate ) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobile", mobile);
        contentValues.put("city", city);
        contentValues.put("address", address);
        contentValues.put("gender", gender);
        contentValues.put("bloodType", bloodType);
        contentValues.put("birthDate", birthDate);
        long result = MyDatabase.update("users", contentValues, "email = ?", new String[]{username});
        return result != -1;
    }

    public Cursor GetDonor(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? ", new String[]{email});
        return cursor;
    }
}
