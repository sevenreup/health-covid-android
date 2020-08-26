package com.skybox.seven.covid.ui.profile.contacts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.covid.model.ContactModel.ContactUsersContacts
import com.skybox.seven.covid.model.ContactRequestModel.PendingContacts
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.network.responses.GenericResponse
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ContactsViewModel @ViewModelInject constructor(private val preferenceRepository: SharedPreferenceRepository,
                                                     private val service: HealthService,
                                                     private val compositeDisposable: CompositeDisposable) : ViewModel() {

    var contactsRefresh = MutableLiveData<Boolean>()
    var actualContacts = MutableLiveData<MutableList<ContactUsersContacts>>(ArrayList())
    var pendingContacts = MutableLiveData<MutableList<PendingContacts>>(ArrayList())
    var contactsLoading = MutableLiveData<Boolean>()
    var networkLoading = MutableLiveData(false)

    val token: String
        get() {
            val token = preferenceRepository.token
            return if (token.token != null) {
                token.type + " " + token.token
            } else ""
        }

    val allContacts: Unit
        get() {
            contactsLoading.value = true
            networkLoading.value = false
            compositeDisposable.add(
                    service.getAllContacts(token)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                actualContacts.value = it
                                contactsLoading.value = false
                                networkLoading.setValue(false)
                            }, {
                                contactsLoading.value = false
                                networkLoading.setValue(true)
                            })
            )
        }

    fun generatePendingContactList() {
        contactsLoading.value = true
        networkLoading.value = false
        compositeDisposable.add(
                service.getPendingContacts(token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            pendingContacts.value = it
                            contactsLoading.value = true
                            networkLoading.value = false
                        }, {
                            contactsLoading.value = false
                            networkLoading.value = true
                        })
        )
    }

    fun acceptContact(id: Int, position: Int) {
        networkLoading.value = false
        compositeDisposable.add(
                service.verifyContact(token, id, "accepted")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            pendingContacts.value?.removeAt(position)
                            pendingContacts.value = pendingContacts.value
                            networkLoading.setValue(true)
                        }, {
                            networkLoading.setValue(true)
                        })
        )
    }

    fun rejectContact(id: Int, position: Int) {
        networkLoading.value = false
        compositeDisposable.add(
                service.verifyContact(token, id, "rejected")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            pendingContacts.value?.removeAt(position)
                            pendingContacts.value = pendingContacts.value
                            networkLoading.setValue(true)
                        }, {
                            networkLoading.setValue(true)
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}