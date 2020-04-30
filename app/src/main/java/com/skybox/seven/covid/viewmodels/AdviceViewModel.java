package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.model.Advice;

public class AdviceViewModel extends ViewModel {
    public MutableLiveData<Advice> activeAdvice = new MutableLiveData<>();
}
