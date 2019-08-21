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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.menard.mynews.BaseSQLite;
import com.menard.mynews.R;
import com.menard.mynews.model.search.Doc;
import com.menard.mynews.model.search.Multimedium;
import com.menard.mynews.utils.DateUtils;

import java.util.List;

import saschpe.android.customtabs.CustomTabsHelper;
import saschpe.android.customtabs.WebViewFallback;

public class SearchedAdapter extends RecyclerView.Adapter<SearchedAdapter.ArticlesViewHolder> {

    private final List<Doc> mListResult;
    private final Context mContext;
    private BaseSQLite baseSQLite;

    public SearchedAdapter(List<Doc> listResult, Context context){
        mListResult = listResult;
        mContext = context;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_view_items, viewGroup, false);
        return new SearchedAdapter.ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {

        Doc result = mListResult.get(position);
        String imageURL;
        baseSQLite = new BaseSQLite(mContext);

        holder.section.setText(result.getSectionName());
        holder.title.setText(result.getAbstract());

        //-- Change the format of the date --
        holder.date.setText(DateUtils.parseSearchedDate(result.getPubDate()));

        //-- Add image in the ImageView with Glide --
        List<Multimedium> multimediumList = result.getMultimedia();
        if(multimediumList.size() > 0) {
            imageURL = multimediumList.get(0).getUrl();
            Glide.with(mContext).load("https://static01.nyt.com/"+imageURL).placeholder(new ColorDrawable(Color.BLACK)).into(holder.imageView);
        }else {
            Glide.with(mContext).load(R.drawable.no_image_available_64).placeholder(new ColorDrawable(Color.BLACK)).into(holder.imageView);
        }

        //-- Check if url already saved in SQLite Database  and change background color accordingly --
        if(baseSQLite.checkURL(result.getWebUrl())){
            holder.constraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.blue_grey));
        }else {
            holder.constraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return mListResult.size();
    }


    class ArticlesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView section;
        private final TextView date;
        private final TextView title;
        private final ConstraintLayout constraintLayout;

        ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.article_image);
            section = itemView.findViewById(R.id.article_section);
            date = itemView.findViewById(R.id.article_date);
            title = itemView.findViewById(R.id.article_title);
            constraintLayout = itemView.findViewById(R.id.article_layout);

            itemView.setOnClickListener(v -> openCustomTabs());
        }

        /**
         * Open the CustomTabs or WebView
         */
        private void openCustomTabs(){
            int itemPosition = getAdapterPosition();

            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().addDefaultShareMenuItem()
                    .setToolbarColor(mContext.getResources().getColor(R.color.colorPrimary))
                    .setShowTitle(true)
                    .build();
            CustomTabsHelper.addKeepAliveExtra(mContext, customTabsIntent.intent);

            CustomTabsHelper.openCustomTab(mContext, customTabsIntent,
                    Uri.parse(mListResult.get(itemPosition).getWebUrl()), new WebViewFallback());

            baseSQLite.addNewURL(mListResult.get(itemPosition).getWebUrl());
            notifyItemChanged(itemPosition);
        }

    }
}
