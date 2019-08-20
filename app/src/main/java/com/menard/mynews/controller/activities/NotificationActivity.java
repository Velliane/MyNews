package com.menard.mynews.controller.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.work.Data;

import com.menard.mynews.view.CategorySelectorView;
import com.menard.mynews.R;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NotififyWorker;
import com.menard.mynews.utils.SearchedRequest;

import java.util.Objects;

public class NotificationActivity extends AppCompatActivity {


    /**
     * Switch Button
     */
    public Switch mSwitch;
    private SharedPreferences.Editor editor;
    /**
     * EditText
     */
    private EditText textSearched;
    /**
     * Category Selector
     */
    private CategorySelectorView mCategorySelectorView;
    /**
     * SearchedRequest
     */
    private SearchedRequest mSearchedRequest;


    public NotificationActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //-- Toolbar --
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notifications");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mSwitch = findViewById(R.id.activity_notification_switch);
        textSearched = findViewById(R.id.activity_search_edit_txt);
        mCategorySelectorView = findViewById(R.id.activity_notification_category);

        mSearchedRequest = new SearchedRequest();
        //-- Get Shared Preferences --
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //-- Check if notification are already enabled --
        if (sharedPreferences.getBoolean(Constants.PREFS_NOTIFICATION, false)) {
            mSwitch.setChecked(true);
            textSearched.setText(sharedPreferences.getString(Constants.PREFS_KEYWORD, ""));
        }


        //-- Enable or disable the notification by clicking on the switch button --
        mSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //-- Save the input of the edittext in shared preferences --
            editor.putString(Constants.PREFS_KEYWORD, textSearched.getText().toString());
            editor.apply();
            configureNotification(isChecked);
        });
    }


    /**
     * Configure the notification
     *
     * @param isChecked state of the switch button
     */
    private void configureNotification(boolean isChecked) {
        if (isChecked) {

            //-- If edit text is empty or no category are selected, unchecked the switch button --
            if (textSearched.getText().toString().equals("")) {
                Toast.makeText(NotificationActivity.this, "Please write a keyword", Toast.LENGTH_SHORT).show();
                mSwitch.setChecked(false);
            } else if (!mCategorySelectorView.atLeastOnBoxChecked()) {
                Toast.makeText(NotificationActivity.this, "Select at least one category", Toast.LENGTH_SHORT).show();
                mSwitch.setChecked(false);
            } else {
                Toast.makeText(this, "Notifications are enabled", Toast.LENGTH_SHORT).show();
                editor.putBoolean(Constants.PREFS_NOTIFICATION, true);
                editor.apply();
            }


            String section = mSearchedRequest.getSections(mSearchedRequest.getNewsDeskForLucene(mCategorySelectorView.getCheckedBoxList()));
            Data data = new Data.Builder().putInt(Constants.EXTRA_ID, 1)
                    .putString(Constants.EXTRA_KEYWORD, textSearched.getText().toString())
                    .putString(Constants.EXTRA_SECTION, section)
                    .build();
            NotififyWorker.scheduleReminder(data);


        } else {
            Toast.makeText(this, "Notifications are disabled", Toast.LENGTH_SHORT).show();
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
