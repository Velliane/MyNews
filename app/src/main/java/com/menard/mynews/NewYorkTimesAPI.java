package com.menard.mynews;

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


   // Retrofit retrofit = new Retrofit.Builder()
            //.baseUrl("http://api.nytimes.com/svc/topstories/v2/")
            //.addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.build();
}
