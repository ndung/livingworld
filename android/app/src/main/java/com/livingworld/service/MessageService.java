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

import java.util.Map;


/**
 * Created by Dizzay on 12/28/2017.
 */

public class MessageService extends FirebaseMessagingService {

    private final String TAG = this.getClass().getName();

    @Override
    public void onMessageReceived(RemoteMessage message){
        String from = message.getFrom();
        Map bundle = message.getData();

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "Bundle: " + bundle);

        String json = (String) bundle.get("data");
        ServiceModel serviceModel = new Gson().fromJson(json, ServiceModel.class);
        System.out.println(json);

        sendNotification(serviceModel);
    }

    public void sendNotification(ServiceModel serviceModel) {

//        ChatModel model = new ChatModel();
//        model.setId(serviceModel.getMessage().getMessageId());
//        model.setMessage(serviceModel.getMessage().getMessage());
//        model.setStatus(0);
//        model.setTopic(serviceModel.getChatRoomId());
//        model.setUserId(serviceModel.getUserId());
//
//        Intent intent = new Intent(Static.BC_MESSAGE);
//        intent.setAction(Static.BC_MESSAGE);
//        intent.putExtra(Static.CHAT_MODEL, model);

//        sendBroadcast(intent);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Intent notificationIntent = new Intent(getApplicationContext(), HomeActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        notificationIntent.setAction(Static.BC_MESSAGE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 2392 ,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(largeIcon)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getResources().getString(R.string.app_name))
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true)
                        .setContentText(serviceModel.getMessage().getMessage());
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(2392, mBuilder.build());
    }

}