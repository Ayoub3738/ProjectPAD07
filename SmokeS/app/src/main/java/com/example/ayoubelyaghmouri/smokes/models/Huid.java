package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.models.Orgaan;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * de klasse huid, dit is ook een orgaan zoals hart en longen
 * Created by jerry on 10-5-2017.
 */

public class Huid extends Orgaan {

    /**
     * constructor
     * @param naam naam van het orgaan
     * @param percentage percentage hoe gezond het orgaan is
     */
    public Huid(String naam, int percentage) {
        super(naam, percentage);
    }

    /**
     * berekent hoe gezond je huid is (hij is niet realistisch omdat wij met de realistische berekening 5 jaar moesten wachten voordat er wat veranderde)
     * @param nietGerookteSigaretten het aantal sigaretten die je niet gerookt hebt
     * @param aantalMeldingen totaal aantal meldingen binnen de app
     * @param db database die eerst de tijden ophaalde
     */
    @Override
    public void berekenPercentage(int nietGerookteSigaretten, int aantalMeldingen, DatabaseHelper db) {
        final double BEGIN_PERCENTAGE = 85;
        final int MAXIMALE_PERCENTAGE = 100;

        final double PROCENT_OMHOOG = 0.4;
        final double PROCENT_OMLAAG = 1;

        double percentage = BEGIN_PERCENTAGE + (nietGerookteSigaretten * PROCENT_OMHOOG);
        percentage = percentage - ((aantalMeldingen - nietGerookteSigaretten) * PROCENT_OMLAAG);

        int perc = (int)Math.round(percentage);

        if(perc > MAXIMALE_PERCENTAGE)
            perc = MAXIMALE_PERCENTAGE;

        super.setPercentage(perc);
    }
}
