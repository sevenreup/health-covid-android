package com.skybox.seven.covid.epoxy.contacts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.adapters.ContactModel;

@EpoxyModelClass(layout = R.layout.model_contact)
public class ContactEpoxyModel extends EpoxyModelWithHolder<ContactEpoxyModel.ContactEpoxyViewHolder> {
    @EpoxyAttribute ContactModel.ContactUsersContacts contact;

    @Override
    protected ContactEpoxyViewHolder createNewHolder() {
        return new ContactEpoxyViewHolder();
    }

    @Override
    public void bind(@NonNull ContactEpoxyViewHolder holder) {
        super.bind(holder);
        holder.name.setText(contact.getUser().getFName() + " " + contact.getUser().getLName());
        holder.number.setText(contact.getUser().getPhone());
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_contact;
    }

    static class ContactEpoxyViewHolder extends EpoxyHolder {

        public TextView name;
        public TextView number;

        @Override
        protected void bindView(@NonNull View itemView) {
            this.name = itemView.findViewById(R.id.name);
            this.number = itemView.findViewById(R.id.number);
        }
    }
}
