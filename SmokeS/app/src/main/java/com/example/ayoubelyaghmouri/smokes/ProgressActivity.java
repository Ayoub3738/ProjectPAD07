package com.example.ayoubelyaghmouri.smokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;
import com.example.ayoubelyaghmouri.smokes.models.Status;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgressActivity extends AppCompatActivity {

    private TextView txtBespaard;
    private TextView txtAantalNietGerookteSigaretten;
    private TextView txtStreak;
    private TextView txtRookvrij;

    private DatabaseHelper myDb;
    private Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        txtBespaard = (TextView)findViewById(R.id.textView2);
        txtAantalNietGerookteSigaretten = (TextView)findViewById(R.id.txtAantalNietGerookt);
        txtStreak = (TextView)findViewById(R.id.txtStreak);
        txtRookvrij = (TextView)findViewById(R.id.txtRookvrij);


        myDb = new DatabaseHelper(this);
        status = Status.getStatus(myDb);
        showData();

    }

    public void showData() {
        if(status == null)
            return;


        txtBespaard.setText(String.format("€ %.2f", status.berekenBesparingenPak()));
        txtAantalNietGerookteSigaretten.setText(status.getNietGerookteSigaretten() + "");
        txtStreak.setText(status.getStreak() + " Sigaretten");

        Date datumNu = new Date();
        txtRookvrij.setText(berekenDatumVerschil(status.getLaatstGerookt(), datumNu));
    }

    public String berekenDatumVerschil(Date date1, Date date2) {
        //deze berekening komt van: http://stackoverflow.com/questions/21285161/android-difference-between-two-dates

        //milliseconds
        long different = date2.getTime() - date1.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;

        return String.format("%d:%02d:%02d", elapsedDays, elapsedHours, elapsedMinutes);
    }
}
