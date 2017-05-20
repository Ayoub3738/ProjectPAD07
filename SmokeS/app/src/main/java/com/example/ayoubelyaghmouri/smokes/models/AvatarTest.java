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

public class AvatarTest extends AppCompatActivity {

    private DatabaseHelper myDb;
    private ImageButton btnNextImage;
    private ImageButton btnPrevImage;
    private Button btnHaarkleur;
    private Button btnOogkleur;
    private Button btnOpslaan;
    private ImageSwitcher imageSwitcherAvatar;
    private Integer[] imagesHair = {R.drawable.womancartooncharacterfullbrownblue,R.drawable.womancartooncharacterfullbrownbrown,R.drawable.womancartooncharacterfullbrowngreen,
                                    R.drawable.womancartooncharacterfullblondblue, R.drawable.womancartooncharacterfullblondbrown, R.drawable.womancartooncharacterfullblondgreen};
    private Integer[] imagesEyes = {R.drawable.womancartooncharacterfullbrownblue,R.drawable.womancartooncharacterfullbrownbrown,R.drawable.womancartooncharacterfullbrowngreen,
                                    R.drawable.womancartooncharacterfullblondblue, R.drawable.womancartooncharacterfullblondbrown, R.drawable.womancartooncharacterfullblondgreen};
    private Integer[] images = imagesHair;
    private int j = 1;
    public int spriteSarah  = getImages(getJ());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_test);
        myDb = new DatabaseHelper(this);

        onBtnNext();
        onBtnPrev();
        onBtnHair();
        onBtnEyes();
        onBtnOpslaan();

        Animation animIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
        Animation animOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);

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

    public void onBtnHair() {
        btnHaarkleur = (Button) findViewById(R.id.btnHaarkleur);
        btnHaarkleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               images = imagesHair;
            }
        });
    }

    public void onBtnEyes() {
        btnOogkleur = (Button) findViewById(R.id.btnOogkleur);
        btnOogkleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               images = imagesEyes;
            }
        });
    }

    public void onBtnOpslaan() {
        btnOpslaan = (Button) findViewById(R.id.btnSlaAvatarOp);
        btnOpslaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.imageSwitcher.setImageResource(getImages(getJ()));
            }
        });
    }

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
