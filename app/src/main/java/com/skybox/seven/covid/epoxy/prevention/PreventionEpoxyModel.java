package com.skybox.seven.covid.epoxy.prevention;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.epoxy.contacts.ContactEpoxyModel;
import com.skybox.seven.covid.model.PreventionModel;

@EpoxyModelClass(layout = R.layout.model_prevention)
public class PreventionEpoxyModel extends EpoxyModelWithHolder<PreventionEpoxyModel.PreventionEpoxyViewHolder> {
    @EpoxyAttribute
    Advice prevention;
    @EpoxyAttribute View.OnClickListener listener;

    @Override
    protected PreventionEpoxyModel.PreventionEpoxyViewHolder createNewHolder() {
        return new PreventionEpoxyViewHolder();}

    @Override
    public void bind(@NonNull PreventionEpoxyModel.PreventionEpoxyViewHolder holder) {
        super.bind(holder);
        holder.preventionRVCardTitle.setText(prevention.getShortTitle());
        holder.preventionRVCardDescription.setText(prevention.getTitle());
        holder.card.setOnClickListener(listener);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_prevention;
    }

    static class PreventionEpoxyViewHolder extends EpoxyHolder {

        public TextView preventionRVCardTitle;
        public TextView preventionRVCardDescription;
        public View card;


        @Override
        protected void bindView(@NonNull View itemView) {
            this.preventionRVCardTitle = itemView.findViewById(R.id.preventionRVCardTitle);
            this.preventionRVCardDescription = itemView.findViewById(R.id.preventionRVCardDescription);
            card = itemView.findViewById(R.id.parent);
        }
    }
}
