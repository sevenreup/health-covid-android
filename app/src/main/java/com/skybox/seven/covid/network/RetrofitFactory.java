package com.skybox.seven.covid.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static Retrofit retrofit = null;

    private RetrofitFactory() {
    }

    /**
     * This returns the base Retrofit Client for the app server
     * @param context
     * @return Retrofit
     */
    public static Retrofit getRetrofit(Context context) {

        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .client(buildOkHttpClient(context))
                    .baseUrl("https://rocky-forest-46591.herokuapp.com/api/")
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
    /**
     *  Added a bunch of Caching Interceptors Because don't have caching enabled on the server
     *  Todo: Make sure the Server has caching enabled and whatnot
     *  Todo: Remove other forcefully caching interceptors and just leave the normal on (OkHttpClient.cache())
     * @return OkHttpClient
     */
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

    /**
     * This Interceptor adds all the required caching headers to the response to enable caching when online
     * This only does that if the server does not support caching
     * @return Interceptor
     */
    public static Interceptor provideOnlineInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());
            int maxAge = 60;
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        };
    }

    /**
     * This interceptor retries the request with a header to get cache response header
     * @return Interceptor
     */
    public static Interceptor provideOfflineCacheInterceptor() {
        return chain -> {
            Request request = chain.request();
            if (!checkInternet()) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(1, TimeUnit.DAYS)
                        .build();
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
            return chain.proceed(request);
        };
    }

    static Boolean checkInternet() {
        return true;
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
            String token = repository.getToken();
            if (token != null) {
                return response.request().newBuilder().header("Authorization", token).build();
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
