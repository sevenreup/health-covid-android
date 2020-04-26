package com.skybox.seven.covid.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skybox.seven.covid.R;

import java.util.ArrayList;

public class ContactRequestAdapter extends RecyclerView.Adapter <ContactRequestAdapter.CRModelHolder>{
    ArrayList<ContactRequestModel> models;

    public ContactRequestAdapter(ArrayList<ContactRequestModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public CRModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_row, null);
        return new CRModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CRModelHolder holder, int position) {
        holder.contName.setText(models.get(position).getName());
        holder.contNumber.setText(models.get(position).getPhone());
    }


    @Override
    public int getItemCount() {
        return models.size();
    }
    


    public class CRModelHolder extends RecyclerView.ViewHolder {

        public TextView contName;
        public TextView contNumber;

        public CRModelHolder(@NonNull View itemView) {
            super(itemView);
            this.contName = itemView.findViewById(R.id.ContName);
            this.contNumber = itemView.findViewById(R.id.ContNumber);
        }
    }
}
