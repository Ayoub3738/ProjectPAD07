package com.example.ayoubelyaghmouri.smokes.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Pair;

import com.example.ayoubelyaghmouri.smokes.models.Character;
import com.example.ayoubelyaghmouri.smokes.models.Sigarettenpak;
import com.example.ayoubelyaghmouri.smokes.models.Status;
import com.example.ayoubelyaghmouri.smokes.models.Tijd;

import java.util.ArrayList;
import java.util.Date;

/**
 * De database helper is een klasse die helpt om een database aan te maken, te updaten en te kunnen praten met de database
 * Created by jerry on 25-4-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //naam en versie van de database
    public static final String DATABASE_NAME = "smokes.db";
    public static final int DB_VERSION = 18;

    //tabelnaam en kollommen van tabel Sigarettenpak
    public static final String PAK_TABLE_NAME = "sigarettenpak_table";
    public static final String PAK_PAK_ID = "pakID";
    public static final String PAK_PRIJS = "prijs";
    public static final String PAK_MERK = "merk";
    public static final String PAK_AANTAL_SIGARETTEN = "aantalSigaretten";

    //tabelnaam en kollommen van tabel Character
    public static final String CHAR_TABLE_NAME = "character_table";
    public static final String CHAR_CHARACTER_ID = "characterID";
    public static final String CHAR_USER_NAAM = "userNaam";
    public static final String CHAR_HAAR_KLEUR = "haarKleur";
    public static final String CHAR_KLEUR_OGEN = "kleurOgen";

    //tabelnaam en kollommen van tabel User/Status
    public static final String USER_TABLE_NAME = "user_table";
    public static final String USER_USER_ID = "userID";
    public static final String USER_CHARACTER_ID = "characterID";
    public static final String USER_PAK_ID = "pakID";
    public static final String USER_STREAK = "streak";
    public static final String USER_NIET_GEROOKTE_SIGARETTEN = "nietGerookteSigaretten";
    public static final String USER_AANTAL_MELDINGEN = "aantalMeldingen";
    public static final String USER_RECORD_STREAK = "recordStreak";
    public static final String USER_LAATST_GEROOKT = "laatstGerookt";

    //tabelnaam en kollommen van tabel Rooktijd
    public static final String TIME_TABLE_NAME = "rooktijd_table";
    public static final String TIME_TIME_ID = "timeID";
    public static final String TIME_USER_ID = "userID";
    public static final String TIME_HOUR = "uur";
    public static final String TIME_MINUTE = "minuut";

    //tabelnaam en kollommen van tabel Achievement
    public static final String ACH_TABLE_NAME = "achievement_table";
    public static final String ACH_ACHIEVEMENT_ID = "achievementID";
    public static final String ACH_USER_ID = "userID";
    public static final String ACH_BEHAALD = "behaald";
    public static final String ACH_NAAM = "naam";
    public static final String ACH_BESCHRIJVING = "beschrijving";

    /**
     * Constructor
     * @param context het scherm waar je op zit
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    /**
     * Wordt uitgevoerd als de database voor de eerste keer wordt aangemaakt
     * @param db lege database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //maakt tabel sigarettenpak_table aan
        db.execSQL("CREATE TABLE " + PAK_TABLE_NAME + " (" +
                PAK_PAK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                PAK_PRIJS + " REAL, " +
                PAK_MERK + " TEXT, " +
                PAK_AANTAL_SIGARETTEN + " INTEGER" +
                ");");

        //maakt tabel character_table aan
        db.execSQL("CREATE TABLE " + CHAR_TABLE_NAME + " (" +
                CHAR_CHARACTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                CHAR_USER_NAAM + " TEXT, " +
                CHAR_HAAR_KLEUR + " TEXT, " +
                CHAR_KLEUR_OGEN + " TEXT" +
                ");");

        //maakt tabel user_table aan
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (" +
                USER_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + //0
                USER_CHARACTER_ID + " INTEGER, " + //1
                USER_PAK_ID + " INTEGER, " + //2
                USER_STREAK + " INTEGER, " + //3
                USER_NIET_GEROOKTE_SIGARETTEN + " INTEGER, " + //4
                USER_AANTAL_MELDINGEN + " INTEGER, " + //5
                USER_RECORD_STREAK + " INTEGER, " + //6
                USER_LAATST_GEROOKT + " INTEGER, " + //7, om een of andere reden stored sqlite datums in INTEGER
                "FOREIGN KEY (" + USER_CHARACTER_ID + ") REFERENCES " + CHAR_TABLE_NAME + " (" + CHAR_CHARACTER_ID + "), " +
                "FOREIGN KEY (" + USER_PAK_ID + ") REFERENCES " + PAK_TABLE_NAME + " (" + PAK_PAK_ID + ")" +
                ");");

        //maakt tabel rooktijd_table
        db.execSQL("CREATE TABLE " + TIME_TABLE_NAME + " (" +
                TIME_TIME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TIME_USER_ID + " INTEGER NOT NULL, " +
                TIME_HOUR + " INTEGER NOT NULL, " +
                TIME_MINUTE + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + TIME_USER_ID + ") REFERENCES " + USER_TABLE_NAME + " (" + USER_USER_ID + ")" +
                ");");

        //maakt tabel achievement_table aan
        db.execSQL("CREATE TABLE " + ACH_TABLE_NAME + " (" +
                ACH_ACHIEVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                ACH_USER_ID + " INTEGER, " +
                ACH_BEHAALD + " INTEGER, " +
                ACH_NAAM + " TEXT, " +
                ACH_BESCHRIJVING + " TEXT, " +
                "FOREIGN KEY (" + ACH_USER_ID + ") REFERENCES " + USER_TABLE_NAME + " (" + USER_USER_ID + ")" +
                ");");

        Sigarettenpak pak = new Sigarettenpak(6.5, "MARLBORO", 21);
        pak.insert(db);

        Character character = new Character("Ginger", "Blond", "Blauw");
        character.insert(db);

        Date datumNu = new Date();
        Status status = new Status(pak, null, 0, 0, datumNu, 0, 0);
        status.insert(db);

    }

    /**
     * Wordt uitgevoerd als er een andere versie (DB_VERSION) in de code staat dan dat er op het apparaat runt
     * @param db de database
     * @param oldVersion oude versie
     * @param newVersion nieuwe versie
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TIME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ACH_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PAK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CHAR_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Selecteert alles uit een tabel
     * @param tableName tabelnaam waarvan je alles wil hebben
     * @return gegevens van een tabel
     */
    public Cursor selectAll(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tableName, null);
        return res;
    }

    /**
     * Geeft de database zelf mee
     * @return de database
     */
    public SQLiteDatabase getDB() {
        return this.getWritableDatabase();
    }
}
