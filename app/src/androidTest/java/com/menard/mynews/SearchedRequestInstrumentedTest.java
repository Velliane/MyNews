package com.menard.mynews;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.menard.mynews.utils.SearchedRequest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class SearchedRequestInstrumentedTest {

    @Test
    public void getThreeSectionSelected(){
        CategorySelector categorySelector = new CategorySelector(InstrumentationRegistry.getInstrumentation().getContext());
        SearchedRequest searchedRequest = new SearchedRequest(categorySelector);
        categorySelector.travelChBox.isChecked();
        categorySelector.scienceChBox.isChecked();
        categorySelector.moviesChBox.isChecked();

        String selection = searchedRequest.getSectionSelected();

        assertEquals("news_desk:(\"Travel\"\"Science\"\"Movies\")", selection);
    }

    @Test
    public void getTwoSectionSelected(){
        CategorySelector categorySelector = new CategorySelector(InstrumentationRegistry.getInstrumentation().getContext());
        SearchedRequest searchedRequest = new SearchedRequest(categorySelector);
        categorySelector.travelChBox.isChecked();
        categorySelector.scienceChBox.isChecked();

        String selection = searchedRequest.getSectionSelected();

        assertEquals("news_desk:(\"Travel\"\"Science\")", selection);
    }

    @Test
    public void getOneSectionSelected(){
        CategorySelector categorySelector = new CategorySelector(InstrumentationRegistry.getInstrumentation().getContext());
        SearchedRequest searchedRequest = new SearchedRequest(categorySelector);
        categorySelector.scienceChBox.isChecked();

        String selection = searchedRequest.getSectionSelected();

        assertEquals("news_desk:(\"Science\")", selection);
    }
}
