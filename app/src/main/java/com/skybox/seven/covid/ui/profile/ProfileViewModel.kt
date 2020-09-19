package com.skybox.seven.covid.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.iid.FirebaseInstanceId
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(private val prefRepository: SharedPreferenceRepository,
                                                    selfTestAnswersRepository: SelfTestAnswersRepository): ViewModel() {
    val isLoggedIn = MutableLiveData<Boolean>(prefRepository.isLoggedIn)
    val lastTest = selfTestAnswersRepository.getLastUpdate()
    val logout = MutableLiveData<Boolean>()

    fun logout() {
        prefRepository.deleteToken()
        GlobalScope.launch(Dispatchers.IO) {
            FirebaseInstanceId.getInstance().deleteInstanceId()
        }

        logout.value = true
    }
}