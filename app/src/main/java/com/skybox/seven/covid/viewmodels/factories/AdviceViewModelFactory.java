package com.skybox.seven.covid.viewmodels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.repositories.AdviceRepository;
import com.skybox.seven.covid.data.repositories.InfoGraphicRepository;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;
import com.skybox.seven.covid.viewmodels.AdviceViewModel;

public class AdviceViewModelFactory implements ViewModelProvider.Factory {
    private AdviceRepository adviceRepository;
    private InfoGraphicRepository infoGraphicRepository;
    private Context context;

    public AdviceViewModelFactory(AdviceRepository adviceRepository, InfoGraphicRepository infoGraphicRepository, Context context) {
        this.adviceRepository = adviceRepository;
        this.infoGraphicRepository = infoGraphicRepository;
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AdviceViewModel(adviceRepository, infoGraphicRepository, new SharedPreferenceRepository(context.getSharedPreferences(context.getString(R.string.shared_preference_key), Context.MODE_PRIVATE)));
    }
}
