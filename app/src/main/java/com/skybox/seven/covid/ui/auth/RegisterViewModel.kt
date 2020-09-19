package com.skybox.seven.covid.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.network.responses.AccessToken
import com.skybox.seven.covid.network.responses.ValidationErrors
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

class RegisterViewModel @ViewModelInject constructor(private val healthService: HealthService,
                                                     private val compositeDisposable: CompositeDisposable) : ViewModel() {
    private val TAG = "AUTHVIEWMODEL"

    var loading = MutableLiveData(false)
    var isRegistered = MutableLiveData<Boolean>()
    var validationErrors = MutableLiveData<ValidationErrors>()
    val fname = MutableLiveData<String>()
    val lname = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun register() {
        loading.value = true
        compositeDisposable.add(
                healthService.register(fname.value, lname.value, number.value, password.value)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isRegistered.setValue(true)
                        }, {
                            val body = (it as HttpException).response()?.errorBody()
                            try {
                                val responseTemp = body?.string()
                                if (responseTemp != null) {
                                    val errors = Gson().fromJson(responseTemp, ValidationErrors::class.java)
                                    validationErrors.value = errors
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            isRegistered.setValue(false)
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}