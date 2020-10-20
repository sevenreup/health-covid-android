package com.skybox.seven.covid.network

import com.skybox.seven.covid.model.ContinentStat
import com.skybox.seven.covid.model.CountryStat
import com.skybox.seven.covid.model.Historical
import com.skybox.seven.covid.model.WorldStats
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StatsService {
    @GET("all")
    fun getWorldStats(): Single<WorldStats>

    @GET("countries/{country}?yesterday=false&twoDaysAgo=false&strict=true&allowNull=true")
    fun getSingleCountry(@Path("country") country: String): Single<CountryStat>

    @GET("countries/{country}?yesterday=false&twoDaysAgo=false&strict=true&allowNull=true")
    fun getSingleCountryR(@Path("country") country: String): Response<CountryStat>

    @GET("countries/{country}?yesterday=false&twoDaysAgo=false&strict=true&allowNull=true")
    fun getSingleCountryC(@Path("country") country: String): Call<CountryStat>

    @GET("countries")
    fun getAllCountries(): Single<List<CountryStat>>

    @GET("countries")
    fun getAllCountriesR(): Response<List<CountryStat>>

    @GET("continents")
    fun getContinent(): Single<List<ContinentStat>>

    @GET("historical/{country}")
    fun getHistoricalData(@Path("country") country: String, @Query("lastdays") days: String): Single<Historical>
}