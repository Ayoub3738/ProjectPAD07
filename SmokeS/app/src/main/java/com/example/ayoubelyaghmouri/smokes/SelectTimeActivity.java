package com.example.ayoubelyaghmouri.smokes;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
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
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SelectTimeActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private Button btnSelectTime;
    private Button btnSlaOp;
    private TextView tTime;
    private int uur, minuten, selectedUur, selectedMinute, futurealarmtimeInMs;
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


    /**
     * init van select time activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        //wijst dbhelper toe
        myDb = new DatabaseHelper(this);

        //haalt uren en minuten van de calender
        int currHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currMin = calendar.get(Calendar.MINUTE);

        //variabelen voor uren en minuten getrimt op 2 decimalen
        String stringCurrHour = String.format("%02d", currHour);
        String stringCurrMin = String.format("%02d", currMin);

        //wijst lijst met tijden, opslaanknoppen en label/textview toe
        listViewTimes = (ListView) findViewById(R.id.lGeselecteerdeTijden);

        btnSlaOp = (Button) findViewById(R.id.btnSlaOp);
        btnSelectTime = (Button) findViewById(R.id.btnSelectTime);
        tTime = (TextView) findViewById(R.id.tTime);

        tTime.setText(stringCurrHour + " : " + stringCurrMin);
        setButtonDisabled(); //disabled opslaanknop

        //als je op selecttime knop drukt dan...
        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //maakt ie een calender en haalt ie uren en minuten op
                calendar = GregorianCalendar.getInstance();
                uur = calendar.get(GregorianCalendar.HOUR_OF_DAY);
                minuten = calendar.get(GregorianCalendar.MINUTE);

                //als je iets selecteerd op de timepicker dan..
                TimePickerDialog timePickerDialog = new TimePickerDialog(SelectTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //weergeeft ie de tijd in een textview weer
                        tTime.setText(hourOfDay + " : " + minute);
                        selectedUur = hourOfDay;
                        selectedMinute = minute;
//                        Calendar c = GregorianCalendar.getInstance(timeZone);
//                        long msNow = c.getTimeInMillis();
//                        c.set(Calendar.HOUR, hourOfDay);x
//                        c.set(Calendar.MINUTE, minute);

//                        futurealarmtimeInMs = (int) (c.getTimeInMillis() - msNow);//calendar.getTimeInMillis());
                        //enabled opslaan knop
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

        //als je op opslaan drukt dan...
        btnSlaOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //voegt ie de tijd aan een lijst toe
                selectedTimesList.add(tTime.getText().toString());
                int selectedDay = (int) spinDays.getSelectedItemId();

                //deze switch was er eerst om een bepaalde dag zoals ma, di, wo op te slaan en ze te onderscheiden...
                switch (selectedDay){
                    case 0:
                        calendar.getInstance();
                        calendar.set(GregorianCalendar.HOUR_OF_DAY, uur);
                        calendar.set(GregorianCalendar.MINUTE, minuten);

                        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(SelectTimeActivity.this, NotificationReciever.class);
                        intent.putExtra("time", uur + ":" + minuten);

                        PendingIntent alarmIntent = PendingIntent.getBroadcast(SelectTimeActivity.this, 0, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                                calendar.getTimeInMillis(), (24 * 1000 * 60 * 60),
                                alarmIntent); //every 24 hours

                        Toast.makeText(SelectTimeActivity.this, "Alarm gestart " + calendar.getTimeInMillis(), Toast.LENGTH_LONG).show();
                        Tijd insertTijd = new Tijd(selectedUur, selectedMinute);
                        insertTijd.insert(myDb);
                        break;
                }
                //gooit de knop weer op disabled
                setButtonDisabled();
                haalTijdenOp();
            }
        });

        //haalt de tijden op en stopt ze in listview
        haalTijdenOp();
    }

    /**
     * deze word gebruikt om een dropdown te vullen
     * @param arrayList de values van de dropdown
     */
    public void fillArrayWeekDays(ArrayList<String> arrayList){
        arrayList.add("Dagelijks");
    }

    /**
     * Een method om de notificatie te sturen op de bepaalde tijd die is ingevoerd
     * @param uur uren
     * @param minuten minuten
     */
    private void handleNotification(int uur, int minuten) {
        calendar.getInstance();
        calendar.set(GregorianCalendar.HOUR_OF_DAY, uur);
        calendar.set(GregorianCalendar.MINUTE, minuten);

        AlarmManager alarmMgr = (AlarmManager) this
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReciever.class);
        intent.putExtra("time", uur + ":" + minuten);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), (24 * 1000 * 60 * 60),
                alarmIntent); //every 24 hours

        Toast.makeText(SelectTimeActivity.this, "Alarm gestart " + calendar.getTimeInMillis(), Toast.LENGTH_LONG).show();

    }

    /**
     * haalt de tijden op en toont ze gesorteerd op het scherm (stopt ze in een listview)
     */
    public void haalTijdenOp(){
        //haalt de tijden op uit de database
        tijden = Tijd.getTijden(myDb);
        List<String> test = new ArrayList<>();

        //haalt voor elke tijd de uren en minuten en gooit ze in een lijst
        for (Tijd tijd : tijden) {
            uur = tijd.getUur();
            minuten = tijd.getMinuten();
            test.add(uur + " : " + minuten);
        }

        //zegt dat de listview deze lijst moet gebruiken
        listViewTimes.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test)
        );

        //als je op een item uit de listview klikt, verwijder hem dan
        listViewTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteTimeFromList(tijden.get(position));
            }
        });


    }

    /**
     * verwijdert een tijd
     * @param tijd de tijd die je wil verwijderen
     */
    public void deleteTimeFromList(final Tijd tijd){
        //toont een melding of je zeker weet of je deze tijd wil verwijderen
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Verwijderen")
                .setMessage("Weet u zeker dat u " + tijd.getUur() + " : " + tijd.getMinuten() + " wilt verwijderen?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //verwijdert de tijd en herlaad de lijst met tijden
                        tijd.delete(myDb);
                        haalTijdenOp();
                    }
                })
                .setNegativeButton("Annuleer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //herlaad de lijst met tijden
                        haalTijdenOp();
                    }
                })
                .create();
        alert.show();
    }

    /**
     * disabled opslaan knop
     */
    public void setButtonDisabled(){
        btnSlaOp.setEnabled(false);
        btnSlaOp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled)));

    }

    /**
     * enabled opslaan knop
     */
    public void setButtonEnabled(){
        btnSlaOp.setEnabled(true);
        btnSlaOp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }
}
