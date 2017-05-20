package com.example.ayoubelyaghmouri.smokes.models;

import com.example.ayoubelyaghmouri.smokes.MainActivity;
import com.example.ayoubelyaghmouri.smokes.R;
import com.example.ayoubelyaghmouri.smokes.models.Orgaan;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by jerry on 10-5-2017.
 */

public class Gezondheid {

    public int totaalGezondheid;
    private ArrayList<Orgaan> organen;

    public Gezondheid(int totaalGezondheid) {
        this.totaalGezondheid = totaalGezondheid;
        organen = new ArrayList<>();
    }

    /**
     * berekent het gemiddelde van alle organen om een gemiddeld percentage te krijgen
     */
    public void berekenTotaalGezondheid(int nietGerookteSigaretten, int aantalMeldingen, DatabaseHelper db) {
        organen = new ArrayList<>(); //maakt arraylist even leeg
        int totaalPercentage = 0;
        final int TOTAAL_ORGANEN = 3;

        //de standaard percentages zijn 0
        Longen longen = new Longen("Longen", 0);
        Hart hart = new Hart("Hart", 0);
        Huid huid = new Huid("Huid", 0);

        //hier word de standaard percentages veranderd met onze top geheime berekening
        longen.berekenPercentage(nietGerookteSigaretten, aantalMeldingen, db);
        hart.berekenPercentage(nietGerookteSigaretten, aantalMeldingen, db);
        huid.berekenPercentage(nietGerookteSigaretten, aantalMeldingen, db);

        organen.add((Orgaan)longen);
        organen.add((Orgaan)hart);
        organen.add((Orgaan)huid);

        for (Orgaan orgaan : organen) {
            totaalPercentage += orgaan.getPercentage();
        }

        totaalGezondheid = (int)Math.round((float)totaalPercentage / TOTAAL_ORGANEN);

        MainActivity.imageSwitcher.setImageResource(R.drawable.womancartooncharacterfull);

        if (totaalGezondheid <= 60){
            MainActivity.imageSwitcher.setImageResource(R.drawable.womancartooncharacterfull60health);
        }

        if (totaalGezondheid <= 30){
            MainActivity.imageSwitcher.setImageResource(R.drawable.womancartooncharacterfull30health);
        }
    }

    public int getTotaalGezondheid() {
        return totaalGezondheid;
    }
}
