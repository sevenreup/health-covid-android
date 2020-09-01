package com.skybox.seven.covid.ui.qna

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.model.*

class QnaViewModel : ViewModel() {
    val qnaLiveData: LiveData<Container>
        get() = _liveData
    private val _liveData = MutableLiveData<Container>()

    private val onExpanded: OnQuestionExpanded = { question_: Question ->
        val oldContainer = _liveData.value
        if (oldContainer != null) {
            val newQuestions = oldContainer.questions.map {
                if (it.question.id == question_.id) {
                    it.copy(question = it.question.copy(isExpanded = !question_.isExpanded))
                } else {
                    it
                }
            }

            _liveData.value = oldContainer.copy(questions = newQuestions)
        }
    }

    init {
        _liveData.value = Container(
                listOf(
                        AnswerPerQuestion(
                                Question(name = "What is covid19"),
                                listOf(
                                        Answer("Corona virus disease 2019")

                                )
                        ),
                        AnswerPerQuestion(
                                Question(name = "What is covid19"),
                                listOf(
                                        Answer("Corona virus disease 2019")

                                        )

                        )
                ),
                onExpanded
        )
    }
}