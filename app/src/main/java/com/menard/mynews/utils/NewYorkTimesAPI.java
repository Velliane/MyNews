package com.menard.mynews.utils;

import com.menard.mynews.model.most_popular.ArticleMostPopular;
import com.menard.mynews.model.search.ArticleSearched;
import com.menard.mynews.model.top_stories.ArticleTopStories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{subject}.json")
    Call<ArticleTopStories> getTopStories(@Path("subject")String subject, @Query("api-key") String value);

    @GET("svc/mostpopular/v2/viewed/7.json")
    Call<ArticleMostPopular> getMostPopular(@Query("api-key") String value);

    @GET("svc/search/v2/articlesearch.json")
    Call<ArticleSearched> getSearched(@Query("q") String section, @Query("api-key") String value);



}
