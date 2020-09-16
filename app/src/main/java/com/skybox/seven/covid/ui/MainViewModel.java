package com.skybox.seven.covid.ui;

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

import static com.skybox.seven.covid.model.OnBoardingItem.language;

public class MainViewModel extends ViewModel {
    private String TAG = "MAINVIEWMODEL";
    private SharedPreferenceRepository preferenceRepository;
    public MutableLiveData<Boolean> searchOpened = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> closeSearch = new MutableLiveData<>();

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
        return  preferenceRepository.getToken() != null;
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
        if (preferenceRepository.getToken() != null) {
            return preferenceRepository.getToken();
        }
        return "";
    }

    public void registerPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferenceRepository.registerOnChangeListener(listener);
    }

    public void  removePreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferenceRepository.unRegisterOnChangeListener(listener);
    }

    public void setLanguage(Locale locale) {
        if (locale.equals(new Locale("eng", "USA"))) {
            preferenceRepository.setActiveLanguage(Constants.ENGLISH);
        } else {
            preferenceRepository.setActiveLanguage(Constants.CHICHEWA);
        }
    }
}
