package com.skybox.seven.covid.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skybox.seven.covid.data.repositories.MythRepository;
import com.skybox.seven.covid.viewmodels.MythViewModel;

public class MythViewModelFactory implements ViewModelProvider.Factory {
    private MythRepository mythRepository;

    public MythViewModelFactory(MythRepository mythRepository) {
        this.mythRepository = mythRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MythViewModel(mythRepository);
    }
}
