package com.skybox.seven.covid.ui.qna

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.repositories.QnARepositoty
import com.skybox.seven.covid.repository.SharedPreferenceRepository

class QnAViewModel @ViewModelInject constructor(qnARepositoty: QnARepositoty, preferenceRepository: SharedPreferenceRepository): ViewModel() {
    val qna = qnARepositoty.getAllQnA(preferenceRepository.activeLanguage)
}