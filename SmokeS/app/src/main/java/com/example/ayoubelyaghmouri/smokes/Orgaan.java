package com.example.ayoubelyaghmouri.smokes;

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
    public abstract void berekenPercentage(int nietGerookteSigaretten);

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
