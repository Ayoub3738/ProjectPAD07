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
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SelectTimeActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private Button btnSelectTime;
    private Button btnSlaOp;
    private EditText tTime;
    private int uur, minuten, selectedUur, selectedMinute;
    private TimeZone timeZone = TimeZone.getTimeZone("Europe/Amsterdam");
    private Calendar calendar = GregorianCalendar.getInstance(timeZone);
    private ArrayList<String> selectedTimesList = new ArrayList<>();
    private ArrayList<String> weekdays = new ArrayList<>();
    private ArrayAdapter adapterDays;
    private ArrayAdapter adapterTimes;
    private ListView listViewTimes;
    private Spinner spinDays;
    private ArrayList<Pair<Integer, Integer>> tijden;

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
                        selectedUur = hourOfDay;
                        selectedMinute = minute;
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
                        btnSlaOp.setText("Dagelijks");
                        alarmMethod2();
                        myDb.insertTijd(selectedUur, selectedMinute);
                        break;

                }

                haalTijdenOp();
            }
        });

        haalTijdenOp();

    }

    public void fillArrayWeekDays(ArrayList arrayList){
        arrayList.add("Dagelijks");
    }

    public void alarmMethod(){
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

    public void alarmMethod2(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 24);
        Intent intent = new Intent(getApplicationContext(), NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(SelectTimeActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();
    }



    public void haalTijdenOp(){
        tijden = myDb.getTijden();
        List<String> test = new ArrayList<>();

        for (Pair<Integer, Integer> tijd : tijden) {
            uur = tijd.first;
            minuten = tijd.second;
            //adapterTimes = new ArrayAdapter<Integer>(this,)

            test.add(uur + " : " + minuten);
        }

        listViewTimes.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test)
        );

        listViewTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


}
