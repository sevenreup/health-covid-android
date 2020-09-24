package com.skybox.seven.covid.epoxy.generic

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.helpers.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.model_no_item)
abstract class NoItemModel: EpoxyModelWithHolder<NoItemHolder>() {
    override fun getDefaultLayout(): Int = R.layout.model_no_item

    override fun createNewHolder(): NoItemHolder = NoItemHolder()
}

class NoItemHolder: KotlinEpoxyHolder() {

}