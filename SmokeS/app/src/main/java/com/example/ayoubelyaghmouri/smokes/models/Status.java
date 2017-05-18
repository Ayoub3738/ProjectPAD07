package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.database.Cursor;
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

        SQLiteDatabase myDb = db.getDB();
        String query = "SELECT * " +
                "FROM " + DatabaseHelper.USER_TABLE_NAME + " " +
                "WHERE " + DatabaseHelper.USER_USER_ID + " = 1;";

        Status status = null;
        Cursor res = myDb.rawQuery(query, null);

        if(res.moveToFirst()) {
            res.moveToFirst();

            Sigarettenpak pak = Sigarettenpak.getPak(db);

            Gezondheid gezondheid = new Gezondheid(0);
            gezondheid.berekenTotaalGezondheid(
                    res.getInt(res.getColumnIndex(db.USER_NIET_GEROOKTE_SIGARETTEN)),
                    res.getInt(res.getColumnIndex(db.USER_AANTAL_MELDINGEN)),
                    db
            );

            status = new Status(
                    res.getInt(res.getColumnIndex(db.USER_USER_ID)),
                    pak, res.getInt(res.getColumnIndex(db.USER_CHARACTER_ID)),
                    gezondheid,
                    res.getInt(res.getColumnIndex(db.USER_NIET_GEROOKTE_SIGARETTEN)),
                    res.getInt(res.getColumnIndex(db.USER_AANTAL_MELDINGEN)),
                    new Date(res.getLong(res.getColumnIndex(db.USER_LAATST_GEROOKT))),
                    res.getInt(res.getColumnIndex(db.USER_RECORD_STREAK)),
                    res.getInt(res.getColumnIndex(db.USER_STREAK))
            );

            res.close();
        }

        return status;
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
        cvUser.put(DatabaseHelper.USER_LAATST_GEROOKT, laatstGerookt.getTime());

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

    public String berekenTijdNietGerookt() {
        //deze berekening komt van: http://stackoverflow.com/questions/21285161/android-difference-between-two-dates
        Date datumNu = new Date();

        //milliseconds
        long different = datumNu.getTime() - laatstGerookt.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;

        return String.format("%d:%02d:%02d", elapsedDays, elapsedHours, elapsedMinutes);
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

    public Date getLaatstGerookt() {
        return laatstGerookt;
    }
}
