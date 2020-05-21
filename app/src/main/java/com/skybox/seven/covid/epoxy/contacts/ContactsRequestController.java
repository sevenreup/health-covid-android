package com.skybox.seven.covid.epoxy.contacts;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed2EpoxyController;
import com.skybox.seven.covid.model.ContactRequestModel;
import com.skybox.seven.covid.epoxy.generic.ListLoadingModel_;

import java.util.List;

public class ContactsRequestController extends Typed2EpoxyController<Boolean, List<ContactRequestModel.PendingContacts>> {
    private ContactsCallBack callBAck;
    @AutoModel
    ListLoadingModel_ loadingModel_;

    public ContactsRequestController(ContactsCallBack callBAck) {
        this.callBAck = callBAck;
    }

    @Override
    protected void buildModels(Boolean loading, List<ContactRequestModel.PendingContacts> pendingContacts) {
        loadingModel_.addIf(loading, this);
        for (ContactRequestModel.PendingContacts contact:
             pendingContacts) {
            new ContactRequestEpoxyModel_().id(contact.getUser().getPhone())
                    .contact(contact)
                    .rejectListener((model, parentView, clickedView, position) -> callBAck.onContactRejectClick(contact.getId(),position))
                    .acceptListener((model, parentView, clickedView, position) -> callBAck.onContactAcceptClick(contact.getId(),position)).addTo(this);
        }
    }

    public interface ContactsCallBack {
        void onContactAcceptClick(int id, int position);
        void onContactRejectClick(int id, int position);
    }
}
