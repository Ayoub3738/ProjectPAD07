package com.example.ayoubelyaghmouri.smokes.models;

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

    }

    public void delete(int uur, int minuten, DatabaseHelper db) {

    }

    public int getUur() {
        return uur;
    }

    public int getMinuten() {
        return minuten;
    }
}
