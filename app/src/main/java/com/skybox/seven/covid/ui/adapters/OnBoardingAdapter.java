package com.skybox.seven.covid.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.OnBoardingItem;

import java.util.List;


public class OnBoardingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<OnBoardingItem> onBoardingItems;
    OnBoardCallback callback;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItems, OnBoardCallback callback) {
        this.onBoardingItems = onBoardingItems;
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case OnBoardingItem.normal:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.on_boarding_item_normal, parent, false);
                return new OnBoardNormalViewHolder(view);
            case OnBoardingItem.end:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.on_boarding_item_end, parent, false);
                return new OnBoardEndViewHolder(view1);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return onBoardingItems.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OnBoardingItem item = onBoardingItems.get(position);
        switch (item.getType()) {
            case OnBoardingItem.normal:
                OnBoardNormalViewHolder viewHolder = (OnBoardNormalViewHolder) holder;
                viewHolder.title.setText(item.getTitle());
                viewHolder.body.setText(item.getExplanation());
                break;
            case OnBoardingItem.end:
                OnBoardEndViewHolder viewHolder1 = (OnBoardEndViewHolder) holder;
                viewHolder1.skip.setOnClickListener(v -> callback.onClickSkip());
                viewHolder1.login.setOnClickListener(v -> callback.onClickLogin());
                viewHolder1.register.setOnClickListener(v -> callback.onClickRegister());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    public class OnBoardNormalViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body;

        public OnBoardNormalViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.page_title);
            body = itemView.findViewById(R.id.page_explanation);
        }
    }

    public class OnBoardEndViewHolder extends RecyclerView.ViewHolder {
        MaterialButton login, register, skip;

        public OnBoardEndViewHolder(@NonNull View itemView) {
            super(itemView);
            login = itemView.findViewById(R.id.page_login);
            register = itemView.findViewById(R.id.page_register);
            skip = itemView.findViewById(R.id.page_skip);
        }
    }

    public interface OnBoardCallback {
        void onClickLogin();

        void onClickRegister();

        void onClickSkip();
    }
}
