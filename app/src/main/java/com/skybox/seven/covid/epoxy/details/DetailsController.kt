package com.skybox.seven.covid.epoxy.details

import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.data.entities.DetailsPhoneNumbers

class DetailsController(): Typed2EpoxyController<Boolean?, List<DetailsPhoneNumbers>>() {
    override fun buildModels(data1: Boolean?, details: List<DetailsPhoneNumbers>) {
        for (detail in details){
            DetailsEpoxyModel_()
                    .id(detail.district)
                    .data(detail)
                    .addTo(this)
        }
    }
}