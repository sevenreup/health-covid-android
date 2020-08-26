package com.skybox.seven.covid.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.repository.SharedPreferenceRepository

class ProfileViewModel @ViewModelInject constructor(private val prefRepository: SharedPreferenceRepository): ViewModel() {
    val isLoggedIn = MutableLiveData<Boolean>(prefRepository.isLoggedIn)
}