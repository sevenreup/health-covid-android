package com.skybox.seven.covid.viewmodels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.repositories.InfoGraphicRepository;
import com.skybox.seven.covid.data.repositories.MythRepository;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;
import com.skybox.seven.covid.viewmodels.MythViewModel;

public class MythViewModelFactory implements ViewModelProvider.Factory {
    private MythRepository mythRepository;
    private Context context;
    private InfoGraphicRepository infographicRepository;

    public MythViewModelFactory(MythRepository mythRepository, InfoGraphicRepository infographicRepository, Context context) {
        this.mythRepository = mythRepository;
        this.context = context;
        this.infographicRepository = infographicRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MythViewModel(mythRepository, infographicRepository,new SharedPreferenceRepository(context.getSharedPreferences(context.getString(R.string.shared_preference_key), Context.MODE_PRIVATE)));
    }
}
