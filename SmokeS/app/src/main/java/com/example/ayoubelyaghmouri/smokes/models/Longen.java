package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.models.Orgaan;

/**
 * Created by jerry on 10-5-2017.
 */

public class Longen extends Orgaan {

    public Longen(String naam, int percentage) {
        super(naam, percentage);
    }

    @Override
    public void berekenPercentage(int nietGerookteSigaretten) {
        //het zou nog kunnen dat je meer nodig hebt dan alleen nietGerookteSigaretten (bijv aantalMeldingen)

        //hier komt gekke berekening die percentage berekent
        super.setPercentage(100);
    }
}
