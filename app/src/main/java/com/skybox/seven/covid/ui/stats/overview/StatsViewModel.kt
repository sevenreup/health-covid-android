package com.skybox.seven.covid.ui.stats.overview

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.model.CountryStat
import com.skybox.seven.covid.model.WorldStats
import com.skybox.seven.covid.network.StatsService
import com.skybox.seven.covid.util.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "StatsViewModel"

class StatsViewModel @ViewModelInject constructor(private val statsService: StatsService, private val compositeDisposable: CompositeDisposable) : ViewModel() {
    val malawiData = MutableLiveData<CountryStat>()
    val worldData = MutableLiveData<WorldStats>()
    val networkError = MutableLiveData<Boolean>(false)

    val malawiLoading = MutableLiveData<Boolean>()
    val worldLoading = MutableLiveData<Boolean>()

    fun getMalawiData() {
        malawiLoading.value  = true
        compositeDisposable.add(
                statsService.getSingleCountry(Constants.MW_ISO_3)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Log.e(TAG, "getMalawiData: her")
                            networkError.value = false
                            malawiLoading.value  = false
                            malawiData.value = it
                        }, {
                            // todo: handle errors
                            networkError.value = true
                            malawiLoading.value  = false
                            it.printStackTrace()
                        })
        )
    }

    fun getWorldData() {
        worldLoading.value  = true
        compositeDisposable.add(
                statsService.getWorldStats()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            worldData.value = it
                            networkError.value = false
                            worldLoading.value  = false
                        }, {
                            // todo: handle errors
                            networkError.value = true
                            worldLoading.value  = false
                            it.printStackTrace()
                        })
        )
    }

    fun getAllCountries() {
        compositeDisposable.add(
                statsService.getAllCountries()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({

                        }, {

                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}