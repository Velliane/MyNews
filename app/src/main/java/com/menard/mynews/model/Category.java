package com.menard.mynews.model;

public enum Category {

    TOP_STORIES("TOP STORIES"),
    MOST_POPULAR("MOST POPULAR"),
    MULTIMEDIA("MULTIMEDIA"),
    SCIENCE("SCIENCE"),
    TRAVEL("TRAVEL");


    private final String mName;

    Category(String name){
        this.mName = name;
    }

    public String getName(){
        return mName;
    }
}



