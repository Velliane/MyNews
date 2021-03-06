package com.menard.mynews.controller.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.menard.mynews.R;
import com.menard.mynews.adapter.TopStoriesAdapter;
import com.menard.mynews.model.top_stories.ArticleTopStories;
import com.menard.mynews.model.top_stories.Result;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NewYorkTimesAPI;
import com.menard.mynews.utils.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TopStoriesFragment extends Fragment {

    /**
     * Retrofit Service
     */
    private final RetrofitService retrofitService = new RetrofitService();
    /**
     * Recycler View
     */
    private RecyclerView list;

    List<Result> articleList;

    public TopStoriesFragment() {
    }

    public static TopStoriesFragment newInstance() {
        return new TopStoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);
        list = result.findViewById(R.id.fragment_list);
        final TextView textView = result.findViewById(R.id.fragment_txtview);
        ProgressBar progressBar = result.findViewById(R.id.fragment_progress);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);

        Retrofit retrofit = retrofitService.getRetrofit();
        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleTopStories> call = newYorkTimesAPI.getTopStories("home", Constants.API_KEY);

        call.enqueue(new Callback<ArticleTopStories>() {
            @Override
            public void onResponse(@NonNull Call<ArticleTopStories> call, @NonNull Response<ArticleTopStories> response) {

                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    ArticleTopStories articleTopStories = response.body();
                    assert articleTopStories != null;
                    List<Result> articleList = articleTopStories.getResults();
                    configureRecyclerView(articleList);
                } else {
                    Log.e("TAG", "response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleTopStories> call, @NonNull Throwable t) {
                t.printStackTrace();
                //-- Show error message --
                list.setVisibility(View.GONE);
                progressBar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });

        return result;

    }

    /**
     * Configure the RecyclerView
     *
     * @param articleList the list of Result
     */
    private void configureRecyclerView(List<Result> articleList) {
        TopStoriesAdapter adapter = new TopStoriesAdapter(articleList, getContext());
        list.setAdapter(adapter);
    }

}







