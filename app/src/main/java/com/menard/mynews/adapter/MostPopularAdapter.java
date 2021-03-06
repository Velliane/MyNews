package com.menard.mynews.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.menard.mynews.BaseSQLite;
import com.menard.mynews.R;
import com.menard.mynews.model.most_popular.MediaMetadatum;
import com.menard.mynews.model.most_popular.Result;
import com.menard.mynews.utils.DateUtils;


import java.util.List;

import saschpe.android.customtabs.CustomTabsHelper;
import saschpe.android.customtabs.WebViewFallback;


public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.ArticlesViewHolder> {

    private final List<Result> listResult;
    private final Context mContext;
    private BaseSQLite baseSQLite;

    public MostPopularAdapter(List<Result> list, Context context) {
        listResult = list;
        mContext = context;
    }

    @NonNull
    @Override
    public MostPopularAdapter.ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_view_items, viewGroup, false);
        return new MostPopularAdapter.ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder articlesViewHolder, int position) {

        Result result = listResult.get(position);
        String imageURL;
        baseSQLite = new BaseSQLite(mContext);

        articlesViewHolder.section.setText(result.getSection());
        articlesViewHolder.title.setText(result.getTitle());

        // Change format of the date
        articlesViewHolder.date.setText(DateUtils.parseMostPopularDate(result.getPublishedDate()));

        //-- Get the first image in the list of multimedia --
        List<MediaMetadatum> mediaMetadatumList = result.getMedia().get(0).getMediaMetadata();
        imageURL = mediaMetadatumList.get(0).getUrl();

        //-- Add it in the ImageView with Glide --
        if (mediaMetadatumList.size() > 0) {
            Glide.with(mContext).load(imageURL).placeholder(new ColorDrawable(Color.BLACK)).into(articlesViewHolder.imageView);
        } else {
            Glide.with(mContext).load(R.drawable.no_image_available_64).placeholder(new ColorDrawable(Color.BLACK)).into(articlesViewHolder.imageView);
        }

        //-- Check if url already saved in SQLite Database  and change background color accordingly --
        if (baseSQLite.checkURL(result.getUrl())) {
            articlesViewHolder.constraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.blue_grey));
        } else {
            articlesViewHolder.constraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }


    @Override
    public int getItemCount() {
        return listResult.size();
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView section;
        private final TextView date;
        private final TextView title;
        private final ConstraintLayout constraintLayout;

        ArticlesViewHolder(@NonNull final View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.article_image);
            section = itemView.findViewById(R.id.article_section);
            date = itemView.findViewById(R.id.article_date);
            title = itemView.findViewById(R.id.article_title);
            constraintLayout = itemView.findViewById(R.id.article_layout);

            itemView.setOnClickListener(v -> openCustomTabs());
        }

        /**
         * Open CustomTabs
         */
        private void openCustomTabs() {
            final int itemPosition = getAdapterPosition();
            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().addDefaultShareMenuItem()
                    .setToolbarColor(mContext.getResources().getColor(R.color.colorPrimary))
                    .setShowTitle(true)
                    .build();
            CustomTabsHelper.addKeepAliveExtra(mContext, customTabsIntent.intent);

            CustomTabsHelper.openCustomTab(mContext, customTabsIntent,
                    Uri.parse(listResult.get(itemPosition).getUrl()), new WebViewFallback());

            baseSQLite.addNewURL(listResult.get(itemPosition).getUrl());
            notifyItemChanged(itemPosition);
        }

    }

}