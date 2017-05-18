package com.example.ayoubelyaghmouri.smokes;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ayoubelyaghmouri.smokes.models.Tijd;
import com.example.ayoubelyaghmouri.smokes.services.DatabaseHelper;
import com.example.ayoubelyaghmouri.smokes.services.NotificationReciever;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SelectTimeActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private Button btnSelectTime;
    private Button btnSlaOp;
    private TextView tTime;
    private int uur, minuten, selectedUur, selectedMinute;
    private TimeZone timeZone = TimeZone.getTimeZone("Europe/Amsterdam");
    private Calendar calendar = GregorianCalendar.getInstance(timeZone);
    private ArrayList<String> selectedTimesList = new ArrayList<>();
    private ArrayList<String> weekdays = new ArrayList<>();
    private ArrayAdapter adapterDays;
    private ArrayAdapter adapterTimes;
    private ListView listViewTimes;
    private Spinner spinDays;
    private ArrayList<Tijd> tijden;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        myDb = new DatabaseHelper(this);

        int currHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currMin = calendar.get(Calendar.MINUTE);

        String stringCurrHour = String.format("%02d", currHour);
        String stringCurrMin = String.format("%02d", currMin);

        listViewTimes = (ListView) findViewById(R.id.lGeselecteerdeTijden);

        btnSlaOp = (Button) findViewById(R.id.btnSlaOp);
        btnSelectTime = (Button) findViewById(R.id.btnSelectTime);
        tTime = (TextView) findViewById(R.id.tTime);

        tTime.setText(stringCurrHour + " : " + stringCurrMin);
        setButtonDisabled();

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
                        selectedUur = hourOfDay;
                        selectedMinute = minute;
                        setButtonEnabled();

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
//                        registerAlarm();
                        registerAlarm(selectedUur, selectedMinute);
                        Tijd insertTijd = new Tijd(selectedUur, selectedMinute);
                        insertTijd.insert(myDb);
                        break;
                }
                setButtonDisabled();
                haalTijdenOp();
            }
        });
        haalTijdenOp();
    }

    public void fillArrayWeekDays(ArrayList<String> arrayList){
        arrayList.add("Dagelijks");
    }

    // Register the alarm and set it at 7am everyday (repeating mode)
    public void registerAlarm(int uur, int minuten) {
        Intent myIntent = new Intent(this, NotificationReciever.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

    // Set the alarm to start at approximately 2:00 p.m.
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, uur);
        cal.set(Calendar.MINUTE, minuten);
        cal.set(Calendar.SECOND, 0);

    // With setInexactRepeating(), you have to use one of the AlarmManager interval
    // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(SelectTimeActivity.this, "Alarm gestart ", Toast.LENGTH_LONG).show();
    }

    private void handleNotification(int uur, int minuten) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, uur);
        cal.set(Calendar.MINUTE, minuten);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Intent alarmIntent = new Intent(this, NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                cal.getTimeInMillis(), alarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(SelectTimeActivity.this, "Alarm gestart ", Toast.LENGTH_LONG).show();

    }

    public void haalTijdenOp(){
        tijden = myDb.getTijden();
        List<String> test = new ArrayList<>();

        for (Tijd tijd : tijden) {
            uur = tijd.getUur();
            minuten = tijd.getMinuten();
            test.add(uur + " : " + minuten);
        }

        listViewTimes.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test)
        );

        listViewTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteTimeFromList(tijden.get(position));
            }
        });
    }


    public void deleteTimeFromList(final Tijd tijd){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Verwijderen")
                .setMessage("Weet u zeker dat u " + tijd.getUur() + " : " + tijd.getMinuten() + " wilt verwijderen?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tijd.delete(myDb);
                        haalTijdenOp();
                    }
                })
                .setNegativeButton("Annuleer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        haalTijdenOp();
                    }
                })
                .create();
        alert.show();
    }

    public void setButtonDisabled(){
        btnSlaOp.setEnabled(false);
        btnSlaOp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled)));

    }

    public void setButtonEnabled(){
        btnSlaOp.setEnabled(true);
        btnSlaOp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }
}
