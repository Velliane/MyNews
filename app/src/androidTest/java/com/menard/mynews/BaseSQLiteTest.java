package com.menard.mynews;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.RuntimeEnvironment;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BaseSQLiteTest {

    private BaseSQLite mBaseSQLite;

    @Before
    public void createDB(){
    Context context = ;
    }

    @Test
    public void checkIfURLExist(){
        String url = "https://www.nytimes.com/";

        //mBaseSQLite.addNewURL(url);
        Mockito.doNothing().when(mBaseSQLite).addNewURL(url);

        assertTrue( mBaseSQLite.checkURL(url));
    }
}
