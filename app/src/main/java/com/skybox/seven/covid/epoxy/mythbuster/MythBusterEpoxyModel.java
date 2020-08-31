package com.skybox.seven.covid.epoxy.mythbuster;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.epoxy.prevention.PreventionEpoxyModel;

@EpoxyModelClass(layout = R.layout.model_myth_buster)
public class MythBusterEpoxyModel extends EpoxyModelWithHolder<MythBusterEpoxyModel.MythBusterEpoxyViewHolder> {
    @EpoxyAttribute
    Myth mythbuster;

    @Override
    protected MythBusterEpoxyModel.MythBusterEpoxyViewHolder createNewHolder() {
        return new MythBusterEpoxyViewHolder();}

    @Override
    public void bind(@NonNull MythBusterEpoxyModel.MythBusterEpoxyViewHolder holder) {
        super.bind(holder);
        holder.mythRVCardTitle.setText(mythbuster.getTitle());
        holder.mythRVCardDescription.setText(mythbuster.getMyth());
    }

    @Override
    protected int getDefaultLayout() { return R.layout.model_myth_buster; }

    static class MythBusterEpoxyViewHolder extends EpoxyHolder {
        public TextView mythRVCardTitle;
        public TextView mythRVCardDescription;


        @Override
        protected void bindView(@NonNull View itemView) {
            this.mythRVCardTitle = itemView.findViewById(R.id.mythRVCardTitle);
            this.mythRVCardDescription = itemView.findViewById(R.id.mythRVCardDescription);
        }

    }
}
