package com.skybox.seven.covid.data.entities

data class DetailsPhoneNumbers(var district: String, var phoneNumber: String)

fun getDetailsData() = listOf(
        DetailsPhoneNumbers("Mzuzu", "265 88 00 00 00"),
        DetailsPhoneNumbers("Lilongwe", "265 88 00 00 00"),
        DetailsPhoneNumbers("Blantyre", "265 88 00 00 00"),
        DetailsPhoneNumbers("Zomba", "265 88 00 00 00")
)