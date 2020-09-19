package com.skybox.seven.covid.ui.auth

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.network.responses.AccessToken
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthViewModel @ViewModelInject constructor(private val preferenceRepository: SharedPreferenceRepository,
                                                 private val healthService: HealthService,
                                                 private val compositeDisposable: CompositeDisposable) : ViewModel() {
    private val TAG = "LOGINVIEWMODEL"

    var loading = MutableLiveData(false)
    var credentials = MutableLiveData<AccessToken?>()
    var authErrors = MutableLiveData<String>()
    var firebaseToken = MutableLiveData<String>()
    var phone = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    fun login() {
        Log.e(TAG, "login: started")
        loading.value = true
        compositeDisposable.add(
                healthService.loginUser(phone.value, password.value)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            loading.value = false
                            credentials.value = it
                            preferenceRepository.setToken(it)
                            firebaseToken.value = preferenceRepository.firebaseToken
                        }, {
                            loading.value = false
                            //                    if (response.raw().code == 401) {
//                        authErrors.setValue("Phone number or password is invalid")
//                    } else {
//                        try {
//                            val responseTemp = response.errorBody()!!.string()
//                            if (responseTemp != null) {
//                                val errors = Gson().fromJson(responseTemp, ValidationErrors::class.java)
//                                validationErrors.value = errors
//                            }
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        }
//                    }
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}

