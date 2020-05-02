package com.skybox.seven.covid.ui.adapters;

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

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.ContactHolder> {
     private ArrayList models;
    private Context context;

    public static int CONTACT_LIST = 0, NEWS_LIST = 1;
    int listType = CONTACT_LIST;

    public contactAdapter(Context context, ArrayList models, int listType) {
        this.models = models;
        this.context = context;
        this.listType = listType;
        Log.e("err", models.toString());
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        if(listType == NEWS_LIST){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, null);
        }
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        if(listType == CONTACT_LIST){
            ContactModel.ContactUsersContacts model = (ContactModel.ContactUsersContacts) models.get(position);
            holder.title.setText(model.getUser().getFName());
            holder.lContName.setText(model.getUser().getLName());
            holder.descrView.setText(model.getUser().getPhone());
        }else{
            NewsArticle article = (NewsArticle) models.get(position);
            holder.title.setText(article.getTitle());
            holder.descrView.setText(article.getDescription());
        }
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setData(ArrayList<ContactModel.ContactUsersContacts> models){
        this.models = models;
        notifyDataSetChanged();

    }

<<<<<<< HEAD
    public void setNews(ArrayList<NewsArticle> models){
        this.models = models;
        notifyDataSetChanged();

    }

=======
>>>>>>> parent of a4dcd25... Revert "Merge branch 'personalcovid'"

    public class ContactHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public TextView title;
        public TextView descrView;
        public TextView lContName;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            mView =itemView;

            if(listType == CONTACT_LIST){
                this.lContName = itemView.findViewById(R.id.nam);
                this.title = itemView.findViewById(R.id.ContName);
                this.descrView = itemView.findViewById(R.id.ContNumber);
            }else{
                this.title = itemView.findViewById(R.id.title);
                this.descrView = itemView.findViewById(R.id.content);
            }

        }
    }



}
