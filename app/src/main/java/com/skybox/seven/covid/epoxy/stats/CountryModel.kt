package com.skybox.seven.covid.epoxy.stats

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.helpers.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.model_countries)
abstract class CountryModel: EpoxyModelWithHolder<CountryHolder>() {
    
    @EpoxyAttribute lateinit var deaths: String
    @EpoxyAttribute lateinit var active: String
    @EpoxyAttribute lateinit var recovered: String
    @EpoxyAttribute lateinit var name: String
    @EpoxyAttribute var listener: View.OnClickListener? = null
    
    override fun getDefaultLayout(): Int = R.layout.model_countries
    override fun createNewHolder(): CountryHolder = CountryHolder()

    override fun bind(holder: CountryHolder) {
        super.bind(holder)
        holder.active.text = active
        holder.deaths.text = deaths
        holder.recovered.text = recovered
        holder.name.text = name

        holder.getView().setOnClickListener(listener)
    }
}
class CountryHolder: KotlinEpoxyHolder() {
    val recovered by bind<TextView>(R.id.total_recovered_no)
    val active by bind<TextView>(R.id.total_active_no)
    val deaths by bind<TextView>(R.id.total_deaths_no)
    val name by bind<TextView>(R.id.country)
}