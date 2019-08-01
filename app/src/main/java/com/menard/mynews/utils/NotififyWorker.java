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
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.menard.mynews.BaseSQLite;
import com.menard.mynews.R;
import com.menard.mynews.controller.activities.NotificationActivity;
import com.menard.mynews.model.search.ArticleSearched;
import com.menard.mynews.model.search.Doc;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Retrofit;

public class NotififyWorker extends Worker {

    private List<Doc> articleList;
    private final RetrofitService retrofitService = new RetrofitService();

    public NotififyWorker (@NonNull Context context, @NonNull WorkerParameters parameters){
        super(context, parameters);
    }

    public static void scheduleReminder(Data data){
        PeriodicWorkRequest notificationWork = new PeriodicWorkRequest.Builder(NotififyWorker.class, 24, TimeUnit.HOURS, 2, TimeUnit.HOURS)
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

        //-- Get extras from intent --
        final String title = getInputData().getString(Constants.EXTRA_TITLE);
        final String text = getInputData().getString(Constants.EXTRA_TEXT);
        final int id = (int) getInputData().getLong(Constants.EXTRA_ID, 0);
        String keyword = getInputData().getString(Constants.EXTRA_KEYWORD);
        String section = getInputData().getString(Constants.EXTRA_SECTION);

        //-- Get list of articles --
        Retrofit retrofit = retrofitService.getRetrofit();
        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleSearched> call = newYorkTimesAPI.getSearched(keyword, section, null, null,Constants.API_KEY);

        ArticleSearched response;
        try {
            response = call.execute().body();
            assert response != null;
            articleList = response.getResponse().getDocs();

            String nbrArticle = getNumberOfNewArticles();
            //-- Send notification only if new articles are available --
            if(!nbrArticle.equals("0")) {
               sendNotification(title, text, nbrArticle, id);
            }

        } catch (IOException e) {
            e.printStackTrace();
            // TODO   Log.e("TAG", "response not successful");
        }

        return Result.success();
    }

    /**
     * Send the notification with the params
     * @param title title
     * @param text text
     * @param number the number of new articles
     * @param id the id of the notification
     */
    private void sendNotification(String title, String text, String number,  int id){
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
                .setContentText(number + text)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        Objects.requireNonNull(notificationManager).notify(id, notification.build());
    }

    /**
     * Get the number of new articles
     * @return the string value of the number
     */
    private String getNumberOfNewArticles(){

        BaseSQLite baseSQLite = new BaseSQLite(getApplicationContext());
        long oldNumber;
        long newNumber;
        oldNumber = baseSQLite.getNumberOfRow();

        for(int i= 0; i< articleList.size(); i++){
            baseSQLite.addNewNotifiedURL(articleList.get(i).getWebUrl());
        }
        newNumber = baseSQLite.getNumberOfRow();
        return String.valueOf(newNumber - oldNumber);
    }

}
