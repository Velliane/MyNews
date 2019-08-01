package com.menard.mynews.adapter;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.menard.mynews.BaseSQLite;
import com.menard.mynews.R;
import com.menard.mynews.model.top_stories.Result;
import com.menard.mynews.utils.DateUtils;

import java.util.List;

import saschpe.android.customtabs.CustomTabsHelper;
import saschpe.android.customtabs.WebViewFallback;


public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ArticlesViewHolder>{

    private final List<Result> listResult;
    private final Context mContext;
    private BaseSQLite mBaseSQLite;

    public TopStoriesAdapter(List<Result> list, Context context){
        listResult = list;
        mContext = context;

    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_view_items, viewGroup, false);
        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder articlesViewHolder, int position) {

        Result result = listResult.get(position);
        String imageURL = "";
        mBaseSQLite = new BaseSQLite(mContext);

        articlesViewHolder.title.setText(result.getSection());
        articlesViewHolder.description.setText(result.getTitle());

        //-- Change the format of the date --
        articlesViewHolder.date.setText(DateUtils.parseZonedDate(result.getUpdatedDate()));

        if(mBaseSQLite.checkURL(result.getUrl())){
            articlesViewHolder.relativeLayout.setBackgroundColor(mContext.getResources().getColor(R.color.blue_grey));
        }else {
            articlesViewHolder.relativeLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

        //-- Get the first image in the list of multimedia --
        if(result.getMultimedia().size() >= 1) {
            imageURL = result.getMultimedia().get(0).getUrl();
        }
        //-- Add it in the ImageView with Glide --
        if(result.getMultimedia().size() > 0) {
            Glide.with(mContext).load(imageURL).placeholder(new ColorDrawable(Color.BLACK)).into(articlesViewHolder.imageView);
        }else {
            Glide.with(mContext).load(mContext.getResources().getIdentifier("no_image_available_64", "drawable", mContext.getPackageName())).placeholder(new ColorDrawable(Color.BLACK)).into(articlesViewHolder.imageView);
        }



    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView title;
        private final TextView date;
        private final TextView description;
        private final RelativeLayout relativeLayout;

        ArticlesViewHolder(@NonNull final View itemView){

            super(itemView);
            imageView = itemView.findViewById(R.id.article_image);
            title = itemView.findViewById(R.id.article_section);
            date = itemView.findViewById(R.id.article_date);
            description = itemView.findViewById(R.id.article_title);
            relativeLayout = itemView.findViewById(R.id.article_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomTabs();
                }
            });
        }

        /**
         * Open CustomTabs
         */
        private void openCustomTabs(){
            int itemPosition = getAdapterPosition();

            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().addDefaultShareMenuItem()
                    .setToolbarColor(mContext.getResources().getColor(R.color.colorPrimary))
                    .setShowTitle(true)
                    .build();
            CustomTabsHelper.addKeepAliveExtra(mContext, customTabsIntent.intent);

            CustomTabsHelper.openCustomTab(mContext, customTabsIntent,
                    Uri.parse(listResult.get(itemPosition).getUrl()), new WebViewFallback());
            mBaseSQLite.addNewURL(listResult.get(itemPosition).getUrl());
            notifyDataSetChanged();
        }

    }
}
