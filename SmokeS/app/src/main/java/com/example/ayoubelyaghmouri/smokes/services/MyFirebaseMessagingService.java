package com.example.ayoubelyaghmouri.smokes.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.ayoubelyaghmouri.smokes.MainActivity;
import com.example.ayoubelyaghmouri.smokes.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Ayoub el Yaghmouri on 2-5-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    /**
     * Deze methode zorgt ervoor dat wanneer online er een notificatiesignaal komt, de notificatie
     * wordt getriggert.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        String signaal = "Ping";
        notificationIntent.putExtra("signaal", signaal);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_statusbar_smokeless_sarah)
                .setContentTitle("FCM NOTIFICATION")
                .setContentText(remoteMessage.getNotification().getBody())
                .addAction(0, "Help Sarah", pendingIntent)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
