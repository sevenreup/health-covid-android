package com.skybox.seven.covid;

import android.app.Application;
import android.content.Context;

import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate;
import com.skybox.seven.covid.data.entities.Language;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.HiltAndroidApp;
import io.radar.sdk.Radar;

@HiltAndroidApp
public class Covid extends Application {
    private LocalizationApplicationDelegate delegate = new LocalizationApplicationDelegate();
    public static final List<Language> SUPPORTED_LOCALES =
            Arrays.asList(
                    new Language(1,"en", "US", "English"),
                    new Language(2,"ny", "MW", "Chichewa")
            );
    @Override
    public void onCreate() {
        super.onCreate();
        Radar.initialize(this, "prj_test_pk_7f75cb504e6e5d6262cc75012cdf1413abe8875e");
        Radar.setLogLevel(Radar.RadarLogLevel.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        delegate.setDefaultLanguage(base, Locale.ENGLISH);
        super.attachBaseContext(delegate.attachBaseContext(base));
    }
}
