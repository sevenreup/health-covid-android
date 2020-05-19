package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.data.repositories.MythRepository;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.util.List;

public class MythViewModel extends ViewModel {
    private MythRepository mythRepository;
    public MutableLiveData<List<Myth>> mythsList = new MutableLiveData<>();
    SharedPreferenceRepository repository;

    public MythViewModel(MythRepository mythRepository, SharedPreferenceRepository repository) {
        this.mythRepository = mythRepository;
        this.repository = repository;
    }

    public void getAllMyths() {
        mythsList.setValue(mythRepository.getAllMyths(repository.getActiveLanguage()));
    }
}
