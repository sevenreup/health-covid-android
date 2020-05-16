package com.skybox.seven.covid.util;

import android.content.Context;

import com.skybox.seven.covid.data.AppDatabase;
import com.skybox.seven.covid.repository.SelfTestRepository;
import com.skybox.seven.covid.viewmodels.SelfTestViewModelFactory;

public class InjectorUtil {
    private static SelfTestRepository getSelfTestRepository(Context context) {
        return SelfTestRepository.getINSTANCE(AppDatabase.getDatabase(context).selfTestResultDAO());
    }

    public static SelfTestViewModelFactory provideSelfTestViewModelFactory(Context context) {
        return new SelfTestViewModelFactory(getSelfTestRepository(context));
    }
}
