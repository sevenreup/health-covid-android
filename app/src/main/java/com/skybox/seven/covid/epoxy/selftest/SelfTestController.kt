package com.skybox.seven.covid.epoxy.selftest

import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.data.entities.SelfTestComplete

class SelfTestController: Typed2EpoxyController<Boolean, List<SelfTestComplete>>() {
    override fun buildModels(loading: Boolean?, selfTests: List<SelfTestComplete>?) {
        selfTests?.forEach {
            SelfTestModel_().id(it.id).date(it.date).submitted(it.submitted).addTo(this)
        }
    }
}