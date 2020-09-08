package com.skybox.seven.covid.ui.survey

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.repositories.SelfTestQuestionsRepository

class SelfTestQuestViewModel @ViewModelInject constructor(questionsRepository: SelfTestQuestionsRepository): ViewModel() {
    val questions = questionsRepository.getAll()
}