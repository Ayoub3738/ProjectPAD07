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

    public static final String TABLE_NAME = "user_table";
    public static final String USER_COL_1 = "userID";
    public static final String USER_COL_2 = "streak";
    public static final String USER_COL_3 = "nietGerookteSigaretten";
    public static final String USER_COL_4 = "aantalMeldingen";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Wordt uitgevoerd als de database voor de eerste keer wordt aangemaakt
     * @param db lege database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //maakt tabel user_table aan
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                USER_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                USER_COL_2 + " INTEGER, " +
                USER_COL_3 + " INTEGER, " +
                USER_COL_4 + " INTEGER" +
                ")");

        //vult database met standaardwaardes
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_2, 0);
        contentValues.put(USER_COL_3, 0);
        contentValues.put(USER_COL_4, 0);
        db.insert(TABLE_NAME, null, contentValues);
    }

    /**
     * Wordt uitgevoerd als er geupdate moet worden
     * @param db de database
     * @param oldVersion oude versie
     * @param newVersion nieuwe versie
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Selecteert alles uit user_table
     * @return alles uit de tabel user_table
     */
    public Cursor selectAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    //update query test <<<TEST!!!
    public void updateStreak(int streak) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + USER_COL_2 + " = " + streak + " WHERE " + USER_COL_1 + " = 1");
    }
}
