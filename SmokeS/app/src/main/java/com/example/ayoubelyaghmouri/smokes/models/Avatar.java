package com.example.ayoubelyaghmouri.smokes.models;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.ayoubelyaghmouri.smokes.MainActivity;
import com.example.ayoubelyaghmouri.smokes.R;

public class Avatar extends MainActivity {

    private ImageSwitcher imageSwitcherAvatar;
    private Integer[] images = {R.drawable.womancartooncharacterfull, R.drawable.womancartooncharacterfullsad};
    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

//        imageSwitcherAvatar = (ImageSwitcher) findViewById(R.id.imageSwitcherAvatar);
//        imageSwitcherAvatar.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
//                ImageView imageView = new ImageView(getApplicationContext());
//                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
//                return imageView;
//            }
//        });
//
//        Animation animIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
//        Animation animOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);
//
//        imageSwitcherAvatar.setInAnimation(animIn);
//        imageSwitcherAvatar.setOutAnimation(animOut);
//        imageSwitcherAvatar.setImageResource(R.drawable.womancartooncharacterfull);
    }

//    protected void prevImage(){
//        j -= 1;
//        imageSwitcherAvatar.setImageResource(images[j]);
//    }
//
//    protected void nextImage(){
//        j += 1;
//        imageSwitcherAvatar.setImageResource(images[j]);
//    }
}
