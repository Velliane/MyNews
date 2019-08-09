package com.menard.mynews.modelTest;

import com.menard.mynews.model.search.Multimedium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultimediumTest {

    private Multimedium mMultimedium;

    @Before
    public void init(){
        mMultimedium = new Multimedium();
    }

    @Test
    public void getUrl(){
        String url = "https://www.nytimes.com/";

        mMultimedium.setUrl(url);

        assertEquals("https://www.nytimes.com/", mMultimedium.getUrl());
    }

    @Test
    public void getHeight(){
        Integer height = 20;

        mMultimedium.setHeight(height);

        assertEquals(20, (Object) mMultimedium.getHeight());
    }

    @Test
    public void getWidth(){
        Integer width = 20;

        mMultimedium.setWidth(width);

        assertEquals(20, (Object) mMultimedium.getWidth());
    }
}
