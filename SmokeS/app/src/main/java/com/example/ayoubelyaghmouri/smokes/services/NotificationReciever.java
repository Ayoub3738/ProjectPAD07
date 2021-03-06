package com.example.ayoubelyaghmouri.smokes.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.ayoubelyaghmouri.smokes.MainActivity;
import com.example.ayoubelyaghmouri.smokes.R;

/**
 * Created by Ayoub el Yaghmouri on 15-5-2017.
 */

public class NotificationReciever extends BroadcastReceiver {

    /**
     * Deze methode zorgt ervoor dat wanneer er lokaal een notificatiesignaal komt, de notificatie
     * wordt getriggert.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        String signaal = "Ping";
        notificationIntent.putExtra("signaal", signaal);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_statusbar_smokeless_sarah)
                .setContentTitle("Sarah wilt roken")
                .setContentText("Help Sarah met haar rookkeuze. "+ intent.getStringExtra("time"))
                .addAction(0, "Help Sarah", pendingIntent)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

    }
}
