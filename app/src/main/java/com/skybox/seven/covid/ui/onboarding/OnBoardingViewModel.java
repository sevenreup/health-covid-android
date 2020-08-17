package com.skybox.seven.covid.ui.onboarding;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;
import com.skybox.seven.covid.util.Constants;

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
        retrofit = RetrofitFactory.getRetrofit(application);
    }

    public void setOnBoardingInfo (boolean value) {
        preferenceRepository.setOnBoardingPref(value);
    }
}
