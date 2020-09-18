package com.skybox.seven.covid.ui.onboarding

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.repositories.LanguageRepository
import com.skybox.seven.covid.repository.SharedPreferenceRepository

class OnBoardingViewModel @ViewModelInject constructor(private val preferenceRepository: SharedPreferenceRepository, languageRepository: LanguageRepository) : ViewModel() {
    val langs = languageRepository.all

    fun setOnBoardingInfo(value: Boolean) {
        preferenceRepository.onBoardingPref = value
    }

    fun setLanguage(locale: Int) {
        preferenceRepository.activeLanguage = locale
    }
}