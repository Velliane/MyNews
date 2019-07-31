package com.menard.mynews;

import android.widget.CheckBox;

import androidx.test.platform.app.InstrumentationRegistry;

import com.menard.mynews.view.CategorySelector;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class CategorySelectorTest {

    private CategorySelector mCategorySelector = new CategorySelector(InstrumentationRegistry.getInstrumentation().getContext());
    private CheckBox mCheckBoxScience;


    @Test
    public void atLeastOnBoxCheckedReturnTrue(){
        mCheckBoxScience.isChecked();

        assertTrue(mCategorySelector.atLeastOnBoxChecked());
    }
}
