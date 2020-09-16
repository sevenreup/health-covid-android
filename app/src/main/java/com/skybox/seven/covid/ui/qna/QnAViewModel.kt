package com.skybox.seven.covid.ui.qna

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.repositories.QnARepositoty

class QnAViewModel @ViewModelInject constructor(private val qnARepositoty: QnARepositoty): ViewModel() {
    val qna = qnARepositoty.getAllQnA()
}