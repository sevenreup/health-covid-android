package com.skybox.seven.covid.ui.selftest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.SelfTestResult
import com.skybox.seven.covid.data.repositories.SelfTestRepository

class SelfTestViewModel @ViewModelInject constructor(private val selfTestRepository: SelfTestRepository) : ViewModel() {
    var selfTestLiveData = MutableLiveData<List<SelfTestResult>>()
    val allTests: Unit
        get() {
            selfTestLiveData.value = selfTestRepository.allTests
        }

    fun deleteTest(result: SelfTestResult?) {
        selfTestRepository.deleteTest(result)
    }

    fun addTest(result: SelfTestResult?) {
        selfTestRepository.addResults(result)
    }

}