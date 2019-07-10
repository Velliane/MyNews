package com.menard.mynews.adapter;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.menard.mynews.R;
import com.menard.mynews.model.top_stories.Result;

import java.util.List;


public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ArticlesViewHolder> {

    private List<Result> listResult;
    private Context mContext;

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

        articlesViewHolder.title.setText(result.getSection());
        articlesViewHolder.date.setText(result.getUpdatedDate());
        articlesViewHolder.description.setText(result.getTitle());


        if(result.getMultimedia().size() >= 1) {
            imageURL = result.getMultimedia().get(0).getUrl();
        }

        if(result.getMultimedia().size() > 0) {
            Glide.with(mContext).load(imageURL).placeholder(new ColorDrawable(Color.BLACK)).into(articlesViewHolder.imageView);
        }


    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView title;
        private final TextView date;
        private final TextView description;

        ArticlesViewHolder(@NonNull View itemView){

            super(itemView);
            imageView = itemView.findViewById(R.id.article_image);
            title = itemView.findViewById(R.id.article_title);
            date = itemView.findViewById(R.id.article_date);
            description = itemView.findViewById(R.id.article_description);
        }

    }
}
