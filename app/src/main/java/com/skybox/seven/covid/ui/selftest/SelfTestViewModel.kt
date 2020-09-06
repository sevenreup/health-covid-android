package com.skybox.seven.covid.ui.selftest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.data.repositories.SelfTestRepository

class SelfTestViewModel @ViewModelInject constructor(private val selfTestRepository: SelfTestRepository) : ViewModel() {
    var selfTestLiveData = MutableLiveData<List<SelfTestAnswer>>()
    val allTests: Unit
        get() {
            selfTestLiveData.value = selfTestRepository.allTests
        }

    fun deleteTest(answer: SelfTestAnswer?) {
        selfTestRepository.deleteTest(answer)
    }

    fun addTest(answer: SelfTestAnswer?) {
        selfTestRepository.addResults(answer)
    }

}