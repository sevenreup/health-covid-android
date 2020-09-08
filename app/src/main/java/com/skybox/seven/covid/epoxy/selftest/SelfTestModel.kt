package com.skybox.seven.covid.epoxy.selftest

import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.helpers.KotlinEpoxyHolder
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

@EpoxyModelClass(layout = R.layout.model_self_test)
abstract class SelfTestModel: EpoxyModelWithHolder<SelfTestHolder>() {
    @EpoxyAttribute lateinit var date: Date
    @EpoxyAttribute var submitted = false

    override fun getDefaultLayout(): Int = R.layout.model_self_test

    override fun createNewHolder(): SelfTestHolder = SelfTestHolder()

    override fun bind(holder: SelfTestHolder) {
        super.bind(holder)
        holder.date.text = PrettyTime().format(date)
        DrawableCompat.setTint(holder.submitted.drawable, holder.submitted.context.resources.getColor(if (submitted) R.color.recovered else R.color.deaths))
    }
}

class SelfTestHolder: KotlinEpoxyHolder() {
    val date by bind<TextView>(R.id.date)
    val submitted by bind<ImageView>(R.id.submitted)
}