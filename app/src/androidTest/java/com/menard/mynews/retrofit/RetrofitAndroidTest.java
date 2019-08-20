package com.menard.mynews.retrofit;

import com.menard.mynews.model.most_popular.ArticleMostPopular;
import com.menard.mynews.model.search.ArticleSearched;
import com.menard.mynews.model.search.Doc;
import com.menard.mynews.model.top_stories.ArticleTopStories;
import com.menard.mynews.model.top_stories.Result;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NewYorkTimesAPI;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.NetworkBehavior;

public class RetrofitAndroidTest {

    private MockRetrofit mMockRetrofit;

    @Before
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://test.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior networkBehavior = NetworkBehavior.create();

        mMockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build();
    }

    @Test
    public void testGetTopStories() throws IOException {
        BehaviorDelegate<NewYorkTimesAPI> delegate = mMockRetrofit.create(NewYorkTimesAPI.class);
        NewYorkTimesAPI mockNewYorkTimesAPI = new MockNewYorkTimesAPI(delegate);

        Call<ArticleTopStories> call = mockNewYorkTimesAPI.getTopStories("home", Constants.API_KEY);
        Response<ArticleTopStories> response = call.execute();

        ArticleTopStories articleTopStories = response.body();
        assert articleTopStories != null;
        List<Result> results = articleTopStories.getResults();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Arts", results.get(0).getSection());
        Assert.assertEquals("Painting...", results.get(0).getTitle());
        Assert.assertEquals("20/08/19", results.get(0).getUpdatedDate());
    }

    @Test
    public void testGetMostPopular() throws IOException {
        BehaviorDelegate<NewYorkTimesAPI> delegate = mMockRetrofit.create(NewYorkTimesAPI.class);
        NewYorkTimesAPI mockNewYorkTimesAPI = new MockNewYorkTimesAPI(delegate);

        Call<ArticleMostPopular> call = mockNewYorkTimesAPI.getMostPopular(Constants.API_KEY);
        Response<ArticleMostPopular> response = call.execute();

        ArticleMostPopular articleMostPopular = response.body();
        assert articleMostPopular != null;
        List<com.menard.mynews.model.most_popular.Result> results = articleMostPopular.getResults();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Travel", results.get(0).getSection());
        Assert.assertEquals("Traveling in Japan...", results.get(0).getTitle());
        Assert.assertEquals("20/08/19", results.get(0).getPublishedDate());
    }

    @Test
    public void testGetSearched() throws IOException {
        BehaviorDelegate<NewYorkTimesAPI> delegate = mMockRetrofit.create(NewYorkTimesAPI.class);
        NewYorkTimesAPI mockNewYorkTimesAPI = new MockNewYorkTimesAPI(delegate);

        Call<ArticleSearched> call = mockNewYorkTimesAPI.getSearched("Japan","Travel", null, null, Constants.API_KEY);
        Response<ArticleSearched> response = call.execute();

        ArticleSearched articleSearched = response.body();
        assert  articleSearched != null;
        List<Doc> docs = articleSearched.getResponse().getDocs();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Travel", docs.get(0).getSectionName());
        Assert.assertEquals("Traveling in Japan...", docs.get(0).getAbstract());
        Assert.assertEquals("20/08/19", docs.get(0).getPubDate());
    }
}
