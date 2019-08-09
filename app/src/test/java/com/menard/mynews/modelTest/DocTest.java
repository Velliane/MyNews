package com.menard.mynews.modelTest;

import com.menard.mynews.model.search.Doc;
import com.menard.mynews.model.search.Multimedium;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DocTest {

    private Doc mDoc;

    @Before
    public void init(){
        mDoc = new Doc();
    }

    @Test
    public void getWebUrl(){
        String url = "https://www.nytimes.com/";

        mDoc.setWebUrl(url);

        assertEquals("https://www.nytimes.com/", mDoc.getWebUrl());
    }

    @Test
    public void getAbstract(){
        String title = "Article one";

        mDoc.setAbstract(title);

        assertEquals("Article one", mDoc.getAbstract());
    }

    @Test
    public void getMultimedia(){
        List<Multimedium> list = new ArrayList<>();

        mDoc.setMultimedia(list);

        assertEquals(list, mDoc.getMultimedia());
    }

    @Test
    public void setPubDate(){
        String date = "31/07/2019";

        mDoc.setPubDate(date);

        assertEquals("31/07/2019", mDoc.getPubDate());
    }

    @Test
    public void getSectionName(){
        String section = "Science";

        mDoc.setSectionName(section);

        assertEquals("Science", mDoc.getSectionName());
    }

    @Test
    public void getId(){
        String id = "21";

        mDoc.setId(id);

        assertEquals("21", mDoc.getId());
    }
}
