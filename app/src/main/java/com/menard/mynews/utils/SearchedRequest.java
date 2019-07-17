package com.menard.mynews.utils;

import com.menard.mynews.CategorySelector;

public class SearchedRequest {


    private CategorySelector mCategorySelector;


    public SearchedRequest(CategorySelector categorySelector) {
        mCategorySelector = categorySelector;
    }


    /**
     * Return the string of section selected
     * @return a string
     */
    public String getSectionSelected(){
        String section = "news_desk:(";

        if(mCategorySelector.moviesChBox.isChecked()){
            section += " \"Movies\"";
        }
        if(mCategorySelector.scienceChBox.isChecked()){
            section += " \"Science\"";
        }
        if(mCategorySelector.travelChBox.isChecked()){
            section += " \"Travel\"";
        }
        section += ")";
        return section;

    }

}
