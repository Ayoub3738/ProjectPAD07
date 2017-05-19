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

import com.example.ayoubelyaghmouri.smokes.R;
import com.example.ayoubelyaghmouri.smokes.SelectTimeActivity;
import com.example.ayoubelyaghmouri.smokes.SettingsActivity;

public class AvatarTest extends AppCompatActivity {

    private ImageButton btnNextImage;
    private ImageButton btnPrevImage;
    private Button btnHaarkleur;
    private Button btnOogkleur;
    private ImageSwitcher imageSwitcherAvatar;
    private Integer[] imagesHair = {R.drawable.womancartooncharacterfullbrownblue,R.drawable.womancartooncharacterfullbrownbrown,R.drawable.womancartooncharacterfullbrowngreen,
                                    R.drawable.womancartooncharacterfullblondblue, R.drawable.womancartooncharacterfullblondbrown, R.drawable.womancartooncharacterfullblondgreen};
    private Integer[] imagesEyes = {R.drawable.womancartooncharacterfullbrownblue,R.drawable.womancartooncharacterfullbrownbrown,R.drawable.womancartooncharacterfullbrowngreen,
                                    R.drawable.womancartooncharacterfullblondblue, R.drawable.womancartooncharacterfullblondbrown, R.drawable.womancartooncharacterfullblondgreen};
    private Integer[] images = imagesHair;

    private int spriteSarah = R.drawable.womancartooncharacterfull;

    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_test);

        onBtnNext();
        onBtnPrev();
        onBtnHair();
        onBtnEyes();

        imageSwitcherAvatar = (ImageSwitcher) findViewById(R.id.imageSwitcherAvatar);

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

            imageSwitcherAvatar.setImageResource(imagesHair[j]);
        }
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

            imageSwitcherAvatar.setImageResource(imagesHair[j]);
        }
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

            imageSwitcherAvatar.setImageResource(imagesEyes[j]);
        }
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

            imageSwitcherAvatar.setImageResource(imagesEyes[j]);
        }
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

}
