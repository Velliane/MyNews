package com.menard.mynews;

import android.widget.CheckBox;

import com.menard.mynews.view.CategorySelector;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class CategorySelectorTest {

    private CategorySelector mCategorySelector = Mockito.mock(CategorySelector.class);
    private CheckBox mCheckBox = Mockito.mock(CheckBox.class);

    @Test
    public void atLeastOnBoxCheckedReturnTrue(){
        when(mCheckBox.isChecked()).thenReturn(true);

        assertTrue(mCategorySelector.atLeastOnBoxChecked());
    }
}
