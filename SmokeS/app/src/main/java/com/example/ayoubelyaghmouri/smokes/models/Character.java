package com.example.ayoubelyaghmouri.smokes.models;

import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.security.PrivateKey;
import java.util.Date;

/**
 * deze class bestaat uit de eigenschappen van je character
 * Created by jerry on 16-5-2017.
 */

public class Character {

    private int characterID;
    private String userNaam = "";
    private String haarKleur = "";
    private String kleurOgen = "";
    private int spriteSarah = 0;

    /**
     * constructor
     * @param characterID een uniek id voor een character (is standaard 1)
     * @param userNaam een gebruikersnaam (de naam van jezelf)
     * @param haarKleur een haarkleur voor je character
     * @param kleurOgen de kleur ogen van je character
     */
    public Character(int characterID, String userNaam, String haarKleur, String kleurOgen) {
        this.characterID = characterID;
        this.userNaam = userNaam;
        this.haarKleur = haarKleur;
        this.kleurOgen = kleurOgen;
        this.spriteSarah = spriteSarah;
    }

    /**
     * constructor voor het inserten (word gebruikt bij het aanmaken van de database)
     * @param userNaam Gebruikersnaam (je eigen naam)
     * @param haarKleur kleur van je avatar haar haar
     * @param kleurOgen kleur van je avatar haar ogen
     */
    public Character(String userNaam, String haarKleur, String kleurOgen) {
        this.userNaam = userNaam;
        this.haarKleur = haarKleur;
        this.kleurOgen = kleurOgen;
    }

    /**
     * Haalt alle eigenschappen van je avatar op
     * @param db database helper, deze class is nodig om met de database te kunnen praten
     * @return alle eigenschappen van je avatar in een Character object
     */
    public static Character getCharacter(DatabaseHelper db) {

        SQLiteDatabase myDb = db.getDB();
        String query = "SELECT * " +
                "FROM " + db.CHAR_TABLE_NAME + " " +
                "WHERE " + db.CHAR_CHARACTER_ID + " = 1;";

        Character character = null;
        Cursor res = myDb.rawQuery(query, null);

        if(res.moveToFirst()) {
            res.moveToFirst();

            character = new Character(
                    res.getInt(res.getColumnIndex(db.CHAR_CHARACTER_ID)),
                    res.getString(res.getColumnIndex(db.CHAR_USER_NAAM)),
                    res.getString(res.getColumnIndex(db.CHAR_HAAR_KLEUR)),
                    res.getString(res.getColumnIndex(db.CHAR_KLEUR_OGEN))
            );
            res.close();
        }

        return character;
    }

    /**
     * Maakt een character aan in de database
     * @param db databasehelper die nodig is om te praten met database
     */
    public void insert(DatabaseHelper db) {
        SQLiteDatabase myDb = db.getDB();
        insert(myDb);
    }

    /**
     * Maakt een character aan in de database
     * @param db dit is de database zelf die je meegeeft
     */
    public void insert(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.CHAR_USER_NAAM, userNaam);
        cv.put(DatabaseHelper.CHAR_HAAR_KLEUR, haarKleur);
        cv.put(DatabaseHelper.CHAR_KLEUR_OGEN, kleurOgen);

        db.insert(DatabaseHelper.CHAR_TABLE_NAME, null, cv);
    }

    /**
     * veranderd je gebruikersnaam
     * @param userNaam gebruikersnaam
     * @param db database waar hij het in opslaat
     */
    public static void update(String userNaam, DatabaseHelper db) {
        SQLiteDatabase dbLite = db.getDB();
        ContentValues cv = new ContentValues();

        cv.put(db.CHAR_USER_NAAM, userNaam);

        dbLite.update(db.CHAR_TABLE_NAME, cv, db.CHAR_CHARACTER_ID + " = 1", null);
    }

    /**
     * update alle eigenschappen van je character en slaat het op
     * @param haarKleur kleur van je avatar haar haar
     * @param oogKleur kleur van je avatar haar ogen
     * @param db database waar hij het in opslaat
     */
    public static void update(String haarKleur, String oogKleur, DatabaseHelper db, int spriteSarah) {
        SQLiteDatabase dbLite = db.getDB();
        ContentValues cv = new ContentValues();

        cv.put(db.CHAR_HAAR_KLEUR, haarKleur);
        cv.put(db.CHAR_KLEUR_OGEN, oogKleur);

        dbLite.update(db.CHAR_TABLE_NAME, cv, db.CHAR_CHARACTER_ID + " = 1", null);
    }


    public String getUserNaam(){

        return userNaam;
    }

    public String getHaarKleur(){
        return haarKleur;
    }

    public String getKleurOgen(){
        return kleurOgen;
    }

    public void setHaarKleur(String haarKleur) {
        this.haarKleur = haarKleur;
    }

    public void setKleurOgen(String kleurOgen) {
        this.kleurOgen = kleurOgen;
    }
}
