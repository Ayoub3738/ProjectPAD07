package com.example.ayoubelyaghmouri.smokes;

import java.util.Date;

/**
 * Created by jerry on 2-5-2017.
 */

public class Status {
    public int nietGerookteSigaretten;
    private int aantalMeldingen;
    private int streak;
    private Date laatstGerookt;
    private Sigarettenpak pak;

    public Status(int nietGerookteSigaretten, int aantalMeldingen, int streak, Date laatstGerookt, Sigarettenpak pak) {
        this.nietGerookteSigaretten = nietGerookteSigaretten;
        this.aantalMeldingen = aantalMeldingen;
        this.streak = streak;
        this.laatstGerookt = laatstGerookt;
        this.pak = pak;
    }

    public Date berekenTijdNietGerookt() {
        return laatstGerookt; //date.now - laatstgerookt;
    }

    public double berekenGeldBesparingen() {
        int aantalPakjes = (int)Math.ceil((float)nietGerookteSigaretten / pak.getAantalSigaretten());
        return aantalPakjes * pak.getPrijs();
    }

    public int getNietGerookteSigaretten() {
        return nietGerookteSigaretten;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
}
