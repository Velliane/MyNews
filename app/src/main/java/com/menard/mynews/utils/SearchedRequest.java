package com.menard.mynews.utils;

import com.menard.mynews.view.CategorySelector;

public class SearchedRequest {


    private CategorySelector mCategorySelector;

    public SearchedRequest(CategorySelector categorySelector) {
        mCategorySelector = categorySelector;
    }

   public SearchedRequest makeSearchRequest(CategorySelector categorySelector){
        return new SearchedRequest(categorySelector);
    }

    /**
     * Return the string of section selected
     * @return a string
     */
    public String getSections(String sectionSelected){
        String sections = "news_desk:(";
        sections += sectionSelected;
        sections += ")";
        return sections;

    }

    public String getSectionSelected(){
        String section ="";
        if(mCategorySelector.moviesChBox.isChecked()){
            section += " \"Movies\"";
        }
        if(mCategorySelector.scienceChBox.isChecked()){
            section += " \"Science\"";
        }
        if(mCategorySelector.travelChBox.isChecked()){
            section += " \"Travel\"";
        }
        return section;
    }

}
