package com.skybox.seven.covid.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.NewsArticle;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
     private ArrayList models;
    private Context context;

    public NewsAdapter(Context context, ArrayList models) {
        this.models = models;
        this.context = context;
        Log.e("err", models.toString());
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, null);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
            NewsArticle article = (NewsArticle) models.get(position);
            holder.title.setText(article.getTitle());
            holder.descrView.setText(article.getDescription());
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setData(ArrayList<ContactModel.ContactUsersContacts> models){
        this.models = models;
        notifyDataSetChanged();

    }

    public void setNews(ArrayList<NewsArticle> models){
        this.models = models;
        notifyDataSetChanged();

    }


    public class NewsHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView descrView;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
                this.title = itemView.findViewById(R.id.title);
                this.descrView = itemView.findViewById(R.id.content);

        }
    }



}
