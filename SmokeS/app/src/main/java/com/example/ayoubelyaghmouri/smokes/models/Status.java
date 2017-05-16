package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.util.Date;

/**
 * Created by jerry on 2-5-2017.
 */

public class Status {

    private int statusID;
    private Sigarettenpak pak;
    private int characterID;
    private Gezondheid gezondheid;
    private int nietGerookteSigaretten;
    private int aantalMeldingen;
    private Date laatstGerookt;
    private int recordStreak;
    private int streak;

    //is oud
    public Status(int nietGerookteSigaretten, int aantalMeldingen, int streak, Date laatstGerookt, Sigarettenpak pak) {
        this.nietGerookteSigaretten = nietGerookteSigaretten;
        this.aantalMeldingen = aantalMeldingen;
        this.streak = streak;
        this.laatstGerookt = laatstGerookt;
        this.pak = pak;
    }

    public Status(int statusID, Sigarettenpak pak, int characterID, Gezondheid gezondheid, int nietGerookteSigaretten, int aantalMeldingen, Date laatstGerookt, int recordStreak, int streak) {
        this.statusID = statusID;
        this.pak = pak;
        this.characterID = characterID;
        this.gezondheid = gezondheid;
        this.nietGerookteSigaretten = nietGerookteSigaretten;
        this.aantalMeldingen = aantalMeldingen;
        this.laatstGerookt = laatstGerookt;
        this.recordStreak = recordStreak;
        this.streak = streak;
    }

    public Status(Sigarettenpak pak, Gezondheid gezondheid, int nietGerookteSigaretten, int aantalMeldingen, Date laatstGerookt, int recordStreak, int streak) {
        this.statusID = 1;
        this.pak = pak;
        this.characterID = 1;
        this.gezondheid = gezondheid;
        this.nietGerookteSigaretten = nietGerookteSigaretten;
        this.aantalMeldingen = aantalMeldingen;
        this.laatstGerookt = laatstGerookt;
        this.recordStreak = recordStreak;
        this.streak = streak;
    }

    public static Status getStatus(DatabaseHelper db) {
        Gezondheid gezondheid = new Gezondheid(1); //hier word gezondheid ook meteen berekend

        return new Status(Sigarettenpak.getPak(db), gezondheid, 1, 1, null, 1, 1);
    }

    public void insert(DatabaseHelper db) {
        SQLiteDatabase myDb = db.getDB();
        insert(myDb);
    }

    public void insert(SQLiteDatabase db) {
        ContentValues cvUser = new ContentValues();
        cvUser.put(DatabaseHelper.USER_CHARACTER_ID, 1);
        cvUser.put(DatabaseHelper.USER_PAK_ID, pak.getPakID());
        cvUser.put(DatabaseHelper.USER_STREAK, streak);
        cvUser.put(DatabaseHelper.USER_NIET_GEROOKTE_SIGARETTEN, nietGerookteSigaretten);
        cvUser.put(DatabaseHelper.USER_AANTAL_MELDINGEN, aantalMeldingen);
        cvUser.put(DatabaseHelper.USER_RECORD_STREAK, recordStreak);
        cvUser.put(DatabaseHelper.USER_LAATST_GEROOKT, "");

        db.insert(DatabaseHelper.USER_TABLE_NAME, null, cvUser);
    }

    public void update(DatabaseHelper db) {

    }

    public static void update(Sigarettenpak pak, DatabaseHelper db) {

    }

    public static <G> void update(String kolomnaam, G gegeven, DatabaseHelper db) {
        SQLiteDatabase dbLite = db.getDB();
        ContentValues cv = new ContentValues();

        if (gegeven instanceof Integer) {
            int data = (Integer) gegeven;
            cv.put(kolomnaam, data);
        }
        else if (gegeven instanceof String) {
            String data = (String) gegeven;
            cv.put(kolomnaam, data);
        }

        dbLite.update(db.USER_TABLE_NAME, cv, db.USER_USER_ID + " = 1", null);
    }

    public double berekenBesparingenPak() {
        return berekenAantalPak() * pak.getPrijs();
    }

    public int berekenAantalPak() {
        return (int)Math.ceil((float)nietGerookteSigaretten / pak.getAantalSigaretten());
    }

    public double berekenBesparingenSigaret() {
        return pak.berekenPrijsSigaret() * nietGerookteSigaretten;
    }

    public Date berekenTijdNietGerookt() {
        return laatstGerookt; //date.now - laatstgerookt;
    }

    public int getNietGerookteSigaretten() {
        return nietGerookteSigaretten;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getAantalMeldingen() {
        return aantalMeldingen;
    }

    public void setNietGerookteSigaretten(int nietGerookteSigaretten) {
        this.nietGerookteSigaretten = nietGerookteSigaretten;
    }

    public void setAantalMeldingen(int aantalMeldingen) {
        this.aantalMeldingen = aantalMeldingen;
    }
}
