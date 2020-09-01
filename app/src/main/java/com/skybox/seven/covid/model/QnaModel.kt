package com.skybox.seven.covid.model

import java.util.concurrent.atomic.AtomicInteger

private val globalId = AtomicInteger(1)

typealias OnQuestionExpanded = (question: Question) -> Unit

data class Container(
        val questions: List<AnswerPerQuestion>,
        val OnQuestionExpanded: OnQuestionExpanded
)

data class AnswerPerQuestion(
        val question: Question,
        val answers: List<Answer>
)

data class Question(
        val name: String,
        val isExpanded: Boolean = false,
        val id: Int = globalId.getAndIncrement()
)

data class Answer(
        val name: String,
        val id: Int = globalId.getAndIncrement()
)