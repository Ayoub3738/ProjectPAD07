package com.example.ayoubelyaghmouri.smokes.models;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.ayoubelyaghmouri.smokes.BesparingenOverzichtActivity;
import com.example.ayoubelyaghmouri.smokes.MainActivity;
import com.example.ayoubelyaghmouri.smokes.R;
import com.example.ayoubelyaghmouri.smokes.SelectTimeActivity;
import com.example.ayoubelyaghmouri.smokes.SettingsActivity;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;

/**
 * Dit is een test klasse/activity om te kijken of het switchen van een image op de character werkt (en deze wijzigingen op te slaan)
 */
public class AvatarTest extends AppCompatActivity {

    //de database
    private DatabaseHelper myDb;

    //de knoppen voor veranderen en opslaan
    private ImageButton btnNextImage;
    private ImageButton btnPrevImage;
    private Button btnHaarkleur;
    private Button btnOogkleur;
    private Button btnOpslaan;
    private Character character;

    //image switcher
    private ImageSwitcher imageSwitcherAvatar;

    //de variabelen die worden gebruikt voor het instellen van de sprite van Sarah
    public static int spriteSarah;
    private boolean aanpassenHaarKleur = true;

    private int hoeveelheidHaarKleuren = 2;
    private int hoeveelheidOogKleuren = 3;

    private int haarKleuren = 0;
    private int oogKleuren = 0;
    private Integer[][] sarahSprite = new Integer[hoeveelheidHaarKleuren][hoeveelheidOogKleuren];

    /**
     * dit is de init
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_test);

        //database toewijzen
        myDb = new DatabaseHelper(this);
        character = Character.getCharacter(myDb);

        //listeners/handlers knoppen
        onBtnNext();
        onBtnPrev();
        onBtnHair();
        onBtnEyes();
        onBtnOpslaan();

        //supercoolle animaties
        Animation animIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
        Animation animOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);

        //toewijzen imageswitcher en animaties
        imageSwitcherAvatar = (ImageSwitcher) findViewById(R.id.imageSwitcherAvatar);
        imageSwitcherAvatar.setInAnimation(animIn);
        imageSwitcherAvatar.setOutAnimation(animOut);

        imageSwitcherAvatar.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        //het toevoegen van de sprites van Sarah aan de 2d array
        sarahSprite[0][0] = R.drawable.womancartooncharacterfullbrownbrown;
        sarahSprite[0][1] = R.drawable.womancartooncharacterfullbrownblue;
        sarahSprite[0][2] = R.drawable.womancartooncharacterfullbrowngreen;

        sarahSprite[1][0] = R.drawable.womancartooncharacterfullblondbrown;
        sarahSprite[1][1] = R.drawable.womancartooncharacterfullblondblue;
        sarahSprite[1][2] = R.drawable.womancartooncharacterfullblondgreen;

        spriteSarah = sarahSprite[getHaarKleuren()][getOogKleuren()];
        btnOpslaan.setVisibility(View.INVISIBLE);
        imageSwitcherAvatar.setImageResource(spriteSarah);
    }


    /**
     * veranderd naar vorige haar in de lijst
     */
    public void prevImageHair(){

        haarKleuren--;
        if (haarKleuren < 0){
            haarKleuren = (hoeveelheidHaarKleuren - 1);
        }

        spriteSarah = sarahSprite[haarKleuren][oogKleuren];
        imageSwitcherAvatar.setImageResource(spriteSarah);
        Update();
    }

    /**
     * veranderd naar volgende haar in de lijst
     */
    public void nextImageHair(){

        haarKleuren++;
        if (haarKleuren > (hoeveelheidHaarKleuren - 1)){
            haarKleuren = 0;
        }

        spriteSarah = sarahSprite[haarKleuren][oogKleuren];
        imageSwitcherAvatar.setImageResource(spriteSarah);
        Update();
    }

    /**
     * veranderd naar vorige ogen in de lijst
     */
    public void prevImageEyes(){

        oogKleuren--;
        if (oogKleuren < 0){
            oogKleuren = (hoeveelheidOogKleuren - 1);
        }

        spriteSarah = sarahSprite[haarKleuren][oogKleuren];
        imageSwitcherAvatar.setImageResource(spriteSarah);
        Update();
    }

    /**
     * veranderd naar volgende ogen in de lijst
     */
    public void nextImageEyes(){

        oogKleuren++;
        if (oogKleuren > (hoeveelheidOogKleuren - 1)){
            oogKleuren = 0;
        }

        spriteSarah = sarahSprite[haarKleuren][oogKleuren];
        imageSwitcherAvatar.setImageResource(spriteSarah);
        Update();
    }

    /**
     * als je op deze knop klikt veranderd ie haar en ogen (deze is vooruit in de lijst)
     */
    public void onBtnNext() {
        btnNextImage = (ImageButton) findViewById(R.id.btnImageNext);
        btnNextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (aanpassenHaarKleur){
                    nextImageHair();
                }
                else{
                    nextImageEyes();
                }
            }
        });
    }

    /**
     * ook bij deze knop, als je erop klikt veranderd hij het haar en de ogen (deze is achteruit in de lijst)
     */
    public void onBtnPrev() {
        btnPrevImage = (ImageButton) findViewById(R.id.btnImagePrev);
        btnPrevImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (aanpassenHaarKleur){
                    prevImageHair();
                }
                else{
                    prevImageEyes();
                }
            }
        });
    }

    /**
     * deze veranderd de haarkleur
     */
    public void onBtnHair() {
        btnHaarkleur = (Button) findViewById(R.id.btnHaarkleur);
        btnHaarkleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aanpassenHaarKleur = true;
            }
        });
    }

    /**
     * deze veranderd de ogen
     */
    public void onBtnEyes() {
        btnOogkleur = (Button) findViewById(R.id.btnOogkleur);
        btnOogkleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aanpassenHaarKleur = false;
            }
        });
    }

    /**
     * dit is een knop die alles opslaat
     */
    public void onBtnOpslaan() {
        btnOpslaan = (Button) findViewById(R.id.btnSlaAvatarOp);
        btnOpslaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //met deze pop up alert word er om een bevestiging van de aanpassingen gevraagd
                AlertDialog.Builder alert = new AlertDialog.Builder(AvatarTest.this);
                alert.setTitle("Weet u zeker dat u de aanpassingen wilt opslaan?")

                        //als er ja word geselecteerd dan word de sprite van Sarah op het startscherm aangepast
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //character.update(BesparingenOverzichtActivity.gebruikersNaam, myDb, sarahSprite[getHaarKleuren()][getOogKleuren()]);
                                //MainActivity.imageSwitcher.setImageResource(sarahSprite[getHaarKleuren()][getOogKleuren()]);
                                MainActivity.spriteSarah = sarahSprite[getHaarKleuren()][getOogKleuren()];

                                //gaat terug naar het startscherm van de app
                                Intent restartIntent = new Intent(AvatarTest.this, MainActivity.class);
                                startActivity(restartIntent);
                            }
                        })
                        //als er nee word geselecteerd dan sluit het pop up alert en word er niks opgeslagen
                        .setNegativeButton("Nee", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                alert.show();
            }
        });
    }

    /**
     * dit is een knop die de staat van de opslaan knop op visible zet
     */
    public void Update(){
        if (spriteSarah != R.drawable.womancartooncharacterfull){
            btnOpslaan.setVisibility(View.VISIBLE);
        }
    }

    public Integer getHuidigeSprite(int indexHaarKleur, int indexOogKleur) {
        return sarahSprite[indexHaarKleur][indexOogKleur];
    }

    public int getHaarKleuren() {
        return haarKleuren;
    }

    public int getOogKleuren() {
        return oogKleuren;
    }
}
