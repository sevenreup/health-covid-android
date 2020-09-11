package com.skybox.seven.covid.epoxy.details

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.DetailsPhoneNumbers

@EpoxyModelClass(layout = R.layout.model_details)
abstract class DetailsEpoxyModel : EpoxyModelWithHolder<DetailsEpoxyModel.QnaEpoxyViewHolder>() {

    @EpoxyAttribute
    var data: DetailsPhoneNumbers? = null

    @EpoxyAttribute
    var cardClick: View.OnClickListener? = null

    override fun createNewHolder(): QnaEpoxyViewHolder {
        return QnaEpoxyViewHolder()
    }

    override fun bind(holder: QnaEpoxyViewHolder) {
        super.bind(holder)

        holder.district!!.text = data!!.district
        holder.phoneNumber!!.text = data!!.phoneNumber

    }

    override fun getDefaultLayout(): Int {
        return R.layout.model_details
    }

    class QnaEpoxyViewHolder : EpoxyHolder() {

        var district: TextView? = null
        var phoneNumber: TextView? = null

        override fun bindView(itemView: View) {

            district = itemView.findViewById(R.id.district)
            phoneNumber = itemView.findViewById(R.id.district_phone)
        }

    }
}