package com.menard.mynews.controller.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import com.menard.mynews.model.most_popular.ArticleMostPopular;
import com.menard.mynews.model.most_popular.Result;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NewYorkTimesAPI;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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


        Retrofit retrofit = getRetrofit();

        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleMostPopular> call = newYorkTimesAPI.getMostPopular(Constants.API_KEY);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        call.enqueue(new Callback<ArticleMostPopular>() {
            @Override
            public void onResponse(@NonNull Call<ArticleMostPopular> call,@NonNull Response<ArticleMostPopular> response) {

                if (response.isSuccessful()) {
                    ArticleMostPopular articleMostPopular = response.body();
                    assert articleMostPopular != null;
                    List<Result> articleList = articleMostPopular.getResults();

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    list.setLayoutManager(layoutManager);
                    MostPopularAdapter adapter = new MostPopularAdapter(articleList, getContext());
                    list.setAdapter(adapter);

                    progressDialog.dismiss();
                }else {
                    Log.e("TAG", "response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleMostPopular> call,@NonNull Throwable t) {
                    t.printStackTrace();
            }
        });
        return result;
    }

    /**
     * Configure Retrofit
     * @return retrofit
     */
    private Retrofit getRetrofit(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl("http://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
