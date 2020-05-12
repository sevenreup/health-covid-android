package com.skybox.seven.covid.epoxy;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;
import com.skybox.seven.covid.epoxy.model.EmergencyContactsFilterModel_;
import com.skybox.seven.covid.epoxy.model.HeaderModel_;
import com.skybox.seven.covid.model.ContactNumber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmergencyController extends TypedEpoxyController<HashMap<String, List<ContactNumber>>> {

    @Override
    protected void buildModels(HashMap<String, List<ContactNumber>> data) {
        for (Map.Entry<String, List<ContactNumber>> entry: data.entrySet()) {
            new HeaderModel_().id(entry.getKey()).header(entry.getKey()).addTo(this);
            List<ContactNumber> numbers = entry.getValue();
            for (ContactNumber number: numbers) {
                new EmergencyContactsFilterModel_().id(number.getName()).number(number).addTo(this);
            }
        }
    }
}
