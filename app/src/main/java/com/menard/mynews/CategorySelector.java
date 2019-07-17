package com.menard.mynews;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.Toast;

public class CategorySelector extends GridLayout implements View.OnClickListener {

    public CheckBox moviesChBox;
    public CheckBox scienceChBox;
    public CheckBox travelChBox;

    public CategorySelector(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_selection, null);
        //inflate(getContext(),R.layout.category_selection,this);
        this.moviesChBox = view.findViewById(R.id.category_selection_movies);
        this.moviesChBox.setOnClickListener(this);
        this.scienceChBox = view.findViewById(R.id.category_selection_science);
        this.scienceChBox.setOnClickListener(this);
        this.travelChBox = view.findViewById(R.id.category_selection_travel);
        this.travelChBox.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


    @Override
    public void onClick(View v) {
        if(moviesChBox.isChecked())
            Toast.makeText(getContext(), "Movies",Toast.LENGTH_SHORT).show();
            //moviesIsChecked();
        if(scienceChBox.isChecked())
            //scienceIsChecked();
        if(travelChBox.isChecked())
            travelIsChecked();
    }

    public boolean moviesIsChecked(){
        return moviesChBox.isChecked();
    }

    public boolean scienceIsChecked(){
        return scienceChBox.isChecked();
    }

    public boolean travelIsChecked(){
        return travelChBox.isChecked();
    }
}
