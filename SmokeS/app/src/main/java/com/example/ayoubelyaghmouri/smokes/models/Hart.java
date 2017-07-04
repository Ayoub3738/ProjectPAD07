package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.models.Orgaan;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Dit is een class Hart, deze berekent hoe gezond je hart is
 * Created by jerry on 10-5-2017.
 */

public class Hart extends Orgaan {

    /**
     * constructor
     * @param naam naam van je hart
     * @param percentage percentage hoe gezond je hart is
     */
    public Hart(String naam, int percentage) {
        super(naam, percentage);
    }

    /**
     * berekent hoe gezond je hart is (helaas is deze berekening niet realistisch, dit komt omdat met de realistische berekening 5 jaar duurt voordat je character er gezonder uit gaat zien)
     * @param nietGerookteSigaretten het aantal sigaretten die je niet gerookt hebt
     * @param aantalMeldingen totaal aantal meldingen die je ontvangen hebt in deze app
     * @param db database waar hij bij de vorige berekening het gemiddelde aantal tijden per dag ophaalde
     */
    @Override
    public void berekenPercentage(int nietGerookteSigaretten, int aantalMeldingen, DatabaseHelper db) {
        final double BEGIN_PERCENTAGE = 75;
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
