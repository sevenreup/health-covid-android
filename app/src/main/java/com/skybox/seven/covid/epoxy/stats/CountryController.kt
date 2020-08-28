package com.skybox.seven.covid.epoxy.stats

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.model.CountryStat

class CountryController(private val callbacks: CountryCallbacks) : Typed2EpoxyController<Boolean, List<CountryStat>>() {
    override fun buildModels(loading: Boolean?, countries: List<CountryStat>?) {
        countries?.forEach {
            CountryModel_().id(it.country)
                    .deaths(it.deaths.toString())
                    .active(it.active.toString())
                    .recovered(it.recovered.toString())
                    .name(it.country)
                    .listener {
                        _,_,view,_ -> callbacks.onCountryClicked(it, view)
                    }
                    .addTo(this)
        }
    }
}

interface CountryCallbacks {
    fun onCountryClicked(stat: CountryStat, view: View)
}