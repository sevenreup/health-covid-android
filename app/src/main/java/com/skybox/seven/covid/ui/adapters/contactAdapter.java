package com.skybox.seven.covid.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skybox.seven.covid.R;

import java.util.ArrayList;

public class contactAdapter extends RecyclerView.Adapter<ContactHolder> {
    ArrayList<ContactModel> models;

    public contactAdapter(ArrayList<ContactModel> models) {
        this.models = models;
        Log.e("err", models.toString());
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
        holder.contNumber.setText(models.get(position).getPhone());
        Log.e("Big error", "Should not be here");
    }


    @Override
    public int getItemCount() {
        return models.size();
    }
}
