package com.l_es.communityrecipes.Services;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.l_es.communityrecipes.EntranceActivity;
import com.l_es.communityrecipes.R;
import com.l_es.communityrecipes.Utilities;

/**
 * Created by Lidor on 05/20/2022.
 * Developer name: L-ES
 *  _        _   _____     ____    ______
 * | |      |_| |  __ \   / __ \  |  O   |
 * | |      | | | |  | | | |  | | |   ___/
 * | |____  | | | |__| | | |__| | | | \
 * |______| |_| |_____/   \____/  |_|__\
 *  ____         ____
 * |  __|       |  __|
 * |  __|   _   |__  |
 * |____|  |_|  |____|
 */
public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context);
    }

    @SuppressLint("ObsoleteSdkInt")
    void showNotification(Context context) {
        NotificationCompat.Builder mBuilder;
        Intent notificationIntent = new Intent(context, EntranceActivity.class);
        Bundle bundle = new Bundle();
        notificationIntent.putExtras(bundle);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new NotificationChannel(Utilities.CHANNEL_ID, Utilities.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(mChannel);
            mBuilder = new NotificationCompat.Builder(context, Utilities.CHANNEL_ID)
                    .setLights(Color.RED, 300, 300);
        } else {
            mBuilder = new NotificationCompat.Builder(context, Utilities.CHANNEL_ID)
                    .setPriority(NotificationManager.IMPORTANCE_DEFAULT);
        }

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setSmallIcon(R.drawable.l_es);
        mBuilder.setChannelId(Utilities.CHANNEL_ID);
        mBuilder.setContentTitle(Utilities.NOTIFICATION_TITLE);
        mBuilder.setContentText(Utilities.NOTIFICATION_CONTENT_TEXT);
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(Utilities.NOTIFICATION_ID, mBuilder.build());
    }
}
