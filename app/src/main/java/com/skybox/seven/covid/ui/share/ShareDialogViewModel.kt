package com.skybox.seven.covid.ui.share

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.Advice
import com.skybox.seven.covid.data.entities.Myth

class ShareDialogViewModel : ViewModel () {

    var advice = MutableLiveData<Advice>()
    var myth = MutableLiveData<Myth>()
    var type = MutableLiveData<Int>()

    val open = MutableLiveData<Boolean>()

}