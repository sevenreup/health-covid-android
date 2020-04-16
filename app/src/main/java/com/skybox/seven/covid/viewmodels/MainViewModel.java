package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.network.responses.LoginResponse;

public class MainViewModel extends ViewModel {
    public MutableLiveData<LoginResponse> credentials = new MutableLiveData<>();
}
