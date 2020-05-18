package com.skybox.seven.covid.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skybox.seven.covid.data.repositories.SelfTestRepository;
import com.skybox.seven.covid.viewmodels.SelfTestViewModel;

public class SelfTestViewModelFactory implements ViewModelProvider.Factory {
    private SelfTestRepository selfTestRepository;

    public SelfTestViewModelFactory(SelfTestRepository selfTestRepository) {
        this.selfTestRepository = selfTestRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SelfTestViewModel(selfTestRepository);
    }
}
