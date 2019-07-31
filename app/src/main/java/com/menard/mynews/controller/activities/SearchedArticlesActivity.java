package com.menard.mynews.controller.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.menard.mynews.R;
import com.menard.mynews.adapter.SearchedAdapter;
import com.menard.mynews.model.search.ArticleSearched;
import com.menard.mynews.model.search.Doc;
import com.menard.mynews.utils.Constants;
import com.menard.mynews.utils.NewYorkTimesAPI;
import com.menard.mynews.utils.RetrofitService;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchedArticlesActivity extends AppCompatActivity {

    private RecyclerView mListArticles;
    private final RetrofitService retrofitService = new RetrofitService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_articles);

        //-- Toolbar --
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Articles");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //-- Get extra from intent --
        Intent intent = getIntent();
        String keyword = intent.getStringExtra(Constants.EXTRA_KEYWORD);
        String section = intent.getStringExtra(Constants.EXTRA_SECTION);
        String beginDate = intent.getStringExtra(Constants.EXTRA_BEGIN_DATE);
        String endDate = intent.getStringExtra(Constants.EXTRA_END_DATE);


        mListArticles = findViewById(R.id.activity_search_list_articles);

        //-- Get list of articles --
        Retrofit retrofit = retrofitService.getRetrofit();
        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleSearched> call = newYorkTimesAPI.getSearched(keyword, section, beginDate, endDate,Constants.API_KEY);

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
                    showAlertDialog();
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

    /**
     * Show an alertDialog in case of there's no articles available
     */
    public void showAlertDialog(){
        new AlertDialog.Builder(this).setTitle("No articles find")
                .setMessage("There is no articles available for your research")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                }).show();
    }


}
