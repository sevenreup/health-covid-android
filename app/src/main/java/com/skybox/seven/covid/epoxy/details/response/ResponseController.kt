package com.skybox.seven.covid.epoxy.details.response

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.data.entities.ResponseTeams

class ResponseController: Typed2EpoxyController<Boolean?, List<ResponseTeams>> (){

    override fun buildModels(loading: Boolean?, teams: List<ResponseTeams>?) {
        if (teams != null) {
            for (team in teams){
                ResponseEpoxyModel_()
                        .id(team.getLocation)
                        .data(team)
                        .click { _, parentView, _, _ ->

                            if (ActivityCompat.checkSelfPermission(parentView.responseCard!!.context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + team.getPhoneNumber))
                                parentView.responseCard?.context?.startActivity(intent)
                            }

                        }
                        .addTo(this)
            }
        }
    }


}