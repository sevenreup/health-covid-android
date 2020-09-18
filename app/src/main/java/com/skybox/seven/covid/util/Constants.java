package com.skybox.seven.covid.util;

import java.util.Locale;

public class Constants {
    public final static int ENGLISH = 1, CHICHEWA = 0;
    public final static int MYTH = 11, ADVICE=12;

    public final static String MW_ISO_3 = "MWI";

    public final static String STATS_BASE_URL = "https://disease.sh/v3/covid-19/";

    public final static int BOOLEAN = 223, ARRAY = 224, TEXT = 225;

    public static Locale getLocale(int locale) {
        if (locale == ENGLISH)
            return Locale.ENGLISH;
        else
            return new Locale("ny", "MW");
    }
}
