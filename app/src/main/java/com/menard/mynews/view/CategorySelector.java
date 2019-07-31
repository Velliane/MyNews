package com.menard.mynews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.GridLayout;

import com.menard.mynews.R;

import java.util.ArrayList;
import java.util.List;

public class CategorySelector extends GridLayout {

    public CheckBox moviesChBox;
    public CheckBox scienceChBox;
    public CheckBox travelChBox;
    public boolean isCheckButtonClicked;
    private List<CheckBox> checkBoxList;

    public CategorySelector(Context context) {
        super(context);
        initView();
    }

    public CategorySelector(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    private void initView(){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            inflater.inflate(R.layout.category_selection, this, true);
            checkBoxList = new ArrayList<>();

            moviesChBox = findViewById(R.id.category_selection_movies);
            //moviesChBox.setOnClickListener(this);
            checkBoxList.add(moviesChBox);
            scienceChBox = findViewById(R.id.category_selection_science);
            //scienceChBox.setOnClickListener(this);
            checkBoxList.add(scienceChBox);
            travelChBox = findViewById(R.id.category_selection_travel);
            //travelChBox.setOnClickListener(this);
            checkBoxList.add(travelChBox);

            isCheckButtonClicked = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


//    @Override
//    public void onClick(View v) {
//        if(moviesChBox.isChecked())
//            Toast.makeText(getContext(), "Movies",Toast.LENGTH_SHORT).show();
//        if(scienceChBox.isChecked())
//            Toast.makeText(getContext(), "Science", Toast.LENGTH_SHORT).show();
//        if(travelChBox.isChecked())
//            Toast.makeText(getContext(), "Travel", Toast.LENGTH_SHORT).show();
//    }


    public boolean atLeastOnBoxChecked(){
        boolean isCheckButtonClicked = false;
        for(int i = 0; i< checkBoxList.size(); i++) {
            if(checkBoxList.get(i).isChecked())
                isCheckButtonClicked = true;
        }
        return isCheckButtonClicked;
    }
}
