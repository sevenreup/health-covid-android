package com.skybox.seven.covid.epoxy.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.google.android.material.card.MaterialCardView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.Advice;

@EpoxyModelClass(layout = R.layout.model_advice_main)
public class AdviceMainModel extends EpoxyModelWithHolder<AdviceMainModel.AdMainModelHolder> {
    @EpoxyAttribute
    Advice advice;
    @EpoxyAttribute View.OnClickListener listener;

    @Override
    protected AdMainModelHolder createNewHolder() {
        return new AdMainModelHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_advice_main;
    }

    @Override
    public void bind(@NonNull AdMainModelHolder holder) {
        super.bind(holder);
        holder.advice.setText(advice.getAdvice());
        holder.title.setText(advice.getShortTitle());
        holder.icon.setImageResource(advice.getImage());
        holder.holder.setOnClickListener(listener);
    }

    public class AdMainModelHolder extends EpoxyHolder {
        MaterialCardView holder;
        TextView title, advice;
        ImageView icon;

        @Override
        protected void bindView(@NonNull View itemView) {
            holder = itemView.findViewById(R.id.advice_holder);
            title = itemView.findViewById(R.id.advice_title);
            advice = itemView.findViewById(R.id.advice_content);
            icon = itemView.findViewById(R.id.advice_icon);
        }
    }
}
