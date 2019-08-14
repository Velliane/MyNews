package com.menard.mynews;

import androidx.annotation.UiThread;
import androidx.test.annotation.UiThreadTest;
import androidx.test.platform.app.InstrumentationRegistry;
import com.menard.mynews.view.CategorySelectorView;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


@RunWith(JUnit4ClassRunner.class)
public class CategorySelectorViewAndroidTest {

    private CategorySelectorView mCategorySelectorView;

    @Before
    public void init(){
        mCategorySelectorView = new CategorySelectorView(InstrumentationRegistry.getInstrumentation().getTargetContext());

    }


    @UiThreadTest
    @Test
    public void atLeastOnBoxCheckedReturnTrue(){
        mCategorySelectorView.travelChBox.setChecked(true);
        mCategorySelectorView.scienceChBox.setChecked(false);
        mCategorySelectorView.moviesChBox.setChecked(false);

        assertTrue(mCategorySelectorView.atLeastOnBoxChecked());
    }

    @UiThreadTest
    @Test
    public void noBoxCheckedReturnFalse(){
        mCategorySelectorView.moviesChBox.setChecked(false);
        mCategorySelectorView.scienceChBox.setChecked(false);
        mCategorySelectorView.travelChBox.setChecked(false);

        assertFalse(mCategorySelectorView.atLeastOnBoxChecked());
    }

    @UiThreadTest
    @Test
    public void ifTravelBoxCheckedReturnTravel(){
        mCategorySelectorView.travelChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Travel");

        assertEquals(list, mCategorySelectorView.getCheckedBoxList());
    }

    @UiThreadTest
    @Test
    public void ifMoviesBoxCheckedReturnMovies(){
        mCategorySelectorView.moviesChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Movies");

        assertEquals(list, mCategorySelectorView.getCheckedBoxList());
    }

    @UiThreadTest
    @Test
    public void ifScienceBoxCheckedReturnScience(){
        mCategorySelectorView.scienceChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Science");

        assertEquals(list, mCategorySelectorView.getCheckedBoxList());
    }

    @UiThreadTest
    @Test
    public void ifMoviesAndScienceBoxCheckedReturnMoviesScience(){
        mCategorySelectorView.moviesChBox.setChecked(true);
        mCategorySelectorView.scienceChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Movies");
        list.add("Science");

        assertEquals(list, mCategorySelectorView.getCheckedBoxList());
    }

    @UiThreadTest
    @Test
    public void ifMoviesAndTravelBoxCheckedReturnMoviesTravel(){
        mCategorySelectorView.moviesChBox.setChecked(true);
        mCategorySelectorView.travelChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Movies");
        list.add("Travel");

        assertEquals(list, mCategorySelectorView.getCheckedBoxList());
    }

    @UiThreadTest
    @Test
    public void ifMoviesAndScienceAndTravelBoxCheckedReturnMoviesScienceTravel(){
        mCategorySelectorView.moviesChBox.setChecked(true);
        mCategorySelectorView.scienceChBox.setChecked(true);
        mCategorySelectorView.travelChBox.setChecked(true);

        List<String> list = new ArrayList<>();
        list.add("Movies");
        list.add("Science");
        list.add("Travel");

        assertEquals(list, mCategorySelectorView.getCheckedBoxList());
    }

}
