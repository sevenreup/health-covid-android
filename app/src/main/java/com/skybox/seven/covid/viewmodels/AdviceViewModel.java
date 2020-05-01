package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.model.InfoGraphic;
import com.skybox.seven.covid.repository.HealthRepository;

import java.util.List;

public class AdviceViewModel extends ViewModel {
    public MutableLiveData<Advice> activeAdvice = new MutableLiveData<>();
    public MutableLiveData<List<Advice>> adviceList = new MutableLiveData<>();
    public MutableLiveData<List<InfoGraphic>> infoGraphicList = new MutableLiveData<>();
    private HealthRepository healthRepository = new HealthRepository();
    public MutableLiveData<Advice.CurrentChip> currentChip = new MutableLiveData<>(Advice.CurrentChip.advice);

    public void getAdviceList() {
        infoGraphicList.setValue(healthRepository.getInfoGraphicList());
        adviceList.setValue(healthRepository.getAdviceList());
    }
}
