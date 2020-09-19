package com.skybox.seven.covid.ui.survey

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.data.entities.SelfTestComplete
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository
import com.skybox.seven.covid.data.repositories.SelfTestQuestionsRepository
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import com.skybox.seven.covid.util.Constants
import com.skybox.seven.survey.answer.BooleanResult
import com.skybox.seven.survey.answer.MultiChoiceResult
import com.skybox.seven.survey.answer.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.*

class SelfTestQuestViewModel @ViewModelInject constructor(questionsRepository: SelfTestQuestionsRepository,
                                                          private val compositeDisposable: CompositeDisposable,
                                                          preferenceRepository: SharedPreferenceRepository,
                                                          private val selfTestAnswersRepository: SelfTestAnswersRepository) : ViewModel() {
    val questions = questionsRepository.getAll(preferenceRepository.activeLanguage)
    val finished = MutableLiveData<Boolean>()

    fun submit(answers: HashMap<Int, Result>?) {
        val complete = SelfTestComplete(null, "", Date(), false)
        compositeDisposable.add(
                selfTestAnswersRepository.insertComplete(complete)
                        .flatMap {id ->
                            val answerList: MutableList<SelfTestAnswer> = ArrayList()
                            answers?.forEach {
                                when (it.value.type) {
                                    Result.MULTI -> {
                                        val answer = it.value as MultiChoiceResult
                                        val selections = answer.answers.keys.toList()
                                        answerList += SelfTestAnswer(
                                                null,
                                                Constants.ARRAY,
                                                id,
                                                answer.id,
                                                selections,
                                                null,
                                                answer.endDate)
                                    }
                                    else -> {
                                        val answer = it.value as BooleanResult
                                        answerList += SelfTestAnswer(
                                                null,
                                                Constants.BOOLEAN,
                                                id,
                                                answer.id,
                                                null,
                                                answer.answer,
                                                answer.endDate
                                        )
                                    }
                                }
                            }
                            selfTestAnswersRepository.insertAllAnswers(answerList).andThen(Single.just(answerList))
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            finished.value = true
                        }, {
                            // todo: show errors
                        })

        )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}