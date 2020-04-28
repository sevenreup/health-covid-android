package com.skybox.seven.covid.ui.adapters;

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

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.ViewHolder> {
    ArrayList models;
    public static int CONTACT_LIST = 0, NEWS_LIST = 1;
    int listType = CONTACT_LIST;

    public contactAdapter(ArrayList models, int listType) {
        this.models = models;
        this.listType = listType;
        Log.e("err", models.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        if(listType == NEWS_LIST){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, null);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(listType == CONTACT_LIST){
            ContactModel model = (ContactModel) models.get(position);
            holder.titleView.setText(model.getName());
            holder.descrView.setText(model.getPhone());
        }else{
            NewsArticle article = (NewsArticle) models.get(position);
            holder.titleView.setText(article.getTitle());
            holder.descrView.setText(article.getDescription());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView descrView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(listType == CONTACT_LIST){
                this.titleView = itemView.findViewById(R.id.ContName);
                this.descrView = itemView.findViewById(R.id.ContNumber);
            }else{
                this.titleView = itemView.findViewById(R.id.title);
                this.descrView = itemView.findViewById(R.id.content);
            }
        }
    }


    @Override
    public int getItemCount() {
        return models.size();
    }
}
