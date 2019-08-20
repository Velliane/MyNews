package com.menard.mynews.retrofit;

import androidx.annotation.Nullable;

import com.bumptech.glide.util.ContentLengthInputStream;
import com.menard.mynews.model.most_popular.ArticleMostPopular;
import com.menard.mynews.model.search.ArticleSearched;
import com.menard.mynews.model.search.Doc;
import com.menard.mynews.model.search.Response;
import com.menard.mynews.model.top_stories.ArticleTopStories;
import com.menard.mynews.model.top_stories.Result;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NewYorkTimesAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockNewYorkTimesAPI implements NewYorkTimesAPI {

    private final BehaviorDelegate<NewYorkTimesAPI> delegate;

    public MockNewYorkTimesAPI(BehaviorDelegate<NewYorkTimesAPI> service){
        this.delegate = service;
    }

    @Override
    public Call<ArticleTopStories> getTopStories(String subject, String api_key) {
        ArticleTopStories articleTopStories = new ArticleTopStories();

        List<Result> list = new ArrayList<>();

        Result result = new Result();
        result.setSection("Arts");
        result.setTitle("Painting...");
        result.setUpdatedDate("20/08/19");
        list.add(result);

        articleTopStories.setResults(list);
        return  delegate.returningResponse(articleTopStories).getTopStories("home", Constants.API_KEY);
    }

    @Override
    public Call<ArticleMostPopular> getMostPopular(String api_key) {
        ArticleMostPopular articleMostPopular = new ArticleMostPopular();

        List<com.menard.mynews.model.most_popular.Result> list = new ArrayList<>();

        com.menard.mynews.model.most_popular.Result result = new com.menard.mynews.model.most_popular.Result();
        result.setSection("Travel");
        result.setTitle("Traveling in Japan...");
        result.setPublishedDate("20/08/19");
        list.add(result);

        articleMostPopular.setResults(list);
        return delegate.returningResponse(articleMostPopular).getMostPopular(Constants.API_KEY);
    }

    @Override
    public Call<ArticleSearched> getSearched(String keywords, String filterSection, @Nullable String beginDate, @Nullable String endDate, String api_key) {
        ArticleSearched articleSearched = new ArticleSearched();

        Response response = new Response();
        List<Doc> docs = new ArrayList<>();

        Doc doc = new Doc();
        doc.setSectionName("Travel");
        doc.setAbstract("Traveling in Japan...");
        doc.setPubDate("20/08/19");
        docs.add(doc);

        response.setDocs(docs);
        articleSearched.setResponse(response);
        return delegate.returningResponse(articleSearched).getSearched("Japan", "Travel", null, null, Constants.API_KEY);

    }
}
