package com.menard.mynews;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.GridLayout;

public class CategorySelector extends GridLayout {

    private CheckBox moviesChBox;
    private CheckBox scienceChBox;
    private CheckBox travelChBox;

    public CategorySelector(Context context) {
        super(context);
        inflate(getContext(),R.layout.category_selection,this);
        this.moviesChBox = findViewById(R.id.category_selection_movies);
        this.scienceChBox = findViewById(R.id.category_selection_science);
        this.travelChBox = findViewById(R.id.category_selection_travel);
    }

}
