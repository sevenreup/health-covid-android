package com.skybox.seven.covid.epoxy.generic

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.epoxy.stats.CountryHolder
import com.skybox.seven.covid.helpers.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.model_no_item)
abstract class NoItemModel: EpoxyModelWithHolder<NoItemHolder>() {
    @EpoxyAttribute var error = R.string.no_self_assess

    override fun getDefaultLayout(): Int = R.layout.model_no_item

    override fun createNewHolder(): NoItemHolder = NoItemHolder()

    override fun bind(holder: NoItemHolder) {
        super.bind(holder)
        holder.error.setText(error)
    }

}

class NoItemHolder: KotlinEpoxyHolder() {
    val error by bind<TextView>(R.id.error_body)
}