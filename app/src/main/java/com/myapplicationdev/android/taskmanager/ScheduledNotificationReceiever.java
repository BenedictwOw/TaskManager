package com.myapplicationdev.android.taskmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ScheduledNotificationReceiever extends BroadcastReceiver {
    int reqCode = 12345;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel("default", "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        Intent i= new Intent(context, AddActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity
                (context, reqCode, i,
                        PendingIntent.FLAG_CANCEL_CURRENT);
        Uri uri= RingtoneManager.getDefaultUri
                (RingtoneManager.TYPE_ALARM);


        Bitmap picture = BitmapFactory.decodeResource(context.getResources(), R.drawable.download);

        // Build notification
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, "default");
        builder.setContentTitle("Task Manager Reminder");
        builder.setContentText(intent.getStringExtra("data"));
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setLargeIcon(picture);
        builder .setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(picture)
                .bigLargeIcon(null));
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);
        builder.setSound(uri);

        Notification n = builder.build();
        notificationManager.notify(123, n);
    }

}
