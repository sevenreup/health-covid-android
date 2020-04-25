package com.skybox.seven.covid.epoxy.model;

import android.view.View;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.google.android.material.button.MaterialButton;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_emergency_contact)
public class EmergencyContanctsCardModel extends EpoxyModelWithHolder<EmergencyContanctsCardModel.EmergencyContactHolder> {

    @EpoxyAttribute
    View.OnClickListener emergencyContactslistener;
    @EpoxyAttribute View.OnClickListener selfTestContactslistener;
    @Override
    protected EmergencyContactHolder createNewHolder() {
        return new EmergencyContactHolder();
    }
    @Override
    public void bind(@NonNull EmergencyContactHolder holder) {
        super.bind(holder);
        holder.contacts.setOnClickListener(emergencyContactslistener);
        holder.selfTest.setOnClickListener(selfTestContactslistener);
    }
    @Override
    protected int getDefaultLayout() {
        return R.layout.model_emergency_contact;
    }

    public class EmergencyContactHolder extends EpoxyHolder {
        MaterialButton contacts, selfTest;
        @Override
        protected void bindView(@NonNull View itemView) {
            contacts = itemView.findViewById(R.id.emergency_contacts);
            selfTest = itemView.findViewById(R.id.self_test);
        }
    }
}
