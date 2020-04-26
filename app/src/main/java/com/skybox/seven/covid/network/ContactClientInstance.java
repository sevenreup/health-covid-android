package com.skybox.seven.covid.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactClientInstance {
        private static Retrofit retrofit;
        private static final  String BASE_URL = "https://rocky-forest-46591.herokuapp.com/";

        public static Retrofit getRetrofitInstance() {
            //
            if (retrofit == null){
                retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            }
            return retrofit;
        }
}
