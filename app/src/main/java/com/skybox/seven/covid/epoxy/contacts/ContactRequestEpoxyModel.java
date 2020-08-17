package com.skybox.seven.covid.epoxy.contacts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.google.android.material.button.MaterialButton;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.ContactRequestModel;

@EpoxyModelClass(layout = R.layout.model_contact_request_row)
public class ContactRequestEpoxyModel extends EpoxyModelWithHolder<ContactRequestEpoxyModel.CRModelHolder> {
    @EpoxyAttribute ContactRequestModel.PendingContacts contact;
    @EpoxyAttribute View.OnClickListener rejectListener;
    @EpoxyAttribute View.OnClickListener acceptListener;

    @Override
    protected CRModelHolder createNewHolder() {
        return new CRModelHolder();
    }

    @Override
    public void bind(@NonNull CRModelHolder holder) {
        super.bind(holder);
        holder.name.setText(contact.getUser().getFName() + " " + contact.getUser().getLName());
        holder.number.setText(contact.getUser().getPhone());
        holder.acceptButton.setOnClickListener(acceptListener);
        holder.rejectButton.setOnClickListener(rejectListener);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_contact_request_row;
    }

    static class CRModelHolder extends EpoxyHolder {

        public TextView name;
        public TextView number;
        public MaterialButton acceptButton;
        public MaterialButton rejectButton;

        @Override
        protected void bindView(@NonNull View itemView) {
            this.name = itemView.findViewById(R.id.ContName);
            this.number = itemView.findViewById(R.id.ContNumber);
            this.acceptButton = itemView.findViewById(R.id.accept);
            this.rejectButton = itemView.findViewById(R.id.reject);
        }
    }
}
