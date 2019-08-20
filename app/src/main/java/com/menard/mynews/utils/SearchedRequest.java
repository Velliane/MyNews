package com.menard.mynews.utils;

import java.util.List;

public class SearchedRequest {


    public SearchedRequest() {
    }

    /**
     * Return the string for the request
     *
     * @return a string
     */
    public String getSections(String sectionSelected) {
        String sections = "news_desk:(";
        sections += sectionSelected;
        sections += ")";
        return sections;

    }

    /**
     * Return a string with the sections selected by the user
     *
     * @param checkedBoxList the list of checked CheckBox
     * @return the string
     */
    public String getNewsDeskForLucene(List<String> checkedBoxList) {
        StringBuilder newsDesk = new StringBuilder();
        for (String section : checkedBoxList) {
            newsDesk.append("\"").append(section).append("\"");

        }
        return newsDesk.toString();
    }

}
