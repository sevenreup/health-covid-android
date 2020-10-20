package com.skybox.seven.covid.ui

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.Advice
import com.skybox.seven.covid.data.entities.Myth
import com.skybox.seven.covid.util.AlarmScheduler


class MainViewModel @ViewModelInject constructor() : ViewModel() {

    private val TAG = "MAINVIEWMODEL"
    var searchOpened = MutableLiveData(false)
    var closeSearch = MutableLiveData<Boolean>()
    var status = MutableLiveData(-1)

    var hideBottom = MutableLiveData(false)
    var advice = MutableLiveData<Advice>()
    var myth = MutableLiveData<Myth>()
    val open = MutableLiveData<Boolean>()

    fun setUpWorker(context: Context) {
        AlarmScheduler.createAlarms(context)
    }
}