package com.skybox.seven.covid.epoxy.contacts;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed2EpoxyController;
import com.skybox.seven.covid.epoxy.generic.ListLoadingModel_;
import com.skybox.seven.covid.model.ContactModel;

import java.util.List;

public class ContactsController extends Typed2EpoxyController<Boolean,List<ContactModel.ContactUsersContacts>> {
    @AutoModel
    ListLoadingModel_ loadingModel_;

    @Override
    protected void buildModels(Boolean loading,List<ContactModel.ContactUsersContacts> contacts) {
        loadingModel_.addIf(loading, this);
        for (ContactModel.ContactUsersContacts contact: contacts) {
            new ContactEpoxyModel_().id(contact.getUser().getPhone()).contact(contact).addTo(this);
        }
    }

}
