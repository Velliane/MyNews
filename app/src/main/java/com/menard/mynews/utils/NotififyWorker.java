package com.menard.mynews.utils;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.menard.mynews.R;
import com.menard.mynews.controller.activities.NotificationActivity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class NotififyWorker extends Worker {

    public NotififyWorker (@NonNull Context context, @NonNull WorkerParameters parameters){
        super(context, parameters);
    }

    public static void scheduleReminder(Data data){
        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotififyWorker.class)
                                .setInitialDelay(1, TimeUnit.DAYS)
                                .setInputData(data).build();

        WorkManager instance = WorkManager.getInstance();
        instance.enqueue(notificationWork);
    }

    public static void cancelReminder (){
        WorkManager instance = WorkManager.getInstance();
        instance.cancelAllWork();
    }


    @NonNull
    @Override
    public ListenableWorker.Result doWork() {

        String title = getInputData().getString(Constants.EXTRA_TITLE);
        String text = getInputData().getString(Constants.EXTRA_TEXT);
        int id = (int) getInputData().getLong(Constants.EXTRA_ID, 0);

        sendNotification(title, text, id);
        return Result.success();
    }

    private void sendNotification(String title, String text, int id){
        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.EXTRA_ID, id);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        Objects.requireNonNull(notificationManager).notify(id, notification.build());
    }
}
