package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Created by jerry on 16-5-2017.
 */

public class Character {

    private int characterID;
    private String userNaam;
    private String haarKleur;
    private String kleurOgen;

    public Character(int characterID, String userNaam, String haarKleur, String kleurOgen) {
        this.characterID = characterID;
        this.userNaam = userNaam;
        this.haarKleur = haarKleur;
        this.kleurOgen = kleurOgen;
    }

    public Character(String userNaam, String haarKleur, String kleurOgen) {
        this.userNaam = userNaam;
        this.haarKleur = haarKleur;
        this.kleurOgen = kleurOgen;
    }

    public static Character getCharacter(DatabaseHelper db) {
        return new Character("", "", "");
    }

    public void insert(DatabaseHelper db) {
        SQLiteDatabase myDb = db.getDB();
        insert(myDb);
    }

    public void insert(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.CHAR_USER_NAAM, userNaam);
        cv.put(DatabaseHelper.CHAR_HAAR_KLEUR, haarKleur);
        cv.put(DatabaseHelper.CHAR_KLEUR_OGEN, kleurOgen);

        db.insert(DatabaseHelper.CHAR_TABLE_NAME, null, cv);
    }

    public void update(DatabaseHelper db) {

    }
}
