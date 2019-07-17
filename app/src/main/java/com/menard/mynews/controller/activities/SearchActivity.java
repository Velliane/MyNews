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

import com.menard.mynews.CategorySelector;
import com.menard.mynews.R;
import com.menard.mynews.utils.SearchedRequest;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSearchText;
    private Button mSearchButton;
    public CategorySelector mCategorySelector;

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
        mSearchButton = findViewById(R.id.activity_search_button);
        mSearchButton.setOnClickListener(this);

        mCategorySelector = new CategorySelector(this);

    }



    @Override
    public void onClick(View v) {
        if(v == mSearchButton){
            mTextSearched = mSearchText.getText().toString();
            String section = new SearchedRequest(mCategorySelector).getSectionSelected();
            Intent intent = new Intent(SearchActivity.this, SearchedArticlesActivity.class);
            intent.putExtra("QUERY", mTextSearched);
            intent.putExtra("SECTION", section);
            startActivity(intent);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
