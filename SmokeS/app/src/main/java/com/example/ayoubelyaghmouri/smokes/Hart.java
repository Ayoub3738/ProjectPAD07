package com.example.ayoubelyaghmouri.smokes;

/**
 * Created by jerry on 10-5-2017.
 */

public class Hart extends Orgaan {

    public Hart(String naam, int percentage) {
        super(naam, percentage);
    }

    @Override
    public void berekenPercentage(int nietGerookteSigaretten) {
        super.setPercentage(100);
    }
}
