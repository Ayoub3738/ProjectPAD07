package com.example.ayoubelyaghmouri.smokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;
import com.example.ayoubelyaghmouri.smokes.models.Status;

public class ProgressActivity extends AppCompatActivity {

    private TextView txtBespaard;
    private TextView txtAantalNietGerookteSigaretten;
    private TextView txtStreak;

    private DatabaseHelper myDb;
    private Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        txtBespaard = (TextView)findViewById(R.id.textView2);
        txtAantalNietGerookteSigaretten = (TextView)findViewById(R.id.txtAantalNietGerookt);
        txtStreak = (TextView)findViewById(R.id.txtStreak);


        myDb = new DatabaseHelper(this);
        status = myDb.getUser();
        showData();

    }

    public void showData() {
        if(status == null)
            return;


        txtBespaard.setText(String.format("€ %.2f", status.berekenBesparingenPak()));
        txtAantalNietGerookteSigaretten.setText(status.getNietGerookteSigaretten() + "");
        txtStreak.setText(status.getStreak() + " Sigaretten");
    }
}
