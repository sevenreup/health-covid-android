package com.skybox.seven.covid.epoxy.details.response

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.card.MaterialCardView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.ResponseTeams

@EpoxyModelClass(layout = R.layout.model_response_teams)
abstract class ResponseEpoxyModel : EpoxyModelWithHolder<ResponseEpoxyModel.ResponseEpoxyViewHolder>() {

    @EpoxyAttribute
    var data: ResponseTeams? = null

    @EpoxyAttribute
    var click: View.OnClickListener? = null

    override fun createNewHolder(): ResponseEpoxyViewHolder {
        return ResponseEpoxyViewHolder()
    }

    override fun bind(holder: ResponseEpoxyViewHolder) {
        super.bind(holder)

        holder.location!!.text  = data!!.getLocation
        holder.phoneNumber!!.text = data!!.getPhoneNumber

        holder.responseCard!!.setOnClickListener(click)
    }

    override fun getDefaultLayout(): Int {
        return R.layout.model_response_teams
    }

    class ResponseEpoxyViewHolder : EpoxyHolder() {

        var location: TextView? = null
        var phoneNumber: TextView? = null

        var responseCard: MaterialCardView? = null


        override fun bindView(itemView: View) {

            location = itemView.findViewById(R.id.responseArea)
            phoneNumber = itemView.findViewById(R.id.responseNumber)
            responseCard = itemView.findViewById(R.id.responseCard)

        }

    }
}