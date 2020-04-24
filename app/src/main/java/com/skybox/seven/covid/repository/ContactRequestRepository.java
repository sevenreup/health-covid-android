package com.skybox.seven.covid.repository;

import com.skybox.seven.covid.ui.adapters.ContactRequestModel;

import java.util.ArrayList;
import java.util.List;

public class ContactRequestRepository {
    private List<ContactRequestModel> models = new ArrayList<>();

    public ContactRequestRepository() {
        getMyContactRequests();
    }

    private void getMyContactRequests() {
        models.add(new ContactRequestModel("Christopher Phiri","099445546"));

    }
}
