package com.skybox.seven.covid.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Language;
import com.skybox.seven.covid.data.repositories.LanguageRepository;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.network.responses.ContactRequest;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;
import com.skybox.seven.covid.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainViewModel extends ViewModel {
    private String TAG = "MAINVIEWMODEL";
    private SharedPreferenceRepository preferenceRepository;

    public MutableLiveData<Boolean> showLoginNotification = new MutableLiveData<>(true);

    public MutableLiveData<Locale> changeLanguage = new MutableLiveData<>();
    public MutableLiveData<Boolean> contactsRefresh = new MutableLiveData<>();

    private Retrofit retrofit;

    public MainViewModel(SharedPreferenceRepository preferenceRepository) {
        super();
        this.preferenceRepository = preferenceRepository;
        retrofit = RetrofitFactory.getRetrofit(preferenceRepository);
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
        RetrofitService service = retrofit.create(RetrofitService.class);
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
            changeLanguage.setValue(new Locale("eng", "USA"));
        } else {
            changeLanguage.setValue(new Locale("ny", "MW"));
        }
    }
}
