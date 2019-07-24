package com.menard.mynews.utils;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
import com.menard.mynews.model.search.ArticleSearched;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        final String title = getInputData().getString(Constants.EXTRA_TITLE);
        final String text = getInputData().getString(Constants.EXTRA_TEXT);
        final int id = (int) getInputData().getLong(Constants.EXTRA_ID, 0);

        String keyword = getInputData().getString(Constants.EXTRA_KEYWORD);
        String section = getInputData().getString(Constants.EXTRA_SECTION);


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //-- Get list of articles --
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleSearched> call = newYorkTimesAPI.getSearched(keyword, section, null, null,Constants.API_KEY);

        ArticleSearched response = null;
        try {
            response = call.execute().body();
            sendNotification(title, text, id);
        } catch (IOException e) {
            e.printStackTrace();
        }



                 // TODO   Log.e("TAG", "response not successful");


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
