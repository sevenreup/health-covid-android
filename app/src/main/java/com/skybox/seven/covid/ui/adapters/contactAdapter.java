package com.skybox.seven.covid.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skybox.seven.covid.R;

import java.util.ArrayList;

public class contactAdapter extends RecyclerView.Adapter<ContactHolder> {
    Context context;
    ArrayList<ContactModel> models;

    public contactAdapter(Context context, ArrayList<ContactModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        holder.contName.setText(models.get(position).getName());
        holder.contNumber.setText(models.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return models.size();
    }
}
