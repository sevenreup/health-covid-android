package com.skybox.seven.covid.util;

import android.content.Context;

import com.skybox.seven.covid.data.AppDatabase;
import com.skybox.seven.covid.data.repositories.LanguageRepository;
import com.skybox.seven.covid.data.repositories.MythRepository;
import com.skybox.seven.covid.data.repositories.SelfTestRepository;
import com.skybox.seven.covid.viewmodels.factories.MythViewModelFactory;
import com.skybox.seven.covid.viewmodels.factories.SelfTestViewModelFactory;

public class InjectorUtil {
    private static SelfTestRepository getSelfTestRepository(Context context) {
        return SelfTestRepository.getINSTANCE(AppDatabase.getDatabase(context).selfTestResultDAO());
    }

    public static SelfTestViewModelFactory provideSelfTestViewModelFactory(Context context) {
        return new SelfTestViewModelFactory(getSelfTestRepository(context));
    }

    public static MythRepository getMythRepository(Context context) {
        return MythRepository.getInstance(AppDatabase.getDatabase(context).mythsDAO());
    }

    public static MythViewModelFactory provideMythViewModelFactory(Context context) {
        return new MythViewModelFactory(getMythRepository(context));
    }

    public static LanguageRepository getLanguageRepository(Context context) {
        return LanguageRepository.getInstance(AppDatabase.getDatabase(context).languageDAO());
    }
}
