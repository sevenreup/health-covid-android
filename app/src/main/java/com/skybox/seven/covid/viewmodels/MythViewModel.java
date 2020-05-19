package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.data.entities.InfoGraphic;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.data.repositories.InfoGraphicRepository;
import com.skybox.seven.covid.data.repositories.MythRepository;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.util.List;

public class MythViewModel extends ViewModel {
    private MythRepository mythRepository;
    public MutableLiveData<List<Myth>> mythsList = new MutableLiveData<>();
    public MutableLiveData<List<InfoGraphic>> infoGraphics = new MutableLiveData<>();
    private SharedPreferenceRepository repository;
    private InfoGraphicRepository infographicRepository;
    public MutableLiveData<String> activeInfoGraphic = new MutableLiveData<>();

    public MythViewModel(MythRepository mythRepository, InfoGraphicRepository infographicRepository, SharedPreferenceRepository repository) {
        this.mythRepository = mythRepository;
        this.repository = repository;
        this.infographicRepository = infographicRepository;
    }

    public void getAllMyths() {
        mythsList.setValue(mythRepository.getAllMyths(repository.getActiveLanguage()));
    }

    public void getAllInfoGraphics() {
        infoGraphics.setValue(infographicRepository.getAllInfoGraphics(repository.getActiveLanguage(), 1));
    }
}
