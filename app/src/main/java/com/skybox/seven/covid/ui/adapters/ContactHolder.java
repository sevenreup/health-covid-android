package com.skybox.seven.covid.ui.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skybox.seven.covid.R;

public class ContactHolder extends RecyclerView.ViewHolder {

    public TextView contName;
    public TextView contNumber;

    public ContactHolder(@NonNull View itemView) {
        super(itemView);
        this.contName = itemView.findViewById(R.id.ContName);
        this.contNumber = itemView.findViewById(R.id.ContNumber);
    }
}
