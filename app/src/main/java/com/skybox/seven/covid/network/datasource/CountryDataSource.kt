package com.skybox.seven.covid.network.datasource

import com.skybox.seven.covid.network.BaseDataSource
import com.skybox.seven.covid.network.StatsService
import javax.inject.Inject


class CountryDataSource @Inject constructor( private val statsService: StatsService ): BaseDataSource() {
    suspend fun getCountries() = getResult {  statsService.getAllCountriesR() }

    suspend fun getCountry(country: String) = getResult {  statsService.getSingleCountryR(country) }
}