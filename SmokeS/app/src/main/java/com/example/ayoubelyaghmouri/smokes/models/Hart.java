package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.models.Orgaan;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Created by jerry on 10-5-2017.
 */

public class Hart extends Orgaan {

    public Hart(String naam, int percentage) {
        super(naam, percentage);
    }

    @Override
    public void berekenPercentage(int nietGerookteSigaretten, int aantalMeldingen, DatabaseHelper db) {
        final float BEGIN_PERCENTAGE = 75;
        final int MAXIMALE_PERCENTAGE = 100;

        final double PROCENT_OMHOOG = 2;
        final double PROCENT_OMLAAG = 3;

        double percentage = BEGIN_PERCENTAGE + (nietGerookteSigaretten * PROCENT_OMHOOG);
        percentage = percentage - ((aantalMeldingen - nietGerookteSigaretten) * PROCENT_OMLAAG);

        int perc = (int)Math.round(percentage);

        if(perc > MAXIMALE_PERCENTAGE)
            perc = MAXIMALE_PERCENTAGE;

        super.setPercentage(perc);
    }
}
