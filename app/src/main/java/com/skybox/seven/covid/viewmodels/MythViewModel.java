package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.data.repositories.MythRepository;
import com.skybox.seven.covid.data.resultentities.MythWithLanguages;

import java.util.List;

public class MythViewModel extends ViewModel {
    private MythRepository mythRepository;
    public MutableLiveData<List<MythWithLanguages>> mythsList = new MutableLiveData<>();

    public MythViewModel(MythRepository mythRepository) {
        this.mythRepository = mythRepository;
    }

    public void getAllMyths() {
        mythsList.setValue(mythRepository.getAllMyths(6));
    }
}
