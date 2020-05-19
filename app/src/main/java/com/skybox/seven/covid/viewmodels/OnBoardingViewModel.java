package com.skybox.seven.covid.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.util.Locale;

import retrofit2.Retrofit;

public class OnBoardingViewModel extends AndroidViewModel {
    private String TAG = "ONBOARDING";
    private SharedPreferenceRepository preferenceRepository;

    public MutableLiveData<Locale> changeLanguage = new MutableLiveData<>();

    private Retrofit retrofit;

    public OnBoardingViewModel(Application application) {
        super(application);
        preferenceRepository = new SharedPreferenceRepository(application.getSharedPreferences(application.getString(R.string.shared_preference_key), Context.MODE_PRIVATE));
        retrofit = RetrofitFactory.getRetrofit(preferenceRepository);
    }

    public void setOnBoardingInfo (boolean value) {
        preferenceRepository.setOnBoardingPref(value);
    }

    public void setLanguage(int language) {
        preferenceRepository.setActiveLanguage(language);
        if (language == 1) {
            changeLanguage.setValue(new Locale("eng", "USA"));
        } else {
            changeLanguage.setValue(new Locale("ny", "MW"));
        }
    }
}
