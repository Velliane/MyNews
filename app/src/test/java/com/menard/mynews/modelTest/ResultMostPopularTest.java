package com.menard.mynews.modelTest;

import com.menard.mynews.model.most_popular.Result;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultMostPopularTest {

    private final Result mResult = new Result();

    @Test
    public void getUrl(){
        String url = "https://www.nytimes.com/";

        mResult.setUrl(url);

        assertEquals("https://www.nytimes.com/", mResult.getUrl());
    }

    @Test
    public void getSection(){
        String section = "Arts";

        mResult.setSection(section);

        assertEquals("Arts", mResult.getSection());
    }

    @Test
    public void getTitle(){
        String title = "Article one";

        mResult.setTitle(title);

        assertEquals("Article one", mResult.getTitle());
    }

    @Test
    public void getPublishedDate(){
        String date = "31/07/2019";

        mResult.setPublishedDate(date);

        assertEquals("31/07/2019", mResult.getPublishedDate());
    }

}
