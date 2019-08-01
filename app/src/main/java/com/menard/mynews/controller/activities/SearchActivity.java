package com.menard.mynews.controller.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.menard.mynews.view.CategorySelector;
import com.menard.mynews.R;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.DateUtils;
import com.menard.mynews.utils.SearchedRequest;

import java.util.Calendar;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    /** Edit Text */
    private EditText mSearchText;
    /** Button Search*/
    private Button mSearchButton;
    /** Text View BeginDate */
    private TextView mBeginDate;
    /** Text View EndDate */
    private TextView mEndDate;
    /** Category Selector */
    private CategorySelector mCategorySelector;
    /** Searched Request */
    private SearchedRequest mSearchedRequest;
    /** Keyword */
    String mTextSearched;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //-- Toolbar --
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Articles");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mSearchText = findViewById(R.id.activity_search_edit_txt);
        mSearchText.addTextChangedListener(this);
        mSearchButton = findViewById(R.id.activity_search_button);
        mSearchButton.setEnabled(false);
        mSearchButton.setOnClickListener(this);

        //-- Category selection --
        mCategorySelector = findViewById(R.id.activity_search_category_selection);

        mSearchedRequest = new SearchedRequest();

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
            showDatePickerDialog(mBeginDate, year, month, day);
        }
        //-- Select end date --
        if(v == mEndDate){
            showDatePickerDialog(mEndDate, year, month, day);
        }
        //-- Start SearchedArticlesActivity with filter --
        if(v == mSearchButton){
            boolean boxChecked = mCategorySelector.atLeastOnBoxChecked();
            if(!boxChecked){
                Toast.makeText(this, "Select at least on category", Toast.LENGTH_SHORT).show();
            }else {
                mTextSearched = mSearchText.getText().toString();
                String section = mSearchedRequest.getSections(mCategorySelector.getNewsDeskForLucene(mCategorySelector.getCheckedBoxList()));

                Intent intent = new Intent(SearchActivity.this, SearchedArticlesActivity.class);
                intent.putExtra(Constants.EXTRA_KEYWORD, mTextSearched);
                intent.putExtra(Constants.EXTRA_SECTION, section);
                intent.putExtra(Constants.EXTRA_BEGIN_DATE, DateUtils.parseRequestDate(mBeginDate.getText().toString()));
                intent.putExtra(Constants.EXTRA_END_DATE, DateUtils.parseRequestDate(mEndDate.getText().toString()));
                startActivity(intent);
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Show DatePickerDialog
     * @param textView the text view clicked
     * @param year the actual year
     * @param month the actual month
     * @param day the actual day
     */
    private void showDatePickerDialog(final TextView textView, int year, int month, int day){
        DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String newDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                textView.setText(newDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!"".equals(mSearchText.getText().toString())){
            mSearchButton.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
