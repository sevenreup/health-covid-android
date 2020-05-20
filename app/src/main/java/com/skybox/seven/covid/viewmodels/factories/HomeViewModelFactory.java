package com.skybox.seven.covid.viewmodels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;
import com.skybox.seven.covid.viewmodels.MainViewModel;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    Context context;

    public HomeViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(new SharedPreferenceRepository(context.getSharedPreferences(context.getString(R.string.shared_preference_key), Context.MODE_PRIVATE)));
    }
}
