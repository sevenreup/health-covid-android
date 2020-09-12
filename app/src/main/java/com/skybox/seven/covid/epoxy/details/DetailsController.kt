package com.skybox.seven.covid.epoxy.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.data.entities.DetailsPhoneNumbers

class DetailsController : Typed2EpoxyController<Boolean?, List<DetailsPhoneNumbers>>() {

    var context: Context? = null

    override fun buildModels(data1: Boolean?, details: List<DetailsPhoneNumbers>) {
        for (detail in details){
            DetailsEpoxyModel_()
                    .id(detail.district)
                    .data(detail)
                    .cardClick { _, parentView, _, _ ->

                        val intent = Intent(Intent.ACTION_CALL, Uri.parse(detail.phoneNumber))
                        try {
                            parentView.card?.context?.startActivity(intent)
                        }catch (e: Exception){
                            Log.e("exception occurred", e.toString())
                        }

                    }
                    .addTo(this)
        }
    }

}