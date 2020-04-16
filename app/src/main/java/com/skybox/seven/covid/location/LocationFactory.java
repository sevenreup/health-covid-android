package com.skybox.seven.covid.location;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LocationFactory extends ViewModelProvider.NewInstanceFactory {

    public LocationFactory() {
        super();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == LocationViewModel.class) {
            return (T) new LocationViewModel();
        }
        return null;
    }
}
