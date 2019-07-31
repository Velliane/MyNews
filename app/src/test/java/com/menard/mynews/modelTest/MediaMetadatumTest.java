package com.menard.mynews.modelTest;

import com.menard.mynews.model.most_popular.MediaMetadatum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MediaMetadatumTest {

    private final MediaMetadatum mMediaMetadatum = new MediaMetadatum();

    @Test
    public void getUrl(){
        String url = "https://static01.nyt.com/images/2019/07/25/multimedia/23armstrong/23armstrong-thumbStandard.jpg";

        mMediaMetadatum.setUrl(url);

        assertEquals("https://static01.nyt.com/images/2019/07/25/multimedia/23armstrong/23armstrong-thumbStandard.jpg", mMediaMetadatum.getUrl());
    }
}
