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
import com.menard.mynews.adapter.MostPopularAdapter;
import com.menard.mynews.model.most_popular.ArticleMostPopular;
import com.menard.mynews.model.most_popular.Result;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NewYorkTimesAPI;
import com.menard.mynews.utils.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MostPopularFragment extends Fragment {


    /**
     * Recycler View
     */
    private RecyclerView list;
    /**
     * Retrofit Service
     */
    private final RetrofitService retrofitService = new RetrofitService();

    public MostPopularFragment() {
    }

    public static MostPopularFragment newInstance() {
        return new MostPopularFragment();
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
        Call<ArticleMostPopular> call = newYorkTimesAPI.getMostPopular(Constants.API_KEY);

        call.enqueue(new Callback<ArticleMostPopular>() {
            @Override
            public void onResponse(@NonNull Call<ArticleMostPopular> call, @NonNull Response<ArticleMostPopular> response) {

                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    ArticleMostPopular articleMostPopular = response.body();
                    assert articleMostPopular != null;
                    List<Result> articleList = articleMostPopular.getResults();
                    configureRecyclerView(articleList);

                } else {
                    Log.e("TAG", "response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleMostPopular> call, @NonNull Throwable t) {
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
        MostPopularAdapter adapter = new MostPopularAdapter(articleList, getContext());
        list.setAdapter(adapter);
    }

}
