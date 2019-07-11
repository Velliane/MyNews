package com.menard.mynews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.menard.mynews.R;
import com.menard.mynews.model.most_popular.Result;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.ArticlesViewHolder> {

    private List<Result> listResult;
    private Context mContext;

    public MostPopularAdapter(List<Result> list, Context context){
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

        articlesViewHolder.title.setText(result.getSection());
        articlesViewHolder.description.setText(result.getTitle());

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        //LocalDate date = LocalDate.parse(result.getPublishedDate(), formatter);
        articlesViewHolder.date.setText(result.getPublishedDate());
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