package com.example.ayoubelyaghmouri.smokes.models;

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

    }

    public void update(DatabaseHelper db) {

    }
}
