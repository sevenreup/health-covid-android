package com.skybox.seven.covid.ui.stats.single

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.helpers.DataSource
import com.skybox.seven.covid.helpers.DataState
import com.skybox.seven.covid.model.CountryStat
import com.skybox.seven.covid.model.Historical
import com.skybox.seven.covid.model.HistoricalResult
import com.skybox.seven.covid.model.TimeLineResult
import com.skybox.seven.covid.network.StatsService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CountryStatsViewModel @ViewModelInject constructor(private val statsService: StatsService,private val compositeDisposable: CompositeDisposable): ViewModel() {
    val countryStats = MutableLiveData<CountryStat>()
    val allStats = MutableLiveData<DataSource<HistoricalResult>>()

    val networkError = MutableLiveData<Boolean>(false)

    val loading = MutableLiveData<Boolean>()

    fun getWeekData(country: String) {
        compositeDisposable.add(
                statsService.getHistoricalData(country, "7")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                        }
                        .subscribe({
                            allStats.postValue(DataSource(DataState.SUCCESS, createResults(it), CountryStatsChatFragment.WEEK))
                        }, {
                            allStats.postValue(DataSource(DataState.ERROR, throwable = it))
                        })
        )
    }

    fun getMothData(country: String) {
        compositeDisposable.add(
                statsService.getHistoricalData(country, "31")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                        }
                        .subscribe({
                            allStats.postValue(DataSource(DataState.SUCCESS, createResults(it), CountryStatsChatFragment.MONTH))
                        }, {
                            allStats.postValue(DataSource(DataState.ERROR, throwable = it))
                        })
        )
    }

    fun getYearData(country: String) {
        compositeDisposable.add(
                statsService.getHistoricalData(country, "265")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                        }
                        .subscribe({
                            allStats.postValue(DataSource(DataState.SUCCESS, createResults(it), CountryStatsChatFragment.ALL))
                        }, {
                            allStats.postValue(DataSource(DataState.ERROR, throwable = it))
                        })
        )
    }

    private fun createResults(data: Historical): HistoricalResult {
        val casesList: List<TimeLineResult>

        data.apply {
            casesList = data.timeline.casesMap.map { entry ->
                val death = data.timeline.deathsMap[entry.key]
                val rec = data.timeline.recoveredMap[entry.key]
                TimeLineResult(entry.key, entry.value, death!!, rec!!)
            }
        }
        return HistoricalResult(
                data.country,
                casesList
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}