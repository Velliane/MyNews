package com.menard.mynews.controller.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.work.Data;

import com.menard.mynews.R;
import com.menard.mynews.adapter.SearchedAdapter;
import com.menard.mynews.model.search.ArticleSearched;
import com.menard.mynews.model.search.Doc;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NewYorkTimesAPI;
import com.menard.mynews.utils.NotififyWorker;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationActivity extends AppCompatActivity {


    Switch mSwitch;

    public SharedPreferences mSharedPreferences;
    public EditText textSearched;
    public SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //-- Toolbar --
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notifications");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwitch = findViewById(R.id.activity_notification_switch);
        textSearched = findViewById(R.id.activity_search_edit_txt);


        mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE, MODE_PRIVATE);
        editor = mSharedPreferences.edit();

        //-- Check if notification are already enabled --
        if (mSharedPreferences.getBoolean(Constants.PREFS_NOTIFICATION, false)) {
            mSwitch.setChecked(true);
            textSearched.setText(mSharedPreferences.getString(Constants.PREFS_KEYWORD, ""));
        }

        //-- Save the input of the edittext in shared preferences --
        editor.putString(Constants.PREFS_KEYWORD, textSearched.getText().toString());
        editor.apply();


        //-- Enable or disable the notification by clicking on the switch button --
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                configureNotification(isChecked);
            }

        });
    }


    /**
     * Configure the notification
     *
     * @param isChecked state of the switch button
     */
    public void configureNotification(boolean isChecked) {
        if (isChecked) {
            editor.putBoolean(Constants.PREFS_NOTIFICATION, true);
            editor.apply();

            //-- If edit text si empty, unchecked the switch button --
            if (textSearched.getText().toString().equals("")) {
                Toast.makeText(NotificationActivity.this, "Input value must not be empty", Toast.LENGTH_SHORT).show();
                mSwitch.setChecked(false);
                editor.putBoolean(Constants.PREFS_NOTIFICATION, false);
                editor.apply();
            }

            Data data = new Data.Builder().putString(Constants.EXTRA_TITLE, Constants.TITLE)
                    .putString(Constants.EXTRA_TEXT,Constants.TEXT)
                    .putInt(Constants.EXTRA_ID, 1)
                    .putString("KEYWORD", textSearched.getText().toString())
                    .build();
            NotififyWorker.scheduleReminder(data);


        } else {
            NotififyWorker.cancelReminder();
            editor.putBoolean(Constants.PREFS_NOTIFICATION, false);
            editor.apply();

        }
    }




}
