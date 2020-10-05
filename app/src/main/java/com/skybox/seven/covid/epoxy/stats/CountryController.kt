package com.skybox.seven.covid.epoxy.stats

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.epoxy.generic.NoItemModel_
import com.skybox.seven.covid.helpers.TextFormatter.formatNumber
import com.skybox.seven.covid.model.CountryStat

class CountryController(private val callbacks: CountryCallbacks) : TypedEpoxyController<List<CountryStat>>() {
    override fun buildModels(countries: List<CountryStat>) {
        NoItemModel_().id("loading").error(R.string.no_country_data).addIf(countries.isEmpty(),  this)
        countries.forEach {
            CountryModel_().id(it.country)
                    .deaths(it.deaths.formatNumber())
                    .active(it.active.formatNumber())
                    .recovered(it.recovered.formatNumber())
                    .name(it.country)
                    .flag(it.countryInfo.flag)
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