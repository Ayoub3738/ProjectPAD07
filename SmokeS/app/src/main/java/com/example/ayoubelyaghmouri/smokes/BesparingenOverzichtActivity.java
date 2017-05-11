package com.example.ayoubelyaghmouri.smokes;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ginger on 25-4-2017.
 */


public class BesparingenOverzichtActivity extends AppCompatActivity {

    private EditText etPrijsSigarettenPak, etSigarettenPakInhoud, etSigarettenGerooktPerDag;
    private Button saveBtn;
    private TextView tvWeekUitslag, tvMaandUitslag, tvJaarUitslag;

    public DatabaseHelper dbHelper;

    private final int weekLengte = 7;
    private final double gemiddeldeMaand = 30.4;
    private final double aantalWekenInMaand = 4.3;
    private final int aantalMaandenInJaar = 12;
    private final int aantalDagenInJaar = 365;
    private double prijs = 0;
    private int inhoud = 0;
    private int gerooktPerDag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besparingen_overzicht);
        geefUitslag();


        dbHelper = new DatabaseHelper(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String prijsStr = etPrijsSigarettenPak.getText().toString();
                if(prijsStr == null || prijsStr.isEmpty()){
                    return;
                } else {
                    prijs = Double.parseDouble(etPrijsSigarettenPak.getText().toString());
                }

                String inhoudStr = etSigarettenPakInhoud.getText().toString();
                if (inhoudStr == null || inhoudStr.isEmpty()){
                    return;
                } else{
                    inhoud = Integer.parseInt(etSigarettenPakInhoud.getText().toString());
                }

                String gerooktPerDagStr = etSigarettenGerooktPerDag.getText().toString();
                if (gerooktPerDagStr == null || gerooktPerDagStr.isEmpty()){
                    return;
                } else{
                    gerooktPerDag = Integer.parseInt(etSigarettenGerooktPerDag.getText().toString());
                }

             dbHelper.updatePak(prijs, inhoud);

            }
        });


    }

    public void geefUitslag() {

        etPrijsSigarettenPak = (EditText) findViewById(R.id.etPrijsSigarettenPak);
        etSigarettenPakInhoud = (EditText) findViewById(R.id.etSigarettenPakInhoud);
        etSigarettenGerooktPerDag = (EditText) findViewById(R.id.etSigarettenGerooktPerDag);
        saveBtn = (Button) findViewById(R.id.saveBtn);

    }


}


