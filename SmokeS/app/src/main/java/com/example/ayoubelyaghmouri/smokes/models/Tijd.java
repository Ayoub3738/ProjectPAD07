package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jerry on 16-5-2017.
 */

public class Tijd implements Comparable<Tijd> {

    private int tijdID;
    private int statusID;
    private int uur;
    private int minuten;

    public Tijd(int tijdID, int statusID, int uur, int minuten) {
        this.tijdID = tijdID;
        this.statusID = statusID;
        this.uur = uur;
        this.minuten = minuten;
    }

    public Tijd(int uur, int minuten) {
        this.statusID = 1;
        this.uur = uur;
        this.minuten = minuten;
    }

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

    public void insert(DatabaseHelper db) {
        SQLiteDatabase myDB = db.getDB();

        ContentValues cv = new ContentValues();
        cv.put(db.TIME_USER_ID, 1);
        cv.put(db.TIME_HOUR, uur);
        cv.put(db.TIME_MINUTE, minuten);

        myDB.insert(db.TIME_TABLE_NAME, null, cv);

    }

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

    @Override
    public int compareTo(@NonNull Tijd o) {
        if(uur - o.getUur() != 0){
            return uur - o.getUur();
        } else {
            return minuten - o.getMinuten();
        }
    }
}
