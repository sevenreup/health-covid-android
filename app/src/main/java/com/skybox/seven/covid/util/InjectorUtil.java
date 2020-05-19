package com.skybox.seven.covid.util;

import android.content.Context;

import com.skybox.seven.covid.data.AppDatabase;
import com.skybox.seven.covid.data.repositories.AdviceRepository;
import com.skybox.seven.covid.data.repositories.InfoGraphicRepository;
import com.skybox.seven.covid.data.repositories.LanguageRepository;
import com.skybox.seven.covid.data.repositories.MythRepository;
import com.skybox.seven.covid.data.repositories.SelfTestRepository;
import com.skybox.seven.covid.viewmodels.factories.AdviceViewModelFactory;
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

    public static InfoGraphicRepository getInfoGraphicRepository(Context context) {
        return InfoGraphicRepository.getInstance(AppDatabase.getDatabase(context).infoGraphicDAO());
    }

    public static MythViewModelFactory provideMythViewModelFactory(Context context) {
        return new MythViewModelFactory(getMythRepository(context), getInfoGraphicRepository(context),context);
    }

    public static AdviceViewModelFactory provideAdviceViewModelFactory(Context context) {
        return new AdviceViewModelFactory(getAdviceRepository(context), getInfoGraphicRepository(context),context);
    }

    public static LanguageRepository getLanguageRepository(Context context) {
        return LanguageRepository.getInstance(AppDatabase.getDatabase(context).languageDAO());
    }

    public static AdviceRepository getAdviceRepository(Context context) {
        return AdviceRepository.getInstance(AppDatabase.getDatabase(context).adviceDAO());
    }
}
