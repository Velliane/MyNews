package com.menard.mynews.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.menard.mynews.R;
import com.menard.mynews.adapter.SearchedAdapter;
import com.menard.mynews.model.search.ArticleSearched;
import com.menard.mynews.model.search.Doc;
import com.menard.mynews.utils.NewYorkTimesAPI;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchedArticlesActivity extends AppCompatActivity {

    private RecyclerView mListArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_articles);

        //-- Toolbar --
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Articles");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //-- Get extra from intent --
        Intent intent = getIntent();
        String textSearched = intent.getStringExtra("QUERY");
        String section = intent.getStringExtra("SECTION");


        mListArticles = findViewById(R.id.activity_search_list_articles);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        //-- Get list of articles --
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleSearched> call = newYorkTimesAPI.getSearched(textSearched, section, null, null,"yHD5uUtRQngsZLyVUwKbVKSxvEihrB0m");

        call.enqueue(new Callback<ArticleSearched>() {
            @Override
            public void onResponse(@NonNull Call<ArticleSearched> call, @NonNull Response<ArticleSearched> response) {

                if (response.isSuccessful()) {
                    ArticleSearched articleSearched = response.body();
                    assert articleSearched != null;
                    List<Doc> articleList = articleSearched.getResponse().getDocs();

                    LinearLayoutManager layoutManager = new LinearLayoutManager(SearchedArticlesActivity.this);
                    mListArticles.setLayoutManager(layoutManager);
                    SearchedAdapter adapter = new SearchedAdapter(articleList, SearchedArticlesActivity.this);
                    mListArticles.setAdapter(adapter);
                }else {
                    Log.e("TAG", "response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleSearched> call,@NonNull Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
