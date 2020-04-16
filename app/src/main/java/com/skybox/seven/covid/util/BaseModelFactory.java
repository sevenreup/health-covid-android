package com.skybox.seven.covid.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skybox.seven.covid.location.LocationViewModel;
import com.skybox.seven.covid.viewmodels.MainViewModel;

public class BaseModelFactory extends ViewModelProvider.NewInstanceFactory {

    public BaseModelFactory() {
        super();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainViewModel.class) {
            return (T) new MainViewModel();
        } else if (modelClass == LocationViewModel.class) {
            return (T) new LocationViewModel();
        }
        return null;
    }
}