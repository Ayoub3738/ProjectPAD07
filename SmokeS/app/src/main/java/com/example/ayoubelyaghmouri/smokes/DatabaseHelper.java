package com.example.ayoubelyaghmouri.smokes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jerry on 25-4-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "smokes.db";
    public static final int DB_VERSION = 3;

    public static final String PAK_TABLE_NAME = "sigarettenpak_table";
    public static final String PAK_PAK_ID = "pakID";
    public static final String PAK_PRIJS = "prijs";
    public static final String PAK_MERK = "merk";
    public static final String PAK_AANTAL_SIGARETTEN = "aantalSigaretten";

    public static final String USER_TABLE_NAME = "user_table";
    public static final String USER_USER_ID = "userID";
    public static final String USER_NAAM = "naam_user";
    public static final String USER_PAK_ID = "pakID";
    public static final String USER_STREAK = "streak";
    public static final String USER_NIET_GEROOKTE_SIGARETTEN = "nietGerookteSigaretten";
    public static final String USER_AANTAL_MELDINGEN = "aantalMeldingen";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    /**
     * Wordt uitgevoerd als de database voor de eerste keer wordt aangemaakt
     * @param db lege database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //maakt tabel sigarettenpak_table aan
        db.execSQL("CREATE TABLE " + PAK_TABLE_NAME + " (" +
                PAK_PAK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                PAK_PRIJS + " REAL, " +
                PAK_MERK + " TEXT, " +
                PAK_AANTAL_SIGARETTEN + " INTEGER" +
                ");");

        //maakt tabel user_table aan
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (" +
                USER_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                USER_PAK_ID + " INTEGER, " +
                USER_NAAM + " TEXT, " +
                USER_STREAK + " INTEGER, " +
                USER_NIET_GEROOKTE_SIGARETTEN + " INTEGER, " +
                USER_AANTAL_MELDINGEN + " INTEGER, " +
                " FOREIGN KEY (" + USER_PAK_ID + ") REFERENCES " + PAK_TABLE_NAME + " (" + PAK_PAK_ID + ")" +
                ");");

        //vult sigarettenpak_table met standaardwaardes
        ContentValues cvPak = new ContentValues();
        cvPak.put(PAK_PRIJS, 5.5);
        cvPak.put(PAK_MERK, "MARLBORO");
        cvPak.put(PAK_AANTAL_SIGARETTEN, 21);

        db.insert(PAK_TABLE_NAME, null, cvPak);

        //vult user_table met standaardwaardes
        ContentValues cvUser = new ContentValues();
        cvUser.put(USER_PAK_ID, 1);
        cvUser.put(USER_NAAM, "Gebruiker");
        cvUser.put(USER_STREAK, 0);
        cvUser.put(USER_NIET_GEROOKTE_SIGARETTEN, 0);
        cvUser.put(USER_AANTAL_MELDINGEN, 0);

        db.insert(USER_TABLE_NAME, null, cvUser);
    }

    /**
     * Wordt uitgevoerd als er een andere versie (DB_VERSION) in de code staat dan dat er op het apparaat runt
     * @param db de database
     * @param oldVersion oude versie
     * @param newVersion nieuwe versie
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PAK_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Selecteert alles uit user_table
     * @return alles uit de tabel user_table
     */
    public Cursor selectAll(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tableName, null);
        return res;
    }



    //update query test <<<TEST!!!
    public void updateStreak(int streak) {
        SQLiteDatabase db = this.getWritableDatabase();

        //updaten kan op 2 manieren

        //manier 1 - deze is het best en het netst
        ContentValues cv = new ContentValues();
        cv.put(USER_STREAK, streak);

        db.update(USER_TABLE_NAME, cv, PAK_PAK_ID + " = 1", null);

        //manier 2 - deze is wat slordiger, maar lijkt wel meer op normaal SQL
        //db.execSQL("UPDATE " + USER_TABLE_NAME + " SET " + USER_STREAK + " = " + streak + " WHERE " + USER_USER_ID + " = 1");
    }
}
