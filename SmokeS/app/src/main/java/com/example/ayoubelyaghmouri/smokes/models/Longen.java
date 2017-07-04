package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.models.Orgaan;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.util.ArrayList;

/**
 * Longen is een orgaan zoals huid en hart
 * Created by jerry on 10-5-2017.
 */

public class Longen extends Orgaan {

    /**
     * constructor
     * @param naam naam van het orgaan
     * @param percentage gezondheids percentage in % van een orgaan (hoe gezond zijn je longen?)
     */
    public Longen(String naam, int percentage) {
        super(naam, percentage);
    }

    /**
     * Berekent je gezondheid van je longen (Helaas is ook deze berekening niet realistisch, komt omdat het 5 jaar duurt voordat er wat gebeurt met de realistische berekening)
     * @param nietGerookteSigaretten het aantal sigaretten die je niet gerookt hebt
     * @param aantalMeldingen Het totaal aantal meldingen die je hebt ontvangen
     * @param db De database, voor als de berekening wat nodig heeft uit de database (haalde eerst tijden op)
     */
    @Override
    public void berekenPercentage(int nietGerookteSigaretten, int aantalMeldingen, DatabaseHelper db) {

        final double BEGIN_PERCENTAGE = 60;
        final int MAXIMALE_PERCENTAGE = 100;

        final double PROCENT_OMHOOG = 3;
        final double PROCENT_OMLAAG = 5;

        double percentage = BEGIN_PERCENTAGE + (nietGerookteSigaretten * PROCENT_OMHOOG);
        percentage = percentage - ((aantalMeldingen - nietGerookteSigaretten) * PROCENT_OMLAAG);

        int perc = (int)Math.round(percentage);

        if(perc > MAXIMALE_PERCENTAGE)
            perc = MAXIMALE_PERCENTAGE;

        super.setPercentage(perc);
    }
}
