package com.skybox.seven.covid.epoxy.contacts;

import com.airbnb.epoxy.Typed2EpoxyController;
import com.skybox.seven.covid.adapters.ContactModel;

import java.util.List;

public class ContactsController extends Typed2EpoxyController<Boolean, List<ContactModel.ContactUsersContacts>> {

    @Override
    protected void buildModels(Boolean data1, List<ContactModel.ContactUsersContacts> data2) {
        for (ContactModel.ContactUsersContacts contact: data2) {
            new ContactEpoxyModel_().id(contact.getUser().getPhone()).contact(contact).addTo(this);
        }
    }

}
