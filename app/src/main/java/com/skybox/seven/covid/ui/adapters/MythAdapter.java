package com.skybox.seven.covid.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.skybox.seven.covid.R;

import java.util.ArrayList;

public class MythAdapter extends RecyclerView.Adapter<MythAdapter.MythHolderVH> {
    ArrayList<MythModel>models;

    public MythAdapter(ArrayList<MythModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public MythHolderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mythrow, null);
        return new MythHolderVH(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MythHolderVH holder, int position) {
        holder.mythTitle.setText(models.get(position).getTitle());
        holder.mythPara.setText(models.get(position).getParagraph());

        boolean isExpanded = models.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }
    

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MythHolderVH  extends RecyclerView.ViewHolder {
        public TextView mythTitle;
        public TextView mythPara;
        ConstraintLayout expandableLayout;

        public MythHolderVH(@NonNull View itemView) {
            super(itemView);
            this.mythTitle = itemView.findViewById(R.id.mythTitle);
            this.mythPara = itemView.findViewById(R.id.mythPara);

            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            mythTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MythModel mythModel = models.get(getAdapterPosition());
                    mythModel.setExpanded(!mythModel.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
    
}
