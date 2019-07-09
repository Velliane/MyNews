package com.menard.mynews.controller.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menard.mynews.R;
import com.menard.mynews.adapter.TopStoriesAdapter;
import com.menard.mynews.model.top_stories.ArticleTopStories;
import com.menard.mynews.model.top_stories.Result;
import com.menard.mynews.utils.NewYorkTimesAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopStoriesFragment extends Fragment {


    public TopStoriesFragment(){}

    public static TopStoriesFragment newInstance() {
        return new TopStoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);
        final RecyclerView list = result.findViewById(R.id.fragment_list);

        //-- Get list of articles --
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleTopStories> call = newYorkTimesAPI.getTopStories("home", "yHD5uUtRQngsZLyVUwKbVKSxvEihrB0m");

        call.enqueue(new Callback<ArticleTopStories>() {
            @Override
            public void onResponse(@NonNull Call<ArticleTopStories> call,@NonNull Response<ArticleTopStories> response) {

                ArticleTopStories articleTopStories = response.body();
                assert articleTopStories != null;
                List<Result> articleList = articleTopStories.getResults();

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                list.setLayoutManager(layoutManager);
                TopStoriesAdapter adapter = new TopStoriesAdapter(articleList, getContext());
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ArticleTopStories> call,@NonNull Throwable t) {

            }
        });

        return result;
    }
}
