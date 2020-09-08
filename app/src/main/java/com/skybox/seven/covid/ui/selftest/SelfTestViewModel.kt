package com.skybox.seven.covid.ui.selftest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository
import com.skybox.seven.covid.data.repositories.SelfTestQuestionsRepository

class SelfTestViewModel @ViewModelInject constructor(private val selfTestAnswersRepository: SelfTestAnswersRepository) : ViewModel() {
    var todayTest = selfTestAnswersRepository.getTodayTest()
    var latestTest = selfTestAnswersRepository.getLastUpdate()
}