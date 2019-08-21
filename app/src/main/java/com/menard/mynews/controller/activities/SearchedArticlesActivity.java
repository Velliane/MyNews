package com.menard.mynews.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchedArticlesActivity extends AppCompatActivity {

    private RecyclerView mListArticles;
    private final RetrofitService retrofitService = new RetrofitService();
    private Context mContext;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_articles);
        mContext = this;
        mProgressBar = findViewById(R.id.searched_articles_activity_progress_bar);
        mListArticles = findViewById(R.id.activity_search_list_articles);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchedArticlesActivity.this);
        mListArticles.setLayoutManager(layoutManager);

        //-- Toolbar --
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Articles");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //-- Get extra from intent --
        Intent intent = getIntent();
        String keyword = intent.getStringExtra(Constants.EXTRA_KEYWORD);
        String section = intent.getStringExtra(Constants.EXTRA_SECTION);
        String beginDate = intent.getStringExtra(Constants.EXTRA_BEGIN_DATE);
        String endDate = intent.getStringExtra(Constants.EXTRA_END_DATE);

        //-- Get list of articles --
        Retrofit retrofit = retrofitService.getRetrofit();
        NewYorkTimesAPI newYorkTimesAPI = retrofit.create(NewYorkTimesAPI.class);
        Call<ArticleSearched> call = newYorkTimesAPI.getSearched(keyword, section, beginDate, endDate, Constants.API_KEY);

        call.enqueue(new Callback<ArticleSearched>() {
            @Override
            public void onResponse(@NonNull Call<ArticleSearched> call, @NonNull Response<ArticleSearched> response) {

                if (response.isSuccessful()) {

                    mProgressBar.setVisibility(View.INVISIBLE);
                    ArticleSearched articleSearched = response.body();
                    assert articleSearched != null;
                    List<Doc> articleList = articleSearched.getResponse().getDocs();

                    //-- If no articles available --
                    if (articleList.size() == 0) {
                        showAlertDialog();
                    }

                    SearchedAdapter adapter = new SearchedAdapter(articleList, SearchedArticlesActivity.this);
                    mListArticles.setAdapter(adapter);
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    showAlertDialog();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleSearched> call, @NonNull Throwable t) {
                t.printStackTrace();
                mProgressBar.setVisibility(View.INVISIBLE);

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
    private void showAlertDialog() {
        new AlertDialog.Builder(mContext).setTitle(R.string.search_no_articles_title)
                .setMessage(R.string.search_no_articles_text)
                .setPositiveButton("Ok", (dialog, which) -> onBackPressed()).show();
    }


}
