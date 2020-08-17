package com.skybox.seven.covid.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.skybox.seven.covid.network.RetrofitFactory.provideOfflineCacheInterceptor;
import static com.skybox.seven.covid.network.RetrofitFactory.provideOnlineInterceptor;

public class NewsFactory {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(Context context) {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder().client(buildOkHttpClient(context)).baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }

    private static OkHttpClient buildOkHttpClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File httpCacheDir = new File(context.getCacheDir(), "offlineCache");
        Cache cache = new Cache(httpCacheDir, 10 * 1024 * 1024);

        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(provideOnlineInterceptor())
                .addNetworkInterceptor(provideOfflineCacheInterceptor())
                .build();
    }
}
