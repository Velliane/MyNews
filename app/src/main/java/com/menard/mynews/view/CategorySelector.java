package com.menard.mynews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
            checkBoxList.add(moviesChBox);
            moviesChBox.setTag("Movies");
            scienceChBox = findViewById(R.id.category_selection_science);
            checkBoxList.add(scienceChBox);
            scienceChBox.setTag("Science");
            travelChBox = findViewById(R.id.category_selection_travel);
            checkBoxList.add(travelChBox);
            travelChBox.setTag("Travel");

            isCheckButtonClicked = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


    /**
     * Check if at least on CheckBox os checked
     * @return true if at least one is checked, or false if not
     */
    public boolean atLeastOnBoxChecked(){
        boolean isCheckButtonClicked = false;
        for(int i = 0; i< checkBoxList.size(); i++) {
            if(checkBoxList.get(i).isChecked())
                isCheckButtonClicked = true;
        }
        return isCheckButtonClicked;
    }

    /**
     * Return a string with the sections selected by the user
     * @param checkedBoxList the list of checked CheckBox
     * @return the string
     */
    public String getNewsDeskForLucene(List<CheckBox> checkedBoxList){
        String newsDesk = "";
        for(CheckBox checkBox : checkedBoxList) {
            if (checkBox.isChecked()) {
                newsDesk += "\"" + checkBox.getTag() + "\"";
            }
        }
        return newsDesk;
    }

    /**
     * Return a list of the checked CheckBox
     * @return a List of CheckBox
     */
    public List<CheckBox> getCheckedBoxList(){
        List<CheckBox> checkedBoxList = new ArrayList<>();
        for(CheckBox checkBox: checkBoxList) {
            if(checkBox.isChecked())
                checkedBoxList.add(checkBox);
        }
        return checkedBoxList;
    }

}
