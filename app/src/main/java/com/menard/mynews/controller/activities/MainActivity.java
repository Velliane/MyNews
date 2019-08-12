package com.menard.mynews.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.menard.mynews.R;
import com.menard.mynews.adapter.ViewPagerAdapter;
import com.menard.mynews.model.Category;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /** ToolBar */
    private Toolbar toolbar;
    /** ViewPager */
    private ViewPager viewPager;
    /** Drawer Layout */
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-- Toolbar --
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //-- Configuration --
        configureViewPager();
        configureDrawerLayout();
        configureNavigationView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /**
     * When click on the ToolBar Items, start other activities
     * @param item the items
     * @return the activity
     */
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
                Intent helpActivity = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpActivity);
                break;

            case R.id.menu_activity_main_about:
                Intent aboutActivity = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Change the page of the viewPager, when clicking on the items
     * @param menuItem the items of the NavigationDrawer
     * @return the page
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.activity_main_drawer_top_stories:
                viewPager.setCurrentItem(Category.TOP_STORIES.ordinal());
                break;
            case R.id.activity_main_drawer_most_popular:
                viewPager.setCurrentItem(Category.MOST_POPULAR.ordinal());
                break;
            case R.id.activity_main_drawer_movies:
                viewPager.setCurrentItem(Category.MOVIES.ordinal());
                break;
            case R.id.activity_main_drawer_science:
                viewPager.setCurrentItem(Category.SCIENCE.ordinal());
                break;
            case R.id.activity_main_drawer_travel:
                viewPager.setCurrentItem(Category.TRAVEL.ordinal());
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    // -- CONFIGURATION -- //

    private void configureViewPager(){
        viewPager = findViewById(R.id.activity_main_viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()) {
        });
        //-- Add tabs --
        TabLayout tabLayout = findViewById(R.id.activity_main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void configureDrawerLayout(){
        drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView(){
        NavigationView navigationView = findViewById(R.id.activity_main_navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }



}
