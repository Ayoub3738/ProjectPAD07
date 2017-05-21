package com.example.ayoubelyaghmouri.smokes.models;

import android.content.Intent;
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
    //image switcher
    private ImageSwitcher imageSwitcherAvatar;
    //de plaatjes opgeslagen in een integer
    private Integer[] imagesHair = {R.drawable.womancartooncharacterfullbrownblue,R.drawable.womancartooncharacterfullbrownbrown,R.drawable.womancartooncharacterfullbrowngreen,
                                    R.drawable.womancartooncharacterfullblondblue, R.drawable.womancartooncharacterfullblondbrown, R.drawable.womancartooncharacterfullblondgreen};
    private Integer[] imagesEyes = {R.drawable.womancartooncharacterfullbrownblue,R.drawable.womancartooncharacterfullbrownbrown,R.drawable.womancartooncharacterfullbrowngreen,
                                    R.drawable.womancartooncharacterfullblondblue, R.drawable.womancartooncharacterfullblondbrown, R.drawable.womancartooncharacterfullblondgreen};
    private Integer[] images = imagesHair;
    private int j = 1;
    public int spriteSarah  = getImages(getJ());

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

        spriteSarah  = getImages(getJ());
        btnOpslaan.setVisibility(View.INVISIBLE);
        imageSwitcherAvatar.setImageResource(spriteSarah);
    }


    /**
     * veranderd naar vorige haar in de lijst
     */
    public void prevImageHair(){
        if (images == imagesHair) {
            if (j == 0 || j == 3) {
                j -= 3;

                if (j < 0) {
                    j = 3;
                }
            }

            if (j == 1 || j == 4) {
                j -= 3;

                if (j < 1) {
                    j = 4;
                }
            }

            if (j == 2 || j == 5) {
                j -= 3;

                if (j < 2) {
                    j = 5;
                }
            }
            spriteSarah = imagesHair[j];
        }
        imageSwitcherAvatar.setImageResource(imagesHair[j]);
        Update();
    }

    /**
     * veranderd naar volgende haar in de lijst
     */
    public void nextImageHair(){

        if (images == imagesHair) {
            if (j == 0 || j == 3) {
                j += 3;

                if (j > 3) {
                    j = 0;
                }
            }

            if (j == 1 || j == 4) {
                j += 3;

                if (j > 4) {
                    j = 1;
                }
            }

            if (j == 2 || j == 5) {
                j += 3;

                if (j > 5) {
                    j = 2;
                }
            }
            spriteSarah = imagesHair[j];
        }
        imageSwitcherAvatar.setImageResource(imagesHair[j]);
        Update();
    }

    /**
     * veranderd naar vorige ogen in de lijst
     */
    public void prevImageEyes(){
        if (images == imagesEyes) {

            if (j == 0 || j == 1 || j == 2) {
                j -= 1;

                if (j < 0) {
                    j = 2;
                }
            }

            if (j == 3 || j == 4 || j == 5) {
                j -= 1;

                if (j < 3) {
                    j = 5;
                }
            }
            spriteSarah = imagesHair[j];
        }
        imageSwitcherAvatar.setImageResource(imagesEyes[j]);
        Update();
    }

    /**
     * veranderd naar volgende ogen in de lijst
     */
    public void nextImageEyes(){

        if (images == imagesEyes) {
            if (j == 0 || j == 1 || j == 2) {
                j += 1;

                if (j > 2) {
                    j = 0;
                }
            }

            if (j == 3 || j == 4 || j == 5) {
                j += 1;

                if (j > 5) {
                    j = 3;
                }
            }
            spriteSarah = imagesHair[j];
        }
        imageSwitcherAvatar.setImageResource(imagesEyes[j]);
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

                if (images == imagesHair){
                    nextImageHair();
                }

                if (images == imagesEyes){
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

                if (images == imagesHair){
                    prevImageHair();
                }

                if (images == imagesEyes){
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
               images = imagesHair;
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

               images = imagesEyes;
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
                MainActivity.imageSwitcher.setImageResource(getImages(getJ()));
            }
        });
    }

    /**
     * dit is een knop die alles update
     */
    public void Update(){
        if (spriteSarah != R.drawable.womancartooncharacterfull){
            btnOpslaan.setVisibility(View.VISIBLE);
        }
    }

    public Integer getImages(int index) {
        return images[index];
    }

    public int getJ() {
        return j;
    }
}
