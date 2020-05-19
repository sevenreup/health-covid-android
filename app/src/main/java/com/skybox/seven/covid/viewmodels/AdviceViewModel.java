package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.data.entities.InfoGraphic;
import com.skybox.seven.covid.data.repositories.AdviceRepository;
import com.skybox.seven.covid.data.repositories.InfoGraphicRepository;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.util.List;

public class AdviceViewModel extends ViewModel {
    public MutableLiveData<String> activeInfoGraphic = new MutableLiveData<>();

    public MutableLiveData<List<Advice>> adviceList = new MutableLiveData<>();
    public MutableLiveData<List<InfoGraphic>> infoGraphicList = new MutableLiveData<>();

    private AdviceRepository adviceRepository;
    private SharedPreferenceRepository sharedPreferenceRepository;
    private InfoGraphicRepository infoGraphicRepository;

    public AdviceViewModel(AdviceRepository adviceRepository, InfoGraphicRepository infographicRepository, SharedPreferenceRepository repository) {
        this.adviceRepository = adviceRepository;
        this.sharedPreferenceRepository = repository;
        this.infoGraphicRepository = infographicRepository;
    }


    public void getAdviceList() {
        adviceList.setValue(adviceRepository.getAllAdvice(sharedPreferenceRepository.getActiveLanguage()));
    }

    public void getInfoGraphicList() {
        infoGraphicList.setValue(infoGraphicRepository.getAllInfoGraphics(sharedPreferenceRepository.getActiveLanguage(), 2));
    }
}
