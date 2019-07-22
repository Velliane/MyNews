package com.menard.mynews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.Toast;

public class CategorySelector extends GridLayout implements View.OnClickListener {

    public CheckBox moviesChBox;
    public CheckBox scienceChBox;
    public CheckBox travelChBox;
    public boolean isCheckButtonClicked;

    public CategorySelector(Context context) {
        super(context);
        initView();
    }

    public CategorySelector(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    protected void initView(){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate( R.layout.category_selection, null);
            addView(view);

            moviesChBox = view.findViewById(R.id.category_selection_movies);
            moviesChBox.setOnClickListener(this);
            scienceChBox = view.findViewById(R.id.category_selection_science);
            scienceChBox.setOnClickListener(this);
            travelChBox = view.findViewById(R.id.category_selection_travel);
            travelChBox.setOnClickListener(this);

            isCheckButtonClicked = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


    @Override
    public void onClick(View v) {
        if(moviesChBox.isChecked())
            Toast.makeText(getContext(), "Movies",Toast.LENGTH_SHORT).show();
         isCheckButtonClicked = true;
        if(scienceChBox.isChecked())
            Toast.makeText(getContext(), "Science", Toast.LENGTH_SHORT).show();
         isCheckButtonClicked = true;
        if(travelChBox.isChecked())
            Toast.makeText(getContext(), "Travel", Toast.LENGTH_SHORT).show();
            isCheckButtonClicked = true;
    }


    public boolean atLeastOnBoxChecked(){
        return  isCheckButtonClicked;
    }
}
