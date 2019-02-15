package com.example.lab_6_1;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class ReminderBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(context, MainActivity.class);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder = TaskStackBuilder.create(context);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder.addNextIntentWithParentStack(resultIntent);
        }

        PendingIntent resultPendingIntent = null;
        // Get the PendingIntent containing the entire back stack
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        // Get alarm text from sent broadcast
        String text = intent.getExtras().getString("TEXT");
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        // Show notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, ReminderApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Your reminder !")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(resultPendingIntent)  // Open MainActivity intent
                .setSound(alarmSound);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(234, mBuilder.build());
    }
}
