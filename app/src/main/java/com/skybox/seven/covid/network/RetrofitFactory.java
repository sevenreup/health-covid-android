package com.skybox.seven.covid.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static Retrofit retrofit = null;

    private RetrofitFactory() {}

    public static Retrofit getRetrofit() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl("https://rocky-forest-46591.herokuapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
