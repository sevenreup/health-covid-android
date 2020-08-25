package com.skybox.seven.covid.ui.auth.register

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.gson.Gson
import com.skybox.seven.covid.R
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.network.RetrofitFactory
import com.skybox.seven.covid.network.responses.AccessToken
import com.skybox.seven.covid.network.responses.GenericResponse
import com.skybox.seven.covid.network.responses.ValidationErrors
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "AUTHVIEWMODEL"
    private val retrofit: Retrofit
    private val preferenceRepository: SharedPreferenceRepository
    var loading = MutableLiveData(false)
    var credentials = MutableLiveData<AccessToken?>()
    var isRegistered = MutableLiveData<Boolean>()
    var validationErrors = MutableLiveData<ValidationErrors>()
    var authErrors = MutableLiveData<String>()
    fun login(phone: String?, password: String?) {
        Log.e(TAG, "login: started")
        loading.value = true
        val healthService = retrofit.create(HealthService::class.java)
        val call = healthService.loginUser(phone, password)
        call.enqueue(object : Callback<AccessToken?> {
            override fun onResponse(call: Call<AccessToken?>, response: Response<AccessToken?>) {
                loading.value = false
                if (response.isSuccessful) {
                    val user = response.body()
                    credentials.value = user
                    Log.e(TAG, "onResponse: $user")
                    preferenceRepository.token = user
                    val fireToken = preferenceRepository.firebaseToken
                    if (fireToken != null) {
                        val fToken = healthService.pushToken(user!!.type + " " + user.token, fireToken)
                        fToken.enqueue(object : Callback<GenericResponse?> {
                            override fun onResponse(call: Call<GenericResponse?>, response: Response<GenericResponse?>) {
                                Log.e(TAG, "onResponse: succeful updated firebase token")
                            }

                            override fun onFailure(call: Call<GenericResponse?>, t: Throwable) {
                                Log.e(TAG, "onFailure: failled to update firebase")
                            }
                        })
                    } else {
                        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task: Task<InstanceIdResult> ->
                            if (task.isSuccessful) {
                                val newToken = task.result!!.token
                                preferenceRepository.setFirebaseMessagingToken(newToken)
                                val fToken = healthService.pushToken(user!!.type + " " + user.token, newToken)
                                fToken.enqueue(object : Callback<GenericResponse?> {
                                    override fun onResponse(call: Call<GenericResponse?>, response: Response<GenericResponse?>) {
                                        Log.e(TAG, "onResponse: succeful updated firebase token")
                                    }

                                    override fun onFailure(call: Call<GenericResponse?>, t: Throwable) {
                                        Log.e(TAG, "onFailure: failled to update firebase")
                                    }
                                })
                            }
                        }
                    }
                } else {
                    loading.value = false
                    if (response.raw().code == 401) {
                        authErrors.setValue("Phone number or password is invalid")
                    } else {
                        try {
                            val responseTemp = response.errorBody()!!.string()
                            if (responseTemp != null) {
                                val errors = Gson().fromJson(responseTemp, ValidationErrors::class.java)
                                validationErrors.value = errors
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AccessToken?>, t: Throwable) {
                t.printStackTrace()
                loading.value = false
                Log.e(TAG, "onFailure: failed to log in")
            }
        })
    }

    fun register(fname: String?, lname: String?, number: String?, gender: String?) {
        loading.value = true
        val healthService = retrofit.create(HealthService::class.java)
        val call = healthService.register(fname, lname, number, gender)
        call.enqueue(object : Callback<GenericResponse?> {
            override fun onResponse(call: Call<GenericResponse?>, response: Response<GenericResponse?>) {
                loading.value = false
                if (response.isSuccessful) {
                    isRegistered.setValue(true)
                } else {
                    try {
                        val responseTemp = response.errorBody()!!.string()
                        if (responseTemp != null) {
                            val errors = Gson().fromJson(responseTemp, ValidationErrors::class.java)
                            validationErrors.value = errors
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    isRegistered.setValue(false)
                }
            }

            override fun onFailure(call: Call<GenericResponse?>, t: Throwable) {
                t.printStackTrace()
                isRegistered.value = false
            }
        })
    }

    fun newInstance(application: Application): RegisterViewModel {
        return RegisterViewModel(application)
    }

    init {
        preferenceRepository = SharedPreferenceRepository(application.getSharedPreferences(application.getString(R.string.shared_preference_key), Context.MODE_PRIVATE))
        retrofit = RetrofitFactory.getRetrofit(application)
    }
}