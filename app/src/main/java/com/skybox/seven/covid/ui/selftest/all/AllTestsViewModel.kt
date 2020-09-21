package com.skybox.seven.covid.ui.selftest.all

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository
import com.skybox.seven.covid.repository.SharedPreferenceRepository

class AllTestsViewModel @ViewModelInject constructor(selfTestAnswersRepository: SelfTestAnswersRepository, private val preferenceRepository: SharedPreferenceRepository) : ViewModel() {
    val all = selfTestAnswersRepository.getAllTest()

    fun isAuth() = preferenceRepository.isLoggedIn
}