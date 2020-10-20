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
    val allCountriesData = MutableLiveData<List<CountryStat>>(ArrayList())
    val close = MutableLiveData<Boolean>()
    val searchText = MutableLiveData<String>()
    val rebuild = MutableLiveData<Boolean>()
    val filteredList = MutableLiveData<List<CountryStat>>()

    val loading = MutableLiveData<Boolean>()

    val networkError = MutableLiveData<Boolean>(false)

    fun getAllCountries() {
        loading.value = true
        compositeDisposable.add(
                statsService.getAllCountries()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            loading.value = false
                            allCountriesData.value = it
                            networkError.value = false
                        }, {
                            loading.value = false
                            it.printStackTrace()
                            networkError.value = true
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}