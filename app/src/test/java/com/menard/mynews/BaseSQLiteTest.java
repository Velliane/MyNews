package com.menard.mynews;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.RuntimeEnvironment;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BaseSQLiteTest {

    private BaseSQLite mBaseSQLite = Mockito.mock(BaseSQLite.class);

    @Test
    public void checkIfURLExist(){
        String url = "https://www.nytimes.com/";

        //mBaseSQLite.addNewURL(url);
        doNothing().when(mBaseSQLite).addNewURL(url);

        assertTrue( mBaseSQLite.checkURL(url));
    }
}
