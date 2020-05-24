package com.skybox.seven.covid.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.skybox.seven.covid.model.NewsArticle;
import com.skybox.seven.covid.network.NewsFactory;
import com.skybox.seven.covid.network.NewsService;
import com.skybox.seven.covid.network.responses.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> networkErrors = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<List<NewsArticle>> newsArticles = new MutableLiveData<>(new ArrayList<>());
    private Retrofit retrofit;
    private static final String API_KEY = "b3d54181b14e4d9faaa3be9909a1953f", TYPE = "covid", FROM = "2020-04-24=", TO ="2020-05-27", SORT = "popularity";

    public NewsViewModel(@NonNull Application application) {
        super(application);
        retrofit = NewsFactory.getRetrofit(application);
    }

    public void getNews() {
        loading.postValue(true);

        NewsService service = retrofit.create(NewsService.class);
        Call<NewsResponse> responseCall = service.getNews(TYPE, TYPE, 10, FROM, TO, SORT, API_KEY);
        responseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                loading.postValue(false);
                if (response.isSuccessful()) {
                    networkErrors.postValue(false);

                    newsArticles.setValue(response.body().getArticles());
                } else {
                    networkErrors.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                loading.postValue(false);
                networkErrors.postValue(true);
            }
        });
    }
}
