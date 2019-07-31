package com.menard.mynews.modelTest;

import com.menard.mynews.model.search.ArticleSearched;
import com.menard.mynews.model.search.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArticleSearchedTest {

    private ArticleSearched mArticleSearched = new ArticleSearched();

    @Test
    public void getResponse(){
        Response response = new Response();

        mArticleSearched.setResponse(response);

        assertEquals(response, mArticleSearched.getResponse());
    }
}
