package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Dit is een algemene klasse voor al je organen, deze dingen moeten er minimaal in zitten per orgaan
 * Created by jerry on 10-5-2017.
 */

public abstract class Orgaan {

    public String naam;
    public int percentage;

    /**
     * constructor
     * @param naam naam van het orgaan
     * @param percentage een percentage hoe gezond het orgaan is (dit gaat in % en 100 is MAX)
     */
    public Orgaan(String naam, int percentage) {
        this.naam = naam;
        this.percentage = percentage;
    }

    /**
     * berekent de staat van een orgaan. 100% is goed, 0% is slecht
     * @param nietGerookteSigaretten het aantal sigaretten die je niet gerookt hebt
     * @param aantalMeldingen Het totaal aantal meldingen die je hebt ontvangen
     * @param db De database, voor als de berekening wat nodig heeft uit de database (haalde eerst tijden op)
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
