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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.menard.mynews.R;
import com.menard.mynews.model.search.Doc;
import com.menard.mynews.model.search.Multimedium;
import com.menard.mynews.utils.DateUtils;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

import saschpe.android.customtabs.CustomTabsHelper;
import saschpe.android.customtabs.WebViewFallback;

public class SearchedAdapter extends RecyclerView.Adapter<SearchedAdapter.ArticlesViewHolder> {

    private List<Doc> mListResult;
    private Context mContext;

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
        String imageURL = "";

        holder.title.setText(result.getSectionName());
        holder.description.setText(result.getAbstract());
        // TODO change format of the date

        holder.date.setText(DateUtils.parseDate(result.getPubDate()));

        //List<Multimedium> multimediumList = result.getMultimedia();
        //imageURL = multimediumList.get(0).getUrl();
        //if(multimediumList.size() > 0) {
          //  Glide.with(mContext).load(imageURL).placeholder(new ColorDrawable(Color.BLACK)).into(holder.imageView);
        //}



    }

    @Override
    public int getItemCount() {
        return mListResult.size();
    }


    class ArticlesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView title;
        private final TextView date;
        private final TextView description;

        ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.article_image);
            title = itemView.findViewById(R.id.article_title);
            date = itemView.findViewById(R.id.article_date);
            description = itemView.findViewById(R.id.article_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomTabs();
                }
            });
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
        }

    }
}
