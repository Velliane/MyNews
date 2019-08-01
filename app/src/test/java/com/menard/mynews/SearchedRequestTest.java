package com.menard.mynews;

import com.menard.mynews.utils.SearchedRequest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchedRequestTest {

    private final SearchedRequest searchedRequest = new SearchedRequest();

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
}
