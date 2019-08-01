package com.menard.mynews.utils;


public class SearchedRequest {


    public SearchedRequest() {
    }


    /**
     * Return the string for the request
     * @return a string
     */
    public String getSections(String sectionSelected){
        String sections = "news_desk:(";
        sections += sectionSelected;
        sections += ")";
        return sections;

    }

}
