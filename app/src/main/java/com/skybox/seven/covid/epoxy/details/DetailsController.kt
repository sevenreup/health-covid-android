package com.skybox.seven.covid.epoxy.details

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.Typed2EpoxyController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.DetailsPhoneNumbers
import com.skybox.seven.covid.data.entities.getResponseData
import com.skybox.seven.covid.epoxy.details.response.ResponseController

class DetailsController : Typed2EpoxyController<Boolean?, List<DetailsPhoneNumbers>>() {

    var context: Context? = null
    lateinit var dialog: BottomSheetDialog
    val data = getResponseData()
    val controller = ResponseController()


    @SuppressLint("InflateParams", "SetTextI18n")
    override fun buildModels(data1: Boolean?, details: List<DetailsPhoneNumbers>) {
        for (detail in details){
            DetailsEpoxyModel_()
                    .id(detail.district)
                    .data(detail)
                    .cardClick { _, parentView, _, _ ->

                        dialog = BottomSheetDialog(parentView.card!!.context, R.style.BottomSheetDialog)
                        val view = dialog.layoutInflater.inflate(R.layout.response_teams_dialog, null)
                        dialog.setContentView(view)
                        dialog.show()

                        val recyclerView: EpoxyRecyclerView = view.findViewById(R.id.responseRecycler)
                        val title: TextView = view.findViewById(R.id.responseTeamName)

                        title.text = detail.district + " " + parentView.card!!.resources.getString(R.string.response_teams)
                        controller.setData(false, data)

                        recyclerView.apply {
                            setController(controller)
                        }



                    }
                    .addTo(this)
        }
    }

}