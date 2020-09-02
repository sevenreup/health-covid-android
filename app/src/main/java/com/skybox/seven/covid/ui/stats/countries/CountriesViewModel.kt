package com.skybox.seven.covid.ui.stats.countries

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.model.CountryStat
import com.skybox.seven.covid.network.StatsService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CountriesViewModel @ViewModelInject constructor(private val statsService: StatsService, private val compositeDisposable: CompositeDisposable) : ViewModel() {
    val allCountriesData = MutableLiveData<List<CountryStat>>()
    val searchText = MutableLiveData<String>()
    val rebuild = MutableLiveData<Boolean>()
    val filteredList = MutableLiveData<List<CountryStat>>()

    fun getAllCountries() {
        compositeDisposable.add(
                statsService.getAllCountries()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            allCountriesData.value = it
                        }, {
                            it.printStackTrace()
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}