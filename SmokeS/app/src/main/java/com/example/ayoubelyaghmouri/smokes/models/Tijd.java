package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Dit is een klasse voor tijd, deze bestaat uit uren en minuten
 * Created by jerry on 16-5-2017.
 */

public class Tijd implements Comparable<Tijd> {

    private int tijdID;
    private int statusID;
    private int uur;
    private int minuten;

    /**
     * constructor
     * @param tijdID uniek id voor een tijd
     * @param statusID bij wie hoort deze tijd? (bij het persoon van wie deze status is)
     * @param uur de uren
     * @param minuten de minuten
     */
    public Tijd(int tijdID, int statusID, int uur, int minuten) {
        this.tijdID = tijdID;
        this.statusID = statusID;
        this.uur = uur;
        this.minuten = minuten;
    }

    /**
     * constructor voor inserts
     * @param uur uren
     * @param minuten minuten
     */
    public Tijd(int uur, int minuten) {
        this.statusID = 1;
        this.uur = uur;
        this.minuten = minuten;
    }

    /**
     * Haalt een lijst op van alle tijden uit de db
     * @param db database
     * @return lijst met tijden
     */
    public static ArrayList<Tijd> getTijden(DatabaseHelper db) {
        SQLiteDatabase myDb = db.getDB();
        String query = "SELECT " +
                db.TIME_HOUR + ", " +
                db.TIME_MINUTE + " " +
                "FROM " + db.TIME_TABLE_NAME + " " +
                "WHERE " + db.TIME_USER_ID + " = 1;";

        Cursor res = myDb.rawQuery(query, null);
        ArrayList<Tijd> uren = new ArrayList<>();

        if (res.moveToFirst()) {
            do {
                Tijd t = new Tijd(res.getInt(res.getColumnIndex(db.TIME_HOUR)), res.getInt(res.getColumnIndex(db.TIME_MINUTE)));
                uren.add(t);
            } while (res.moveToNext());
        }

        Collections.sort(uren);

        return uren;
    }

    /**
     * insert een tijd in de database
     * @param db database
     */
    public void insert(DatabaseHelper db) {
        SQLiteDatabase myDB = db.getDB();

        ContentValues cv = new ContentValues();
        cv.put(db.TIME_USER_ID, 1);
        cv.put(db.TIME_HOUR, uur);
        cv.put(db.TIME_MINUTE, minuten);

        myDB.insert(db.TIME_TABLE_NAME, null, cv);

    }

    /**
     * verwijderd een tijd uit de database
     * @param db database
     */
    public void delete(DatabaseHelper db) {
        SQLiteDatabase myDb = db.getDB();
        myDb.execSQL("DELETE FROM " + db.TIME_TABLE_NAME +
        " WHERE " + db.TIME_HOUR + " = " + uur +
        " AND " + db.TIME_MINUTE + " = " + minuten);
    }

    public int getUur() {
        return uur;
    }

    public int getMinuten() {
        return minuten;
    }

    /**
     * sorteert de tijden heel netjes op uren en dan op minuten
     * @param o tijd waarmee hij de huidige tijd mee moet vergelijken
     * @return een getal waarmee hij de lijst op volgorde sorteerd
     */
    @Override
    public int compareTo(@NonNull Tijd o) {
        if(uur - o.getUur() != 0){
            return uur - o.getUur();
        } else {
            return minuten - o.getMinuten();
        }
    }
}
