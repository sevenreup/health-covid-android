package com.skybox.seven.covid.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static Retrofit retrofit = null;

    private RetrofitFactory() {
    }

    public static Retrofit getRetrofit(SharedPreferenceRepository repository) {

        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .authenticator(new TokenAuthenticator(repository))
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(chain -> {
                         Request.Builder builder = chain.request().newBuilder();
//                         builder.addHeader("Accept", "application/json");
                        return chain.proceed(builder.build());
                    }).build();

            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder().client(client).baseUrl("https://rocky-forest-46591.herokuapp.com/api/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }

    static class TokenAuthenticator implements Authenticator {
        SharedPreferenceRepository repository;

        public TokenAuthenticator(SharedPreferenceRepository repository) {
            this.repository = repository;
        }

        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            if (responseCount(response) >= 3)
                return null;
            AccessToken token = repository.getToken();
            if (token.getToken() != null) {
                return response.request().newBuilder().header("Authorization", token.getType() + " " + token.getToken()).build();
            }
            return null;
        }
    }

    private void refreshToken() {

    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
