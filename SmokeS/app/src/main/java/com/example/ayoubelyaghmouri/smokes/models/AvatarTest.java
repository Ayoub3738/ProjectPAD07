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
    private ImageSwitcher imageSwitcherAvatar;
    private Integer[] images = {R.drawable.womancartooncharacterfull, R.drawable.womancartooncharacterfullsad, R.drawable.womancartooncharacter};
    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_test);

        onBtnNext();
        onBtnPrev();

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

        imageSwitcherAvatar.setImageResource(R.drawable.womancartooncharacterfull);
    }


    public void prevImage(){
        j -= 1;
        if (j < 0){
            j = 2;
        }

        imageSwitcherAvatar.setImageResource(images[j]);
    }

    public void nextImage(){
        j += 1;
        if (j > 2){
            j = 0;
        }
        imageSwitcherAvatar.setImageResource(images[j]);
    }


    public void onBtnNext() {
        btnNextImage = (ImageButton) findViewById(R.id.btnImageNext);
        btnNextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prevImage();
            }
        });
    }

    public void onBtnPrev() {
        btnNextImage = (ImageButton) findViewById(R.id.btnImagePrev);
        btnNextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextImage();
            }
        });
    }

}
