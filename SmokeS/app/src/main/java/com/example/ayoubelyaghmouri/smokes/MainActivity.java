package com.example.ayoubelyaghmouri.smokes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.ayoubelyaghmouri.smokes.models.AvatarTest;
import com.example.ayoubelyaghmouri.smokes.models.Character;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;
import com.example.ayoubelyaghmouri.smokes.models.Status;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tGebruiker;
    private TextView tvNavBarNaam;
    private TextView tvNavBarMail;
    private String gebruikersNaam = " ";
    DatabaseHelper myDb;
    private int aantalSigaretten = 1;
    private Integer[] images = {R.drawable.womancartooncharacterfull, R.drawable.womancartooncharacterfullsad};
    private int i = 0;
    public static ImageSwitcher imageSwitcher;
    public int spriteSarah = R.drawable.womancartooncharacterfull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        myDb = new DatabaseHelper(this);

        tGebruiker = (TextView)findViewById(R.id.tGebruiker);
        tvNavBarNaam = (TextView) headerView.findViewById(R.id.tvNavBarNaam);
        tvNavBarMail = (TextView) headerView.findViewById(R.id.tvNavBarMail);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcherHome);

        Character character = Character.getCharacter(myDb);
        gebruikersNaam = character.getUserNaam();

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
                return imageView;
            }
        });

        makeFloatButtons();

        Animation animIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
        Animation animOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);

        imageSwitcher.setInAnimation(animIn);
        imageSwitcher.setOutAnimation(animOut);
        imageSwitcher.setImageResource(spriteSarah);
        tGebruiker.setAnimation(animIn);
        tGebruiker.setText("Hey " + gebruikersNaam + " !");
        tvNavBarNaam.setText(gebruikersNaam);
        tvNavBarMail.setText(gebruikersNaam + "@hva.nl");

        String signaal = "Ping";

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
//            String melding = bundle.getString("signaal");
//            tGebruiker.setText(melding + " " + signaal);
                showAlert();
//
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void makeFloatButtons(){
        Animation animInFloatDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_float_down);
        Animation animInFloatCenterLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_float_center_left);
        Animation animInFloatCenterRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_float_center_right);

        FloatingActionButton btnFloatSettings = (FloatingActionButton) findViewById(R.id.btnFloatSettings);
        btnFloatSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        FloatingActionButton btnFloatProgress = (FloatingActionButton) findViewById(R.id.btnFloatProgress );
        btnFloatProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statusIntent = new Intent(MainActivity.this, ProgressActivity.class);
                startActivity(statusIntent);
            }
        });

        FloatingActionButton btnFloatAchievements = (FloatingActionButton) findViewById(R.id.btnFloatAchievements);
        btnFloatAchievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent achievementsIntent = new Intent(MainActivity.this, AchievementActivity.class);
                startActivity(achievementsIntent);
            }
        });

        FloatingActionButton btnFloatMelding = (FloatingActionButton) findViewById(R.id.btnFloatMelding);
        btnFloatMelding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });

        FloatingActionButton btnFloatNotification = (FloatingActionButton) findViewById(R.id.btnFloatNotification);
        btnFloatNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

        btnFloatSettings.setAnimation(animInFloatDown);
        btnFloatProgress.setAnimation(animInFloatDown);
        btnFloatAchievements.setAnimation(animInFloatDown);
        btnFloatMelding.setAnimation(animInFloatCenterLeft);
        btnFloatNotification.setAnimation(animInFloatCenterRight);
    }

    public void showAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Rookmelding!")
                .setMessage("Wilt u Sarah een sigaret laten roken?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //haalt gegevens van user uit database
                        Status newStatus = Status.getStatus(myDb);

                        //reset streak, voegt 1 toe bij aantalmeldingen
                        newStatus.setStreak(0);
                        newStatus.setAantalMeldingen(newStatus.getAantalMeldingen() +1);
                        Date datumNu = new Date();
                        newStatus.setLaatstGerookt(datumNu);

                        //update database met nieuwe gegevens
                        newStatus.update(myDb);

                        //melding oeps
                        tGebruiker.setTextSize(26);
                        tGebruiker.setText("Ik heb spijt dat ik gerookt heb.");
                        i = 1;
                        imageSwitcher.setImageResource(images[i]);
                    }
                })
                .setNegativeButton("Nee", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //haalt gegevens van user uit database
                        Status newStatus = Status.getStatus(myDb);

                        //voegt 1 toe bij streak en bij aantalmeldingen en niet gerooktesigaretten
                        newStatus.setStreak(newStatus.getStreak() +1);
                        newStatus.setAantalMeldingen(newStatus.getAantalMeldingen() +1);
                        newStatus.setNietGerookteSigaretten(newStatus.getNietGerookteSigaretten() +1);

                        if (newStatus.getStreak() > newStatus.getRecordStreak()) {
                            newStatus.setRecordStreak(newStatus.getStreak());
                        }

                        //update database met nieuwe gegevens
                        newStatus.update(myDb);

                        //melding goedzo :D
                        tGebruiker.setText("Ik voel me een stuk beter!");
                        i = 0;
                        imageSwitcher.setImageResource(images[i]);
                    }
                })
                .create();
        alert.show();
    }

    public void showAlertBtn(View v) {
        showAlert();
    }

    public void showNotification(){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        //Beiden triggeren, zowel alert als notificatie.
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(getBaseContext())
                .setSmallIcon(R.drawable.ic_statusbar_smokeless_sarah)
                .setContentTitle("Sarah wil gaan roken")
                .setContentText("Help Sarah bij haar rookkeuze.")
                .addAction(0, "Help Sarah", pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(10, notification);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action 
        } else if (id == R.id.nav_status) {
            Intent statusIntent = new Intent(MainActivity.this, ProgressActivity.class);
            startActivity(statusIntent);
        } else if (id == R.id.nav_achievements) {
            Intent achievementIntent = new Intent(MainActivity.this, AchievementActivity.class);
            startActivity(achievementIntent);
        } else if (id == R.id.nav_settings) {
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
