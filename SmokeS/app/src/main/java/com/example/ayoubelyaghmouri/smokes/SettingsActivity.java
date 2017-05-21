package com.example.ayoubelyaghmouri.smokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ayoubelyaghmouri.smokes.models.AvatarTest;

/**
 * instellingen scherm, hierop kun je navigeren naar verschillende instellingen in de app
 */

public class SettingsActivity extends AppCompatActivity {

    private Button btnSelectTime;
    private Button btnAlgemeen;
    private Button btnAvatarCustomisation;

    /**
     * de init van het instellingenscherm
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //wijst de knoppen toe + maakt click events
        onBtnSelectTimeClick();
        onBtnAvatarCustomisationClick();
        onBtnSAlgemeenClick();
    }

    /**
     * als je op deze knop drukt toont hij het tijden selecteer scherm
     */
    public void onBtnSelectTimeClick() {
        btnSelectTime = (Button) findViewById(R.id.btnSelectTime);
        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, SelectTimeActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * als je op deze knop drukt, toont hij de algemene instellingen
     */
    public void onBtnSAlgemeenClick() {
        btnAlgemeen = (Button) findViewById(R.id.btnAlgemeen);
        btnAlgemeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, BesparingenOverzichtActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * als je op deze knop drukt, dan toont hij het customization scherm
     */
    public void onBtnAvatarCustomisationClick() {
        btnAvatarCustomisation = (Button) findViewById(R.id.btnAvatarCustomisation);
        btnAvatarCustomisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsActivity.this, AvatarTest.class);
                startActivity(intent);
            }
        });
    }
}
