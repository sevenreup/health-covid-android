package com.skybox.seven.covid.ui.selftest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.data.repositories.SelfTestQuestionsRepository

class SelfTestViewModel @ViewModelInject constructor(private val selfTestQuestionsRepository: SelfTestQuestionsRepository) : ViewModel() {
    var selfTestLiveData = MutableLiveData<List<SelfTestAnswer>>()
    val allTests: Unit
        get() {
//            selfTestLiveData.value = selfTestQuestionsRepository.allTests
        }

    fun deleteTest(answer: SelfTestAnswer?) {
//        selfTestQuestionsRepository.deleteTest(answer)
    }

    fun addTest(answer: SelfTestAnswer?) {
//        selfTestQuestionsRepository.addResults(answer)
    }

}