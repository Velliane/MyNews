package com.menard.mynews.utils;

import com.menard.mynews.model.top_stories.ArticleTopStories;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Retrofit;


public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{subject}.json")
    Call<ArticleTopStories> getTopStories(@Path("subject")String subject, @Query("api-key") String value);


   Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    //Call<ArticleTopStories> resultCall= retrofit.create(NewYorkTimesAPI.class).getTopStories("arts", "yHD5uUtRQngsZLyVUwKbVKSxvEihrB0m");
        //resultCall.enqueue(new Callback<ArticleTopStories>() {
        //@Override
        //public void onResponse(Call<ArticleTopStories> call, Response<ArticleTopStories> response) {
        //}

        //@Override
        //public void onFailure(Call<ArticleTopStories> call, Throwable t) {

        //}
    //});

}
