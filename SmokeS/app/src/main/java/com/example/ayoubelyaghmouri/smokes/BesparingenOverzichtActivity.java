package com.example.ayoubelyaghmouri.smokes;


import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
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
import com.example.ayoubelyaghmouri.smokes.models.Status;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

import java.util.Date;

/**
 * Created by Ginger on 25-4-2017.
 */


public class BesparingenOverzichtActivity extends AppCompatActivity {

    private EditText etPrijsSigarettenPak, etSigarettenPakInhoud, etGebruikersNaam, etSigarettenPakMerk;
    private Button saveBtn;
    private Character character;
    private Sigarettenpak sigarettenPak;
    private DatabaseHelper myDb;
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

        etGebruikersNaam.setText(character.getUserNaam());
        etPrijsSigarettenPak.setText(sigarettenPak.getPrijs() + "");
        etSigarettenPakInhoud.setText(sigarettenPak.getAantalSigaretten() + "");
        etSigarettenPakMerk.setText(sigarettenPak.getMerk());

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(BesparingenOverzichtActivity.this);
                alert.setTitle("Weet u zeker dat u de gegevens wilt opslaan?")
                        .setMessage("De app zal opnieuw opgestart worden.")
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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

                                character.update(gebruikersNaam, myDb);
                                sigarettenPak.update(prijs, inhoud, merk, myDb);

                                Intent restartIntent = new Intent(BesparingenOverzichtActivity.this, MainActivity.class);
                                startActivity(restartIntent);
                            }
                        })
                        .setNegativeButton("Annuleer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                alert.show();
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


