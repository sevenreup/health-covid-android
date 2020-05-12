package com.skybox.seven.covid.epoxy.model;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.google.android.material.chip.ChipGroup;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.ContactNumber;

@EpoxyModelClass(layout = R.layout.model_emergency_contacts_filter)
public class EmergencyContactsFilterModel extends EpoxyModelWithHolder<EmergencyContactsFilterModel.EmergencyFilterViewHolder> {
    @EpoxyAttribute
    ContactNumber number;

    @Override
    protected EmergencyFilterViewHolder createNewHolder() {
        return new EmergencyFilterViewHolder();
    }

    @Override
    public void bind(@NonNull EmergencyFilterViewHolder holder) {
        super.bind(holder);
        holder.name.setText(number.getName());
        holder.logo.setText(Character.toString(number.getName().charAt(0)));
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_emergency_contacts_filter;
    }

    static class EmergencyFilterViewHolder extends EpoxyHolder {
        TextView name, logo;
        @Override
        protected void bindView(@NonNull View itemView) {
            name = itemView.findViewById(R.id.number_name);
            logo = itemView.findViewById(R.id.logo);
        }
    }
}
