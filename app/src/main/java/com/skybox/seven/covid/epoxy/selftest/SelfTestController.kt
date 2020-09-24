package com.skybox.seven.covid.epoxy.selftest

import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.data.entities.SelfTestComplete
import com.skybox.seven.covid.epoxy.generic.NoItemModel_

class SelfTestController: Typed2EpoxyController<Boolean, List<SelfTestComplete>>() {
    override fun buildModels(loading: Boolean?, selfTests: List<SelfTestComplete>?) {
        if (selfTests != null) {
            if (selfTests.isNotEmpty()) {
                selfTests.forEach {
                    SelfTestModel_().id(it.id).date(it.date).submitted(it.submitted).addTo(this)
                }
            } else {
                NoItemModel_().id("missing").addTo(this)
            }
        } else {
            NoItemModel_().id("missing").addTo(this)
        }
    }
}