package com.example.ayoubelyaghmouri.smokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SelectTimeActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button btnTime;
    private TextView tSelectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        showTime();
    }

    public void showTime(){
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        btnTime = (Button) findViewById(R.id.btnShowTime);
        tSelectedTime = (TextView) findViewById(R.id.tSelectedTime);

    }

    public void drukOpKnop(View view) {
        tSelectedTime.setText("Geselecteerde tijd: " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());
    }

}
