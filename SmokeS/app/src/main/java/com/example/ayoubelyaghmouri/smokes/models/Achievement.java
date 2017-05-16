package com.example.ayoubelyaghmouri.smokes.models;

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

    public Achievement getAchievement(String naam, DatabaseHelper db) {
        return new Achievement(true, naam, "");
    }

    public ArrayList<Achievement> getAchievements(DatabaseHelper db) {
        return new ArrayList<>();
    }

    public void insert(DatabaseHelper db) {

    }

    public void update(DatabaseHelper db) {
        
    }
}
