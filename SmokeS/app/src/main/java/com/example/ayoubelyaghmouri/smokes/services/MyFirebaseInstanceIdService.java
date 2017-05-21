package com.example.ayoubelyaghmouri.smokes.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by Ayoub el Yaghmouri on 2-5-2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String REG_TOKEN = "REG_TOKEN";

    /**
     * Dit haalt de speciale code op voor de app voor de firebase service.
     */
    @Override
    public void onTokenRefresh() {
        String recentToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, recentToken);
        Log.e(TAG, "Refreshed token: " + recentToken);
    }
}
