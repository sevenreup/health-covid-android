package com.skybox.seven.covid.epoxy.prevention;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_prevention)
public class PreventionEpoxyModel extends EpoxyModelWithHolder<PreventionEpoxyModel.PreventionEpoxyViewHolder> {
    @EpoxyAttribute String description;
    @EpoxyAttribute String title;
    @EpoxyAttribute
    @DrawableRes
    int image;
    @EpoxyAttribute View.OnClickListener listener;

    @Override
    protected PreventionEpoxyModel.PreventionEpoxyViewHolder createNewHolder() {
        return new PreventionEpoxyViewHolder();}

    @Override
    public void bind(@NonNull PreventionEpoxyModel.PreventionEpoxyViewHolder holder) {
        super.bind(holder);
        holder.title.setText(title);
        holder.description.setText(description);
        holder.card.setOnClickListener(listener);
        holder.icon.setImageResource(image);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_prevention;
    }

    public static class PreventionEpoxyViewHolder extends EpoxyHolder {

        public TextView title;
        public TextView description;
        public ImageView icon;
        public View card;


        @Override
        protected void bindView(@NonNull View itemView) {
            this.title = itemView.findViewById(R.id.preventionRVCardTitle);
            this.description = itemView.findViewById(R.id.preventionRVCardDescription);
            card = itemView.findViewById(R.id.parent);
            icon = itemView.findViewById(R.id.card_icon);
        }
    }
}
