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

import com.example.ayoubelyaghmouri.smokes.models.Character;
import com.example.ayoubelyaghmouri.smokes.models.Sigarettenpak;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Created by Ginger on 25-4-2017.
 */


public class BesparingenOverzichtActivity extends AppCompatActivity {

    private EditText etPrijsSigarettenPak, etSigarettenPakInhoud, etGebruikersNaam, etSigarettenPakMerk;
    private Button saveBtn;
    private Character character;
    private Sigarettenpak sigarettenPak;
    private DatabaseHelper myDb;
    private final int weekLengte = 7;
    private final double gemiddeldeMaand = 30.4;
    private final double aantalWekenInMaand = 4.3;
    private final int aantalMaandenInJaar = 12;
    private final int aantalDagenInJaar = 365;
    private String gebruikersNaam = "";
    private double prijs = 0;
    private int inhoud = 0;
    private String merk = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besparingen_overzicht);
        geefUitslag();


        myDb = new DatabaseHelper(this);
        character = Character.getCharacter(myDb);
        sigarettenPak = Sigarettenpak.getPak(myDb);
        saveBtn.setText("Opslaan");

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

                String merkStr = etSigarettenPakMerk.getText().toString();
                if (merkStr == null || merkStr.isEmpty()){
                    return;
                } else{
                    merk = etSigarettenPakMerk.getText().toString();
                }

                String gebruikersNaamStr = etGebruikersNaam.getText().toString();
                if (gebruikersNaamStr == null || gebruikersNaamStr.isEmpty()){
                    return;
                } else{
                    gebruikersNaam = etGebruikersNaam.getText().toString();
                }

               saveBtn.setText("Opgeslagen!");
                character.update(gebruikersNaam, myDb);
                sigarettenPak.update(prijs, inhoud, merk, myDb);


            }
        });


    }

    public void geefUitslag() {

        etPrijsSigarettenPak = (EditText) findViewById(R.id.etPrijsSigarettenPak);
        etSigarettenPakInhoud = (EditText) findViewById(R.id.etSigarettenPakInhoud);
        etSigarettenPakMerk = (EditText) findViewById(R.id.etSigarettenPakMerk);
        etGebruikersNaam = (EditText) findViewById(R.id.etGebruikersNaam);
        saveBtn = (Button) findViewById(R.id.saveBtn);

    }


}


