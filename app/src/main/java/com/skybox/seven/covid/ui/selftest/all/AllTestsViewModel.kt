package com.skybox.seven.covid.ui.selftest.all

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class AllTestsViewModel @ViewModelInject constructor(selfTestAnswersRepository: SelfTestAnswersRepository, private val compositeDisposable: CompositeDisposable) : ViewModel() {
    val all = selfTestAnswersRepository.getAllTest()

    fun submitTests() {

    }
}