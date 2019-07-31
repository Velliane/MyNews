package com.menard.mynews;

import androidx.test.platform.app.InstrumentationRegistry;

import com.menard.mynews.utils.SearchedRequest;
import com.menard.mynews.view.CategorySelector;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchedRequestAndroidTest {

    private SearchedRequest mSearchedRequest;
    private CategorySelector mCategorySelector;

    @Before
    public void init(){
        mCategorySelector = new CategorySelector(InstrumentationRegistry.getInstrumentation().getContext());
        mSearchedRequest = new SearchedRequest(mCategorySelector);
    }

    @Test
    public void ifOnBoxChecked(){
        mCategorySelector.travelChBox.isChecked();

        assertEquals("\"Travel\"", mSearchedRequest.getSectionSelected());
    }
}
