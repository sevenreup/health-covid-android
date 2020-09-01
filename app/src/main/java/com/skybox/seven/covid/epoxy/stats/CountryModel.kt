package com.skybox.seven.covid.epoxy.stats

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.preload.Preloadable
import com.bumptech.glide.Glide
import com.skybox.seven.covid.R
import com.skybox.seven.covid.helpers.KotlinEpoxyHolder
import com.skybox.seven.covid.util.loadImage

@EpoxyModelClass(layout = R.layout.model_countries)
abstract class CountryModel: EpoxyModelWithHolder<CountryHolder>() {
    
    @EpoxyAttribute lateinit var deaths: String
    @EpoxyAttribute lateinit var active: String
    @EpoxyAttribute lateinit var recovered: String
    @EpoxyAttribute lateinit var name: String
    @EpoxyAttribute var listener: View.OnClickListener? = null
    @EpoxyAttribute lateinit var flag: String
    
    override fun getDefaultLayout(): Int = R.layout.model_countries
    override fun createNewHolder(): CountryHolder = CountryHolder()

    override fun bind(holder: CountryHolder) {
        super.bind(holder)
        holder.active.text = active
        holder.deaths.text = deaths
        holder.recovered.text = recovered
        holder.name.text = name

        holder.glide.loadImage(flag, true)
                .circleCrop()
                .into(holder.image)

        holder.getView().setOnClickListener(listener)
    }
}
class CountryHolder: KotlinEpoxyHolder(), Preloadable {
    val recovered by bind<TextView>(R.id.recovered)
    val active by bind<TextView>(R.id.active)
    val deaths by bind<TextView>(R.id.deaths)
    val name by bind<TextView>(R.id.country)
    val image by bind<ImageView>(R.id.flag)
    val glide by lazy { Glide.with(image.context) }

    override val viewsToPreload by lazy { listOf(image) }

}