package com.menard.mynews;

import com.menard.mynews.utils.SearchedRequest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchedRequestTest {

    private SearchedRequest searchedRequest;

    @Before
    public void init(){
        searchedRequest = new SearchedRequest();
    }

    @Test
    public void getThreeSectionSelected(){
        String sectionSelected = "\"Travel\"\"Science\"\"Movies\"";

        String selection = searchedRequest.getSections(sectionSelected);

        assertEquals("news_desk:(\"Travel\"\"Science\"\"Movies\")", selection);
    }

    @Test
    public void getTwoSectionSelected(){
         String sectionSelected = "\"Travel\"\"Science\"";

         String selection = searchedRequest.getSections(sectionSelected);

         assertEquals("news_desk:(\"Travel\"\"Science\")", selection);
    }

    @Test
    public void getOneSectionSelected(){
        String sectionSelected = "\"Science\"";

        String selection = searchedRequest.getSections(sectionSelected);

        assertEquals("news_desk:(\"Science\")", selection);
    }

    @Test
    public void getNewsDeskFromListOfOneString(){
        List<String> list = new ArrayList<>();
        list.add("Movies");

        String newsDesk = searchedRequest.getNewsDeskForLucene(list);

        assertEquals("\"Movies\"", newsDesk);
    }

    @Test
    public void getNewsDeskFromListOfTwoString(){
        List<String> list = new ArrayList<>();
        list.add("Movies");
        list.add("Multimedia");

        String newsDesk = searchedRequest.getNewsDeskForLucene(list);

        assertEquals("\"Movies\"\"Multimedia\"", newsDesk);
    }

    @Test
    public void getNewsDeskFromListOfThreeString(){
        List<String> list = new ArrayList<>();
        list.add("Movies");
        list.add("Multimedia");
        list.add("Travel");

        String newsDesk = searchedRequest.getNewsDeskForLucene(list);

        assertEquals("\"Movies\"\"Multimedia\"\"Travel\"", newsDesk);
    }

}
