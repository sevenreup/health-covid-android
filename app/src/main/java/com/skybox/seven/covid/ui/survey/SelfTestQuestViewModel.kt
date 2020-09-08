package com.skybox.seven.covid.ui.survey

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository
import com.skybox.seven.covid.data.repositories.SelfTestQuestionsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SelfTestQuestViewModel @ViewModelInject constructor(questionsRepository: SelfTestQuestionsRepository,
                                                          private val compositeDisposable: CompositeDisposable,
                                                          private val selfTestAnswersRepository: SelfTestAnswersRepository): ViewModel() {
    val questions = questionsRepository.getAll()
    val finished = MutableLiveData<Boolean>()

    fun submit(answers: List<SelfTestAnswer>) {
        compositeDisposable.add(
                selfTestAnswersRepository.insertAll(answers)
                        .subscribeOn(Schedulers.io())
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