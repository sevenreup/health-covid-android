package com.skybox.seven.covid.epoxy.contacts;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.ContactRequestModel;

@EpoxyModelClass(layout = R.layout.request_row)
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
        return R.layout.request_row;
    }

    static class CRModelHolder extends EpoxyHolder {

        public TextView name;
        public TextView number;
        public Button acceptButton;
        public Button rejectButton;

        @Override
        protected void bindView(@NonNull View itemView) {
            this.name = itemView.findViewById(R.id.ContName);
            this.number = itemView.findViewById(R.id.ContNumber);
            this.acceptButton = itemView.findViewById(R.id.yes);
            this.rejectButton = itemView.findViewById(R.id.no);
        }
    }
}
