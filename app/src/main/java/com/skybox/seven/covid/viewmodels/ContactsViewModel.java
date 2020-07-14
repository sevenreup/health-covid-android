package com.skybox.seven.covid.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.model.ContactModel;
import com.skybox.seven.covid.model.ContactRequestModel;
import com.skybox.seven.covid.network.HealthService;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsViewModel extends ViewModel {
    private SharedPreferenceRepository preferenceRepository;
    public MutableLiveData<Boolean> contactsRefresh = new MutableLiveData<>();
    private HealthService service;
    public MutableLiveData<List<ContactModel.ContactUsersContacts>> actualContacts = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<ContactRequestModel.PendingContacts>> pendingContacts = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<Boolean> contactsLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> networkLoading = new MutableLiveData<>(false);

    @ViewModelInject
    public ContactsViewModel(SharedPreferenceRepository sharedPreferenceRepository, HealthService service) {
        this.preferenceRepository = sharedPreferenceRepository;
        this.service = service;
    }

    public String getToken() {
        AccessToken token = preferenceRepository.getToken();
        if (token.getToken() != null) {
            return token.getType() + " " + token.getToken();
        }
        return "";
    }

    public void getAllContacts() {
        contactsLoading.setValue(true);
        networkLoading.setValue(false);
        Call<ArrayList<ContactModel.ContactUsersContacts>> call = service.getAllContacts(getToken());

        call.enqueue(new Callback<ArrayList<ContactModel.ContactUsersContacts>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactModel.ContactUsersContacts>> call, Response<ArrayList<ContactModel.ContactUsersContacts>> response)
            {
                actualContacts.setValue(response.body());
                contactsLoading.setValue(false);
                networkLoading.setValue(false);
            }
            @Override
            public void onFailure(Call<ArrayList<ContactModel.ContactUsersContacts>> call, Throwable t) {
                contactsLoading.setValue(false);
                networkLoading.setValue(true);
            }
        });
    }

    public void generatePendingContactList() {
        contactsLoading.setValue(true);
        networkLoading.setValue(false);
        Call<ArrayList<ContactRequestModel.PendingContacts>> call = service.getPendingContacts(getToken());

        call.enqueue(new Callback<ArrayList<ContactRequestModel.PendingContacts>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactRequestModel.PendingContacts>> call, Response<ArrayList<ContactRequestModel.PendingContacts>> response) {
                pendingContacts.setValue(response.body());
                contactsLoading.setValue(true);
                networkLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<ArrayList<ContactRequestModel.PendingContacts>> call, Throwable t) {
                contactsLoading.setValue(false);
                networkLoading.setValue(true);
            }
        });
    }

    public void acceptContact(int id, int position) {
        networkLoading.setValue(false);
        Call<GenericResponse> call = service.verifyContact(getToken(), id, "accepted");
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                pendingContacts.getValue().remove(position);
                pendingContacts.setValue(pendingContacts.getValue());
                networkLoading.setValue(true);
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                networkLoading.setValue(true);
            }
        });
    }

    public void rejectContact(int id, int position) {
        networkLoading.setValue(false);
        Call<GenericResponse> call = service.verifyContact(getToken(), id, "rejected");
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                pendingContacts.getValue().remove(position);
                pendingContacts.setValue(pendingContacts.getValue());
                networkLoading.setValue(true);
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                networkLoading.setValue(true);
            }
        });
    }
}
