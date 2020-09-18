package com.skybox.seven.covid.data.entities

data class ResponseTeams(var districtTag: String, var getLocation: String?, var getPhoneNumber: String?)

fun getResponseData() = listOf(

        ResponseTeams("Lilongwe","Area 12", "265 88 00 00 00")

)