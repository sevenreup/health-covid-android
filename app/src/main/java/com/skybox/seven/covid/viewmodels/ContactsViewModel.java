package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

public class ContactsViewModel extends ViewModel {
    private SharedPreferenceRepository preferenceRepository;
    public MutableLiveData<Boolean> contactsRefresh = new MutableLiveData<>();

    public ContactsViewModel(SharedPreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public String getToken() {
        AccessToken token = preferenceRepository.getToken();
        if (token.getToken() != null) {
            return token.getType() + " " + token.getToken();
        }
        return "";
    }
}
