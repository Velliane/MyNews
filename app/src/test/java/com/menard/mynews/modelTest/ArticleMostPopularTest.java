package com.menard.mynews.modelTest;

import com.menard.mynews.model.most_popular.ArticleMostPopular;
import com.menard.mynews.model.most_popular.Result;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArticleMostPopularTest {

    private ArticleMostPopular mArticleMostPopular;

    @Before
    public void init(){
        mArticleMostPopular = new ArticleMostPopular();
    }

    @Test
    public void getResult(){
        List<Result> list = new ArrayList<>();

        mArticleMostPopular.setResults(list);

        assertEquals(list, mArticleMostPopular.getResults());
    }
}
