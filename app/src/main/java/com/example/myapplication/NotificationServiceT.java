package com.example.myapplication;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;


public class NotificationServiceT extends IntentService {
    private static final int NOTIFICATION_ID = 3;
    public NotificationServiceT() {

        super("NotificationServiceT");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        String[] tweets={
                "Not  a lot going on at the moment",
                "maybe your not meant to fit in,maybe your meant to stand out",
                "I don't know if you know who you are until you lose who you are",
                "you gotta tell the story the way that it happened to you and the way you experienced it",
                "The moment whe Kanye West secretly records youre phone call, then Kim posts it on the internet"

        };
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Tweeting Tay Tay");
        builder.setContentText(tweets[(int)(Math.random()*tweets.length)]);
        builder.setSmallIcon(R.drawable.phone_icon);
        Intent notifyIntent = new Intent(this, ActivityChat.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);


        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        builder.build();
        mNotificationManager.notify(NOTIFICATION_ID,notificationCompat);

    }


}