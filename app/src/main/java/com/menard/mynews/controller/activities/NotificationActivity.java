package com.menard.mynews.controller.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.work.Data;

import com.menard.mynews.view.CategorySelector;
import com.menard.mynews.R;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NotififyWorker;
import com.menard.mynews.utils.SearchedRequest;

public class NotificationActivity extends AppCompatActivity {


    /**
     * Switch Button
     */
    private Switch mSwitch;
    /** Shared Preferences */
    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor editor;
    /** EditText */
    public EditText textSearched;
    /** Category Selector */
    private CategorySelector mCategorySelector;
    private SearchedRequest mSearchedRequest;



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
        mCategorySelector = findViewById(R.id.activity_notification_category);

        mSearchedRequest = new SearchedRequest();
        //-- Get Shared Preferences --
        mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE, MODE_PRIVATE);
        editor = mSharedPreferences.edit();

        //-- Check if notification are already enabled --
        if (mSharedPreferences.getBoolean(Constants.PREFS_NOTIFICATION, false)) {
            mSwitch.setChecked(true);
            textSearched.setText(mSharedPreferences.getString(Constants.PREFS_KEYWORD, ""));
        }


        //-- Enable or disable the notification by clicking on the switch button --
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //-- Save the input of the edittext in shared preferences --
                editor.putString(Constants.PREFS_KEYWORD, textSearched.getText().toString());
                editor.apply();
                configureNotification(isChecked);
            }

        });
    }


    /**
     * Configure the notification
     * @param isChecked state of the switch button
     */
    public void configureNotification(boolean isChecked) {
        if (isChecked) {
            editor.putBoolean(Constants.PREFS_NOTIFICATION, true);
            editor.apply();

            //-- If edit text si empty, unchecked the switch button --
            if (textSearched.getText().toString().equals("") && !mCategorySelector.atLeastOnBoxChecked()) {
                Toast.makeText(NotificationActivity.this, "Input value or category must not be empty", Toast.LENGTH_SHORT).show();
                mSwitch.setChecked(false);
                editor.putBoolean(Constants.PREFS_NOTIFICATION, false);
                editor.apply();
            }


            String section = mSearchedRequest.getSections(mCategorySelector.getNewsDeskForLucene(mCategorySelector.getCheckedBoxList()));
            Data data = new Data.Builder().putString(Constants.EXTRA_TITLE, Constants.TITLE)
                    .putString(Constants.EXTRA_TEXT,Constants.TEXT)
                    .putInt(Constants.EXTRA_ID, 1)
                    .putString(Constants.EXTRA_KEYWORD, textSearched.getText().toString())
                    .putString(Constants.EXTRA_SECTION, section)
                    .build();
            NotififyWorker.scheduleReminder(data);

            Toast.makeText(this, "Notification are enabled", Toast.LENGTH_SHORT).show();

        } else {
            NotififyWorker.cancelReminder();
            editor.putBoolean(Constants.PREFS_NOTIFICATION, false);
            editor.apply();

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
