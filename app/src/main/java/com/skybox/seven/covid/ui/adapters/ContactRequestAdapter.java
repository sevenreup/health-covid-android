package com.skybox.seven.covid.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skybox.seven.covid.R;

import java.util.ArrayList;

public class ContactRequestAdapter extends RecyclerView.Adapter <ContactRequestAdapter.CRModelHolder>{
    ArrayList<ContactRequestModel.PendingContacts> models;
    private Context context;
    private ContactsCallBAck contactsCallBAck;

    public ContactRequestAdapter(Context context, ArrayList<ContactRequestModel.PendingContacts> models, ContactsCallBAck contactsCallBAck) {
        this.models = models;
        this.context = context;
        this.contactsCallBAck = contactsCallBAck;
    }

    @NonNull
    @Override
    public CRModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_row, null);
        return new CRModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CRModelHolder holder, int position) {
        holder.contName.setText(models.get(position).getUser().getFName());
        holder.contLName.setText(models.get(position).getUser().getLName());
        holder.contNumber.setText(models.get(position).getUser().getPhone());
        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsCallBAck.onContactAcceptClick(models.get(position).getId(), position);
            }
        });
        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsCallBAck.onContactRejectClick(models.get(position).getId(), position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setData(ArrayList<ContactRequestModel.PendingContacts> models) {
        this.models =models;
        notifyDataSetChanged();
    }

    public class CRModelHolder extends RecyclerView.ViewHolder {

        public TextView contName;
        public TextView contNumber;
        public TextView contLName;
        public Button acceptButton;
        public Button rejectButton;

        public CRModelHolder(@NonNull View itemView) {
            super(itemView);
            this.contLName = itemView.findViewById(R.id.ContLName);
            this.contName = itemView.findViewById(R.id.ContName);
            this.contNumber = itemView.findViewById(R.id.ContNumber);
            this.acceptButton = itemView.findViewById(R.id.yes);
            this.rejectButton = itemView.findViewById(R.id.no);
        }
    }

    public interface ContactsCallBAck{
        void onContactAcceptClick(int id, int position);
        void onContactRejectClick(int id, int position);
    }
}
