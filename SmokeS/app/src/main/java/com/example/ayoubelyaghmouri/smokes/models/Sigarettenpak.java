package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Created by jerry on 2-5-2017.
 */

public class Sigarettenpak {
    private int pakID;
    private double prijs;
    private String merk;
    private int aantalSigaretten;

    public Sigarettenpak(double prijs, String merk, int aantalSigaretten) {
        this.pakID = 1;
        this.prijs = prijs;
        this.merk = merk;
        this.aantalSigaretten = aantalSigaretten;
    }

    public Sigarettenpak(int pakID, double prijs, String merk, int aantalSigaretten) {
        this.pakID = pakID;
        this.prijs = prijs;
        this.merk = merk;
        this.aantalSigaretten = aantalSigaretten;
    }

    public static Sigarettenpak getPak(DatabaseHelper db) {

        SQLiteDatabase myDb = db.getDB();
        String query = "SELECT * " +
                "FROM " + db.PAK_TABLE_NAME + " " +
                "WHERE " + db.PAK_PAK_ID + " = 1;";

        Sigarettenpak pak = null;
        Cursor res = myDb.rawQuery(query, null);

        if(res.moveToFirst()) {
            res.moveToFirst();

            pak = new Sigarettenpak(
                    res.getInt(res.getColumnIndex(db.PAK_PAK_ID)),
                    res.getDouble(res.getColumnIndex(db.PAK_PRIJS)),
                    res.getString(res.getColumnIndex(db.PAK_MERK)),
                    res.getInt(res.getColumnIndex(db.PAK_AANTAL_SIGARETTEN))
            );

            res.close();
        }

        return pak;
    }

    public void insert(DatabaseHelper db) {
        SQLiteDatabase myDb = db.getDB();
        insert(myDb);
    }

    public void insert(SQLiteDatabase db) {
        ContentValues cvPak = new ContentValues();
        cvPak.put(DatabaseHelper.PAK_PRIJS, prijs);
        cvPak.put(DatabaseHelper.PAK_MERK, merk);
        cvPak.put(DatabaseHelper.PAK_AANTAL_SIGARETTEN, aantalSigaretten);

        db.insert(DatabaseHelper.PAK_TABLE_NAME, null, cvPak);
    }

    public static void update(double prijs, int aantalSigaretten, String merk, DatabaseHelper db) {
        SQLiteDatabase dbLite = db.getDB();
        ContentValues cv = new ContentValues();

        cv.put(db.PAK_PRIJS, prijs);
        cv.put(db.PAK_AANTAL_SIGARETTEN, aantalSigaretten);
        cv.put(db.PAK_MERK, merk);

        dbLite.update(db.PAK_TABLE_NAME, cv, db.PAK_PAK_ID + " = 1", null);
    }

    public double berekenPrijsSigaret() {
        return prijs / aantalSigaretten;
    }

    public int getPakID() {
        return pakID;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getMerk() {
        return merk;
    }

    public int getAantalSigaretten() {
        return aantalSigaretten;
    }



}
