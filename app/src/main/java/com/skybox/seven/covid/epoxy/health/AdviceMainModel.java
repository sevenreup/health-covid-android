package com.skybox.seven.covid.epoxy.health;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Advice;

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
        holder.share.setOnClickListener(listener);
        holder.holder.setOnClickListener(v -> {
            if (!advice.isExpanded()) {

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(holder.layout);

                // move the image
                constraintSet.connect(R.id.advice_icon, ConstraintSet.TOP, R.id.advice_title, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.advice_icon, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
                constraintSet.connect(R.id.advice_icon, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);

                // move the content
                constraintSet.connect(R.id.advice_content, ConstraintSet.TOP,R.id.advice_icon, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.advice_content, ConstraintSet.START,ConstraintSet.PARENT_ID, ConstraintSet.START, 0);

                // move the title
                constraintSet.connect(R.id.advice_title, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);

                constraintSet.applyTo(holder.layout);

                holder.advice.setEllipsize(null);
                holder.advice.setMaxLines(100);

                holder.share.setVisibility(View.VISIBLE);

                advice.setExpanded(true);
            } else {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(holder.layout);

                // move back the icon
                constraintSet.clear(R.id.advice_icon, ConstraintSet.END);

                // move back the content
                constraintSet.connect(R.id.advice_content, ConstraintSet.TOP,R.id.advice_title, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.advice_content, ConstraintSet.START,R.id.advice_icon, ConstraintSet.END, 0);

                // move back the title
                constraintSet.connect(R.id.advice_title, ConstraintSet.START, R.id.advice_icon, ConstraintSet.START, 0);

                constraintSet.applyTo(holder.layout);

                holder.advice.setEllipsize(TextUtils.TruncateAt.END);
                holder.advice.setMaxLines(2);

                holder.share.setVisibility(View.GONE);

                advice.setExpanded(false);
            }
        });
    }

    public class AdMainModelHolder extends EpoxyHolder {
        MaterialCardView holder;
        MaterialButton share;
        ConstraintLayout layout;
        TextView title, advice;
        ImageView icon;

        @Override
        protected void bindView(@NonNull View itemView) {
            holder = itemView.findViewById(R.id.advice_holder);
            title = itemView.findViewById(R.id.advice_title);
            advice = itemView.findViewById(R.id.advice_content);
            icon = itemView.findViewById(R.id.advice_icon);
            layout = itemView.findViewById(R.id.advice_container);
            share = itemView.findViewById(R.id.advice_share);
        }
    }
}
