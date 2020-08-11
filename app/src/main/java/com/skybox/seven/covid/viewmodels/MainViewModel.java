package com.skybox.seven.covid.viewmodels;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.skybox.seven.covid.network.HealthService;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.network.responses.ContactRequest;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;
import com.skybox.seven.covid.util.Constants;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private String TAG = "MAINVIEWMODEL";
    private SharedPreferenceRepository preferenceRepository;

    public MutableLiveData<Boolean> showLoginNotification = new MutableLiveData<>(true);
    public MutableLiveData<Integer> status = new MutableLiveData<>(-1);

    public MutableLiveData<Locale> changeLanguage = new MutableLiveData<>();

    private HealthService service;

    @ViewModelInject
    public MainViewModel(SharedPreferenceRepository sharedPreferenceRepository, HealthService service) {
        super();
        this.preferenceRepository = sharedPreferenceRepository;
        this.service = service;
    }

    public void logout() {
        preferenceRepository.deleteToken();
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = preferenceRepository.getToken();
        Log.e(TAG, "isLoggedIn: " + accessToken.toString());
        return accessToken.getToken() != null;
    }

    public void saveContacts(ArrayList<ContactRequest> members, LatLng userLocation) {
        service.saveContacts(getToken(), members).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                // TODO: Get some actual response sent to the UI
                System.out.println(response + "wellooooo");
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                System.out.println(call + "well damn");
            }

        });
    }

    public String getToken() {
        AccessToken token = preferenceRepository.getToken();
        if (token.getToken() != null) {
            return token.getType() + " " + token.getToken();
        }
        return "";
    }

    public void registerPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferenceRepository.registerOnChangeListener(listener);
    }

    public void  removePreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferenceRepository.unRegisterOnChangeListener(listener);
    }

    public int getLanguage() {
        return preferenceRepository.getActiveLanguage();
    }

    public void setLanguage(int language) {
        preferenceRepository.setActiveLanguage(language);
        if (language == Constants.ENGLISH) {
            status.setValue(0);
            changeLanguage.setValue(new Locale("eng", "USA"));
        } else {
            status.setValue(1);
            changeLanguage.setValue(new Locale("ny", "MW"));
        }
    }
}
