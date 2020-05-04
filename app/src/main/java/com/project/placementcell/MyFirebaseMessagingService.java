package com.project.placementcell;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    static List<String> listData;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("mylog", "From: " + remoteMessage.getFrom());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("mylog", "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("mylog", "Body: " + remoteMessage.getNotification().getBody());
            Log.d("mylog", "Title: " + remoteMessage.getNotification().getTitle());
        }
        listData = new ArrayList<>();
        listData.add( remoteMessage.getData().get("message"));
        String message = remoteMessage.getData().get("message");
        Log.d("mylog", "Message Payload: " + message);

        Intent intent = new Intent(getApplicationContext(), Notification.class);
        intent.putExtra("message", remoteMessage.getNotification().getBody());
        intent.putExtra("title", remoteMessage.getNotification().getTitle());

        getApplicationContext().startActivity(intent);


    }
}