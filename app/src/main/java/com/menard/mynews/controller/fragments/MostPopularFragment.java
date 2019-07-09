package com.menard.mynews.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.menard.mynews.R;
import com.menard.mynews.adapter.MostPopularAdapter;
import com.menard.mynews.model.most_popular.Article;
import com.menard.mynews.model.most_popular.ArticleMostPopular;
import com.menard.mynews.utils.NewYorkTimesAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MostPopularFragment extends Fragment {


    public MostPopularFragment(){}

    public static MostPopularFragment newInstance() {
        return new MostPopularFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);
        final RecyclerView list = result.findViewById(R.id.fragment_list);

        //-- Get list of articles --
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleMostPopular> call = newYorkTimesAPI.getMostPopular("yHD5uUtRQngsZLyVUwKbVKSxvEihrB0m");

        call.enqueue(new Callback<ArticleMostPopular>() {
            @Override
            public void onResponse(@NonNull Call<ArticleMostPopular> call,@NonNull Response<ArticleMostPopular> response) {

                ArticleMostPopular articleMostPopular = response.body();
                assert articleMostPopular != null;
                List<Article> articleList = articleMostPopular.getArticles();

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                list.setLayoutManager(layoutManager);
                MostPopularAdapter adapter = new MostPopularAdapter(articleList, getContext());
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ArticleMostPopular> call,@NonNull Throwable t) {

            }
        });



        return result;
    }
}
