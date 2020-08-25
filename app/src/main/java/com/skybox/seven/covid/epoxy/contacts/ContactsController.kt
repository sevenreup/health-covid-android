package com.skybox.seven.covid.epoxy.contacts

import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.epoxy.generic.ListLoadingModel_
import com.skybox.seven.covid.model.ContactModel.ContactUsersContacts

class ContactsController : Typed2EpoxyController<Boolean, List<ContactUsersContacts>?>() {
    override fun buildModels(loading: Boolean, contacts: List<ContactUsersContacts>?) {
        ListLoadingModel_().id("loading").addIf(loading, this)
        if (contacts != null) {
            contacts.forEach {
                ContactEpoxyModel_().id(it.user.phone).contact(it).addTo(this)
            }
        } else {

        }
    }
}