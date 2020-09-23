package com.skybox.seven.covid.ui

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.util.AlarmScheduler


class MainViewModel @ViewModelInject constructor() : ViewModel() {

    private val TAG = "MAINVIEWMODEL"
    var searchOpened = MutableLiveData(false)
    var closeSearch = MutableLiveData<Boolean>()
    var status = MutableLiveData(-1)

    fun setUpWorker(context: Context) {
        AlarmScheduler.createAlarms(context)
    }
}