package com.menard.mynews.controller.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.menard.mynews.R;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSearchText;
    private Button mSearchButton;
    private CheckBox mCategoryMovies;
    private CheckBox mCategoryScience;
    private CheckBox mCategoryTravel;
    String mTextSearched;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //-- Toolbar --
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Articles");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSearchText = findViewById(R.id.activity_search_edit_txt);
        mTextSearched = mSearchText.getText().toString();
        mCategoryMovies = findViewById(R.id.category_selection_movies);
        mCategoryScience = findViewById(R.id.category_selection_science);
        mCategoryTravel = findViewById(R.id.category_selection_travel);

        mSearchButton = findViewById(R.id.activity_search_button);
        mSearchButton.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        if(v == mSearchButton){
            Intent intent = new Intent(SearchActivity.this, SearchedArticlesActivity.class);
            intent.putExtra("QUERY", mTextSearched);
            startActivity(intent);
        }

        if(v == mCategoryMovies){
            if(mCategoryMovies.isChecked()){

            }else {

            }
        }

        if(v == mCategoryScience){

        }

        if(v == mCategoryTravel){

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
