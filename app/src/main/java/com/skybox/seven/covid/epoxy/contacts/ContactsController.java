package com.skybox.seven.covid.epoxy.contacts;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed3EpoxyController;
import com.skybox.seven.covid.adapters.ContactModel;
import com.skybox.seven.covid.epoxy.generic.ListLoadingModel_;
import com.skybox.seven.covid.epoxy.generic.ListNetworkErrorModel_;

import java.util.List;

public class ContactsController extends Typed3EpoxyController<Boolean, Boolean,List<ContactModel.ContactUsersContacts>> {
    @AutoModel
    ListLoadingModel_ loadingModel_;
    @AutoModel
    ListNetworkErrorModel_ errorModel_;

    @Override
    protected void buildModels(Boolean loading, Boolean network,List<ContactModel.ContactUsersContacts> contacts) {
        loadingModel_.addIf(loading, this);
        errorModel_.addIf(network, this);
        for (ContactModel.ContactUsersContacts contact: contacts) {
            new ContactEpoxyModel_().id(contact.getUser().getPhone()).contact(contact).addTo(this);
        }
    }

}
