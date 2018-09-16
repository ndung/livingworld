package com.livingworld.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.livingworld.R;
import com.livingworld.service.model.ServiceModel;
import com.livingworld.ui.HomeActivity;
import com.livingworld.ui.InboxActivity;

import org.json.JSONObject;

import java.util.Map;


/**
 * Created by Dizzay on 12/28/2017.
 */

public class MessageService extends FirebaseMessagingService {

    private final String TAG = this.getClass().getName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String from = remoteMessage.getFrom();
        Map bundle = remoteMessage.getData();

        String title = (String) bundle.get("title");
        String data = (String) bundle.get("data");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "title: " + title);
        Log.d(TAG, "data: " + data);
        sendNotification(title, data);

    }

    public void sendNotification(String title, String message) {

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Intent notificationIntent = new Intent(getApplicationContext(), InboxActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        notificationIntent.setAction(Static.BC_MESSAGE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 2392, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(message);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setTicker(title)
                        .setWhen(0)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentIntent(contentIntent)
                        .setStyle(inboxStyle)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(largeIcon)
                        //.setContentTitle(getResources().getString(R.string.app_name))
                        .setContentText(message);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(2392, mBuilder.build());
    }

}