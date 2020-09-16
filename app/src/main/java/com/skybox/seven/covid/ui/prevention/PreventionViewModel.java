package com.skybox.seven.covid.ui.prevention;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.data.repositories.AdviceRepository;
import com.skybox.seven.covid.data.repositories.InfoGraphicRepository;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.util.List;

public class PreventionViewModel extends ViewModel {

    public MutableLiveData<List<Advice>> adviceList = new MutableLiveData<>();

    private AdviceRepository adviceRepository;
    private SharedPreferenceRepository sharedPreferenceRepository;

    @ViewModelInject
    public PreventionViewModel(AdviceRepository adviceRepository, SharedPreferenceRepository repository) {
        this.adviceRepository = adviceRepository;
        this.sharedPreferenceRepository = repository;
    }


    public void getAdviceList() {
        adviceList.setValue(adviceRepository.getAllAdvice(sharedPreferenceRepository.getActiveLanguage()));
    }

}
