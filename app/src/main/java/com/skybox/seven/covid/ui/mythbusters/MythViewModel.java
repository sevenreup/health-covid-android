package com.skybox.seven.covid.ui.mythbusters;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.data.repositories.InfoGraphicRepository;
import com.skybox.seven.covid.data.repositories.MythRepository;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.util.List;

public class MythViewModel extends ViewModel {
    private MythRepository mythRepository;
    public MutableLiveData<List<Myth>> mythsList = new MutableLiveData<>();
    private SharedPreferenceRepository repository;

    @ViewModelInject
    public MythViewModel(MythRepository mythRepository, SharedPreferenceRepository repository) {
        this.mythRepository = mythRepository;
        this.repository = repository;
    }

    public void getAllMyths() {
        int locale = repository.getActiveLanguage();
        mythsList.setValue(mythRepository.getAllMyths(repository.getActiveLanguage()));
    }

}
