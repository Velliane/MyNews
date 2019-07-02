package com.menard.mynews.controller;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.menard.mynews.R;
import com.menard.mynews.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-- Toolbar --
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.configureViewPager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_activity_main_search:
                Intent searchActivity = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchActivity);
                break;

            case R.id.menu_activity_main_notifications:
                Intent notificationActivity = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(notificationActivity);
                break;

            case R.id.menu_activity_main_help:


            case R.id.menu_activity_main_about:

               return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void configureViewPager(){
        ViewPager viewPager = findViewById(R.id.activity_main_viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()) {
        });
        //-- Add tabs --
        TabLayout tabLayout = findViewById(R.id.activity_main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

}
