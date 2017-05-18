package com.example.ayoubelyaghmouri.smokes.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by jerry on 16-5-2017.
 */

public class Achievement {

    private int achievementID;
    private int statusID;
    private boolean behaald;
    private String naam;
    private String beschrijving;

    public Achievement(int achievementID, int statusID, boolean behaald, String naam, String beschrijving) {
        this.achievementID = achievementID;
        this.statusID = statusID;
        this.behaald = behaald;
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    public Achievement(boolean behaald, String naam, String beschrijving) {
        this.statusID = 1;
        this.behaald = behaald;
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    public static Achievement getAchievement(String naam, DatabaseHelper db) {

        SQLiteDatabase myDb = db.getDB();
        String query = "SELECT * " +
                "FROM " + db.ACH_TABLE_NAME + " " +
                "WHERE " + db.ACH_NAAM + " = '" + naam + "';";

        Achievement achievement = null;
        Cursor res = myDb.rawQuery(query, null);

        if(res.moveToFirst()) {
            res.moveToFirst();

            boolean behaald = (res.getInt(res.getColumnIndex(db.ACH_BEHAALD)) == 0) ? false : true;

            achievement = new Achievement(
                    res.getInt(res.getColumnIndex(db.ACH_ACHIEVEMENT_ID)),
                    res.getInt(res.getColumnIndex(db.ACH_USER_ID)),
                    behaald,
                    res.getString(res.getColumnIndex(db.ACH_NAAM)),
                    res.getString(res.getColumnIndex(db.ACH_BESCHRIJVING))
            );

            res.close();
        }

        return achievement;
    }

    public static ArrayList<Achievement> getAchievements(DatabaseHelper db) {
        return new ArrayList<>();
    }

    public void insert(DatabaseHelper db) {

    }

    public void update(DatabaseHelper db) {

    }
}
