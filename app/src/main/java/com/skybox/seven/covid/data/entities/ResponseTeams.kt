package com.skybox.seven.covid.data.entities

data class ResponseTeams(var getLocation: String?, var getPhoneNumber: String?)

fun getResponseData() = listOf(
        ResponseTeams("Area 12", "265 88 00 00 00"),
        ResponseTeams("Area 18", "265 88 00 00 00"),
        ResponseTeams("Area 25", "265 88 00 00 00"),
        ResponseTeams("Area 36", "265 88 00 00 00")
)