package com.menard.mynews.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menard.mynews.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ArticlesViewHolder> {


    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_view_items, viewGroup, false);
        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder articlesViewHolder, int position) {



        articlesViewHolder.title.setText("Titre de l'article");
        articlesViewHolder.date.setText("02/07/2019");
        articlesViewHolder.description.setText("Courte description");

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
