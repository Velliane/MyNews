package com.menard.mynews;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BaseSQLiteAndroidTest {

    private BaseSQLite mBaseSQLite;
    @Before
    public void openDB(){
        mBaseSQLite = new BaseSQLite(InstrumentationRegistry.getInstrumentation().getContext());
    }

    @Test
    public void checkIfURLExist(){
        String url = "https://www.nytimes.com/";


        mBaseSQLite.addNewURL(url);

        assertTrue( mBaseSQLite.checkURL(url));
    }
}

