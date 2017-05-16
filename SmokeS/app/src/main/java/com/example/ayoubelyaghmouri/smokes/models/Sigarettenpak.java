package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Created by jerry on 2-5-2017.
 */

public class Sigarettenpak {
    private int pakID;
    private double prijs;
    private String merk;
    private int aantalSigaretten;

    public Sigarettenpak(double prijs, String merk, int aantalSigaretten) {
        this.pakID = 1;
        this.prijs = prijs;
        this.merk = merk;
        this.aantalSigaretten = aantalSigaretten;
    }

    public Sigarettenpak(int pakID, double prijs, String merk, int aantalSigaretten) {
        this.pakID = pakID;
        this.prijs = prijs;
        this.merk = merk;
        this.aantalSigaretten = aantalSigaretten;
    }

    public static Sigarettenpak getPak(DatabaseHelper db) {
        Sigarettenpak pak = new Sigarettenpak(8.5, "Malborro", 21);
        //haalt pak uit database

        return pak;
    }

    public void insert(DatabaseHelper db) {
        //insert dit object in db
    }

    public double berekenPrijsSigaret() {

        //gekke berekening
        return 5;
    }

    public int getPakID() {
        return pakID;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getMerk() {
        return merk;
    }

    public int getAantalSigaretten() {
        return aantalSigaretten;
    }
}
