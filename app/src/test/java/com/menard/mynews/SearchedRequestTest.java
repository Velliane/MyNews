package com.menard.mynews;

import android.content.Context;

import com.menard.mynews.controller.activities.SearchActivity;
import com.menard.mynews.utils.SearchedRequest;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

public class SearchedRequestTest {


    @Test
    public void getThreeSectionSelected(){
        CategorySelector categorySelector = new CategorySelector(mock(SearchActivity.class));
        SearchedRequest searchedRequest = new SearchedRequest(categorySelector);
        categorySelector.travelChBox.isChecked();
        categorySelector.scienceChBox.isChecked();
        categorySelector.moviesChBox.isChecked();

        String selection = searchedRequest.getSectionSelected();

        assertEquals("news_desk:(\"Travel\"\"Science\"\"Movies\")", selection);
    }

    @Test
    public void getTwoSectionSelected(){
        CategorySelector categorySelector = new CategorySelector(mock(SearchActivity.class));
        SearchedRequest searchedRequest = new SearchedRequest(categorySelector);
        categorySelector.travelChBox.isChecked();
        categorySelector.scienceChBox.isChecked();

        String selection = searchedRequest.getSectionSelected();

        assertEquals("news_desk:(\"Travel\"\"Science\")", selection);
    }

    @Test
    public void getOneSectionSelected(){
        CategorySelector categorySelector = new CategorySelector(mock(SearchActivity.class));
        SearchedRequest searchedRequest = new SearchedRequest(categorySelector);
        categorySelector.scienceChBox.isChecked();

        String selection = searchedRequest.getSectionSelected();

        assertEquals("news_desk:(\"Science\")", selection);
    }
}
