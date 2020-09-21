package com.skybox.seven.covid.ui.selftest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository
import com.skybox.seven.covid.data.repositories.SelfTestQuestionsRepository
import com.skybox.seven.covid.repository.SharedPreferenceRepository

class SelfTestViewModel @ViewModelInject constructor(selfTestAnswersRepository: SelfTestAnswersRepository, private val preferenceRepository: SharedPreferenceRepository) : ViewModel() {
    var todayTest = selfTestAnswersRepository.getTodayTest()
    var latestTest = selfTestAnswersRepository.getLastUpdate()
    val topThree = selfTestAnswersRepository.getTopSelfTests()

    fun isAuth(): Boolean = preferenceRepository.isLoggedIn
}