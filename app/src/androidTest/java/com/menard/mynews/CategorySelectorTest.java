package com.menard.mynews;

import android.widget.CheckBox;

import androidx.test.platform.app.InstrumentationRegistry;

import com.menard.mynews.view.CategorySelector;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4ClassRunner.class)
public class CategorySelectorTest {

    private CategorySelector mCategorySelector;

    @Before
    public void init(){
        mCategorySelector = new CategorySelector(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }


    @Test
    public void atLeastOnBoxCheckedReturnTrue(){
        mCategorySelector.travelChBox.setChecked(true);
        mCategorySelector.scienceChBox.setChecked(false);
        mCategorySelector.moviesChBox.setChecked(false);

        assertTrue(mCategorySelector.atLeastOnBoxChecked());
    }

    @Test
    public void noBoxCheckedReturnFalse(){
        mCategorySelector.moviesChBox.setChecked(false);
        mCategorySelector.scienceChBox.setChecked(false);
        mCategorySelector.travelChBox.setChecked(false);

        assertFalse(mCategorySelector.atLeastOnBoxChecked());
    }

    @Test
    public void ifTravelBoxCheckedReturnTravel(){
        mCategorySelector.travelChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Travel");

        assertEquals(list, mCategorySelector.getCheckedBoxList());
    }

    @Test
    public void ifMoviesBoxCheckedReturnMovies(){
        mCategorySelector.moviesChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Movies");

        assertEquals(list, mCategorySelector.getCheckedBoxList());
    }

    @Test
    public void ifScienceBoxCheckedReturnScience(){
        mCategorySelector.scienceChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Science");

        assertEquals(list, mCategorySelector.getCheckedBoxList());
    }

    @Test
    public void ifMoviesAndScienceBoxCheckedReturnMoviesScience(){
        mCategorySelector.moviesChBox.setChecked(true);
        mCategorySelector.scienceChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Movies");
        list.add("Science");

        assertEquals(list, mCategorySelector.getCheckedBoxList());
    }

    @Test
    public void ifMoviesAndTravelBoxCheckedReturnMoviesTravel(){
        mCategorySelector.moviesChBox.setChecked(true);
        mCategorySelector.travelChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Movies");
        list.add("Travel");

        assertEquals(list, mCategorySelector.getCheckedBoxList());
    }

    @Test
    public void ifMoviesAndScienceAndTravelBoxCheckedReturnMoviesScienceTravel(){
        mCategorySelector.moviesChBox.setChecked(true);
        mCategorySelector.scienceChBox.setChecked(true);
        mCategorySelector.travelChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Movies");
        list.add("Science");
        list.add("Travel");

        assertEquals(list, mCategorySelector.getCheckedBoxList());
    }

}
