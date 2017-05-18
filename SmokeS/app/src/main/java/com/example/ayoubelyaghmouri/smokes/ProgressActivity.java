package com.example.ayoubelyaghmouri.smokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ayoubelyaghmouri.smokes.models.Gezondheid;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;
import com.example.ayoubelyaghmouri.smokes.models.Status;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgressActivity extends AppCompatActivity {

    private TextView txtBesparingSigaret;
    private TextView txtGezondheid;
    private TextView txtBesparingPak;
    private TextView txtNietGerooktePakjes;
    private TextView txtAantalNietGerookteSigaretten;
    private TextView txtStreak;
    private TextView txtHighscoreStreak;
    private TextView txtRookvrij;

    private DatabaseHelper myDb;
    private Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        txtBesparingSigaret = (TextView)findViewById(R.id.textView2);
        txtGezondheid = (TextView)findViewById(R.id.txtSarahGezondheid);
        txtBesparingPak = (TextView)findViewById(R.id.txtPrijsPak);
        txtNietGerooktePakjes = (TextView)findViewById(R.id.txtNietGerooktePakjes);
        txtAantalNietGerookteSigaretten = (TextView)findViewById(R.id.txtAantalNietGerookt);
        txtStreak = (TextView)findViewById(R.id.txtStreak);
        txtHighscoreStreak = (TextView)findViewById(R.id.txtHighscore);
        txtRookvrij = (TextView)findViewById(R.id.txtRookvrij);


        myDb = new DatabaseHelper(this);
        status = Status.getStatus(myDb);
        showData();

    }

    public void showData() {
        if(status == null)
            return;

        Gezondheid gezondheid = status.getGezondheid();

        txtBesparingSigaret.setText(String.format("€ %.2f", status.berekenBesparingenSigaret()));
        txtGezondheid.setText(gezondheid.getTotaalGezondheid() + "%");
        txtBesparingPak.setText(String.format("€ %.2f", status.berekenBesparingenPak()));
        txtNietGerooktePakjes.setText(status.berekenAantalPak() + "");
        txtAantalNietGerookteSigaretten.setText(status.getNietGerookteSigaretten() + "");
        txtStreak.setText(status.getStreak() + " Sigaretten");
        txtHighscoreStreak.setText(status.getRecordStreak() + "");
        txtRookvrij.setText(status.berekenTijdNietGerookt());
    }
}
