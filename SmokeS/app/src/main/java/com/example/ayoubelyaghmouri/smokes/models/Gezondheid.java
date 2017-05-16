package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.models.Orgaan;

import java.util.ArrayList;

/**
 * Created by jerry on 10-5-2017.
 */

public class Gezondheid {

    private int totaalGezondheid;
    private ArrayList<Orgaan> organen;

    public Gezondheid(int totaalGezondheid) {
        this.totaalGezondheid = totaalGezondheid;
        organen = new ArrayList<>();
    }

    /**
     * berekent het gemiddelde van alle organen om een gemiddeld percentage te krijgen
     */
    public void berekenTotaalGezondheid() {
        int totaalPercentage = 0;

        for (Orgaan orgaan : organen) {
            totaalPercentage =+ orgaan.getPercentage();
        }

        totaalGezondheid = (int)Math.round((float)totaalPercentage / organen.size());
    }

    public int getTotaalGezondheid() {
        return totaalGezondheid;
    }
}
