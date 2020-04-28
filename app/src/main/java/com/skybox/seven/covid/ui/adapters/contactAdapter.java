package com.skybox.seven.covid.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skybox.seven.covid.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.ViewHolder> {
     ArrayList<ContactModel.ContactUsersContacts> models;
    private Context context;
    

    public contactAdapter(Context context, ArrayList<ContactModel.ContactUsersContacts> models) {
        this.models = models;
        this.context = context;
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
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        holder.contName.setText(models.get(position).getUser().getFName());
        holder.lContName.setText(models.get(position).getUser().getLName());
        holder.contNumber.setText(models.get(position).getUser().getPhone());

    public class ViewHolder extends RecyclerView.ViewHolder{
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

    public void setData(ArrayList<ContactModel.ContactUsersContacts> models){
        this.models = models;
        notifyDataSetChanged();

    }


    public class ContactHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public TextView contName;
        public TextView contNumber;
        public TextView lContName;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            mView =itemView;

            this.lContName = itemView.findViewById(R.id.nam);
            this.contName = itemView.findViewById(R.id.ContName);
            this.contNumber = itemView.findViewById(R.id.ContNumber);
        }
    }



}
