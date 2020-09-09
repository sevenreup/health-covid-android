package com.skybox.seven.covid.ui.selftest.all

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository

class AllTestsViewModel @ViewModelInject constructor(selfTestAnswersRepository: SelfTestAnswersRepository) : ViewModel() {
    val all = selfTestAnswersRepository.getAllTest()
}