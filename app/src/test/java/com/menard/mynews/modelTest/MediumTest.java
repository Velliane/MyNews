package com.menard.mynews.modelTest;

import com.menard.mynews.model.most_popular.MediaMetadatum;
import com.menard.mynews.model.most_popular.Medium;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MediumTest {

    private Medium mMedium;

    @Before
    public void init(){
        mMedium = new Medium();
    }

    @Test
    public void getMediaMetadata(){
        List<MediaMetadatum> list = new ArrayList<>();

        mMedium.setMediaMetadata(list);

        assertEquals(list, mMedium.getMediaMetadata());
    }
}
