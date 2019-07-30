package com.menard.mynews.controller.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.menard.mynews.view.CategorySelector;
import com.menard.mynews.R;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.DateUtils;
import com.menard.mynews.utils.SearchedRequest;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSearchText;
    private Button mSearchButton;
    private TextView mBeginDate;
    private TextView mEndDate;
    private CategorySelector mCategorySelector;
    private SearchedRequest mSearchedRequest;

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
        mSearchButton.setEnabled(true);
        mSearchButton.setOnClickListener(this);

        //-- Category selection --
        mCategorySelector = findViewById(R.id.activity_search_category_selection);
        //boolean boxChecked = mCategorySelector.atLeastOnBoxChecked();
        //if(boxChecked && mTextSearched!= null)
          //  mSearchButton.setEnabled(true);

        mSearchedRequest= new SearchedRequest(mCategorySelector);

        //-- Date selection --
        mBeginDate = findViewById(R.id.activity_search_spinner_begin_date);
        mBeginDate.setClickable(true);
        mBeginDate.setOnClickListener(this);
        mEndDate = findViewById(R.id.activity_search_spinner_end_date);
        mEndDate.setClickable(true);
        mEndDate.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        //-- Select begin date --
        if(v == mBeginDate){
            showDatePickerDialog(year, month, day);
        }
        //-- Select end date --
        if(v == mEndDate){
            showDatePickerDialog(year, month, day);
        }
        //-- Start SearchedArticlesActivity with filter --
        if(v == mSearchButton){
            mTextSearched = mSearchText.getText().toString();
            String section = mSearchedRequest.getSections(mSearchedRequest.getSectionSelected());

            Intent intent = new Intent(SearchActivity.this, SearchedArticlesActivity.class);
            intent.putExtra(Constants.EXTRA_KEYWORD, mTextSearched);
            intent.putExtra(Constants.EXTRA_SECTION, section);
            intent.putExtra(Constants.EXTRA_BEGIN_DATE, DateUtils.parseRequestDate(mBeginDate.getText().toString()));
            intent.putExtra(Constants.EXTRA_END_DATE, DateUtils.parseRequestDate(mEndDate.getText().toString()));
            startActivity(intent);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Show DatePickerDialog
     * @param year the actual year
     * @param month the actual month
     * @param day the actual day
     */
    private void showDatePickerDialog(int year, int month, int day){
        DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String newDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                mBeginDate.setText(newDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
