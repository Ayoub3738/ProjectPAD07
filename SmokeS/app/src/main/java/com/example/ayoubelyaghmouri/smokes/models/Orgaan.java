package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Created by jerry on 10-5-2017.
 */

public abstract class Orgaan {

    public String naam;
    public int percentage;

    public Orgaan(String naam, int percentage) {
        this.naam = naam;
        this.percentage = percentage;
    }

    /**
     * berekent de staat van een orgaan. 100% is goed, 0% is slecht
     * @param nietGerookteSigaretten het aantal sigaretten die je niet gerookt hebt
     */
    public abstract void berekenPercentage(int nietGerookteSigaretten, int aantalMeldingen, DatabaseHelper db);

    public String getNaam() {
        return naam;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
