package com.example.ayoubelyaghmouri.smokes;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SelectTimeActivity extends AppCompatActivity {

    private Button btnSelectTime;
    private Button btnSlaOp;
    private EditText tTime;
    private int dag, maand, jaar, uur, minuten;
    private TimeZone timeZone = TimeZone.getTimeZone("Europe/Amsterdam");
    private Calendar calendar = GregorianCalendar.getInstance(timeZone);
    private ArrayList<String> selectedTimesList = new ArrayList<>();
    private ArrayList<String> weekdays = new ArrayList<>();
    private ArrayAdapter adapterDays;
    private ListView listViewTimes;
    private Spinner spinDays;
    public Intent notificationIntent = new Intent(SelectTimeActivity.this, MainActivity.class);
//    private AlarmManager alarmManager;
//    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        int currHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currMin = calendar.get(Calendar.MINUTE);

//        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, MainActivity.class);
//        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);


        String stringCurrHour = String.format("%02d", currHour);
        String stringCurrMin = String.format("%02d", currMin);


        listViewTimes = (ListView) findViewById(R.id.lGeselecteerdeTijden);

        btnSlaOp = (Button) findViewById(R.id.btnSlaOp);
        btnSelectTime = (Button) findViewById(R.id.btnSelectTime);
        tTime = (EditText) findViewById(R.id.tTime);

        tTime.setText(stringCurrHour + " : " + stringCurrMin);

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                uur = calendar.get(Calendar.HOUR_OF_DAY);
                minuten = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(SelectTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tTime.setText(hourOfDay + " : " + minute);
                    }
                }, uur, minuten, true);
                timePickerDialog.show();
            }
        });

        spinDays = (Spinner) findViewById(R.id.spinDays);
        fillArrayWeekDays(weekdays);
        adapterDays = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weekdays);
        spinDays.setAdapter(adapterDays);



        btnSlaOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTimesList.add(tTime.getText().toString());
                int selectedDay = (int) spinDays.getSelectedItemId();

                switch (selectedDay){
                    case 0:
                        btnSlaOp.setText("Maandag");
                        break;
                    case 1:
                        btnSlaOp.setText("Dinsdag");
                        break;
                    case 2:
                        btnSlaOp.setText("Woensdag");
                        break;
                    case 3:
                        btnSlaOp.setText("Donderdag");
                        break;
                    case 4:
                        btnSlaOp.setText("Vrijdag");
                        break;
                    case 5:
                        btnSlaOp.setText("Zaterdag");
                        break;
                    case 6:
                        btnSlaOp.setText("Zondag");
                        break;
                    case 7:
                        btnSlaOp.setText("Dagelijks");
                        alarmMethod2();
                        break;

                }
            }
        });

    }

    public void fillArrayWeekDays(ArrayList arrayList){
        arrayList.add("Maandag");
        arrayList.add("Dinsdag");
        arrayList.add("Woensdag");
        arrayList.add("Donderdag");
        arrayList.add("Vrijdag");
        arrayList.add("Zaterdag");
        arrayList.add("Zondag");
        arrayList.add("Dagelijks");
    }

    private void alarmMethod(){
        Intent myIntent = new Intent(this, NotificationReciever.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 51);
//        calendar.set(Calendar.AM_PM, Calendar.AM);
//        calendar.add(Calendar.DAY_OF_MONTH, 1);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);

        Toast.makeText(SelectTimeActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();
    }

    private void alarmMethod2(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 24);
        Intent intent = new Intent(getApplicationContext(), NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(SelectTimeActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();
    }
}
