package com.skybox.seven.covid.repository

import com.skybox.seven.covid.network.StatsService
import javax.inject.Inject

class StatsRepository @Inject constructor(private val statsService: StatsService) {
    fun getCountryData(country: String, time: String) = statsService.getHistoricalData(country, time)

    fun getAllCountries() = statsService.getAllCountries()

}