package com.example.ayoubelyaghmouri.smokes;

/**
 * Created by jerry on 2-5-2017.
 */

public class Sigarettenpak {
    private int pakID;
    private double prijs;
    private String merk;
    private int aantalSigaretten;

    public Sigarettenpak(int pakID, double prijs, String merk, int aantalSigaretten) {
        this.pakID = pakID;
        this.prijs = prijs;
        this.merk = merk;
        this.aantalSigaretten = aantalSigaretten;
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
