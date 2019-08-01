package com.menard.mynews;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BaseSQLiteAndroidTest {

    private BaseSQLite mBaseSQLite;
    @Before
    public void openDB(){
        mBaseSQLite = new BaseSQLite(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    @Test
    public void checkIfURLExist(){
        String url = "https://www.nytimes.com/";


        mBaseSQLite.addNewURL(url);

        assertTrue( mBaseSQLite.checkURL(url));
    }

    @Test
    public void checkWhenURLAlreadySaved(){
        String url = "https://www.nytimes.com/";
        String url2 = "https://www.nytimes.com/";

        mBaseSQLite.addNewURL(url);
        mBaseSQLite.addNewURL(url2);

        assertTrue( mBaseSQLite.checkURL(url));

    }
}

