package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by jerry on 16-5-2017.
 */

public class Tijd {

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
        return new ArrayList<>();
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
}
