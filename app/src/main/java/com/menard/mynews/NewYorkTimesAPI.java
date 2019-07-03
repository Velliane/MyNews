package com.menard.mynews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{subject}.json")
    Call<ResultTopStories> getTopStories(@Path("subject")String subject, @Query("api-key") String value);
}
