package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Dit is een sigarettenpak klasse
 * Created by jerry on 2-5-2017.
 */

public class Sigarettenpak {
    private int pakID;
    private double prijs;
    private String merk;
    private int aantalSigaretten;

    /**
     * constructor die gebruikt word voor insert
     * @param prijs
     * @param merk
     * @param aantalSigaretten
     */
    public Sigarettenpak(double prijs, String merk, int aantalSigaretten) {
        this.pakID = 1;
        this.prijs = prijs;
        this.merk = merk;
        this.aantalSigaretten = aantalSigaretten;
    }

    /**
     * constructor
     * @param pakID uniek id van een sigarettenpak
     * @param prijs een prijs van een pakje sigaretten
     * @param merk het merk van een pak
     * @param aantalSigaretten het aantal sigaretten hoeveel er in 1 pak zit
     */
    public Sigarettenpak(int pakID, double prijs, String merk, int aantalSigaretten) {
        this.pakID = pakID;
        this.prijs = prijs;
        this.merk = merk;
        this.aantalSigaretten = aantalSigaretten;
    }

    /**
     * haalt een sigarettenpak uit de database en maakt er een object van
     * @param db databasehelper die hij gebruikt om te praten met de database
     * @return een object Sigarettenpak die uit de db komt
     */
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

    /**
     * deze methode insert een pak in de database met behulp van een databasehelper
     * @param db databasehelper
     */
    public void insert(DatabaseHelper db) {
        SQLiteDatabase myDb = db.getDB();
        insert(myDb);
    }

    /**
     * insert een pak in de database
     * @param db de database zelf
     */
    public void insert(SQLiteDatabase db) {
        ContentValues cvPak = new ContentValues();
        cvPak.put(DatabaseHelper.PAK_PRIJS, prijs);
        cvPak.put(DatabaseHelper.PAK_MERK, merk);
        cvPak.put(DatabaseHelper.PAK_AANTAL_SIGARETTEN, aantalSigaretten);

        db.insert(DatabaseHelper.PAK_TABLE_NAME, null, cvPak);
    }

    /**
     * maakt aanpassingen voor een sigarettenpak aan in de database
     * @param prijs prijs van een pak
     * @param aantalSigaretten hoeveelheid sigaretten in 1 pak
     * @param merk merk van een pak
     * @param db database waar ie het heen schrijft
     */
    public static void update(double prijs, int aantalSigaretten, String merk, DatabaseHelper db) {
        SQLiteDatabase dbLite = db.getDB();
        ContentValues cv = new ContentValues();

        cv.put(db.PAK_PRIJS, prijs);
        cv.put(db.PAK_AANTAL_SIGARETTEN, aantalSigaretten);
        cv.put(db.PAK_MERK, merk);

        dbLite.update(db.PAK_TABLE_NAME, cv, db.PAK_PAK_ID + " = 1", null);
    }

    /**
     * berekent de prijs van 1 sigaret
     * @return prijs van 1 sigaret
     */
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
