package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.network.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainViewModel extends ViewModel {
    public MutableLiveData<LoginResponse> credentials = new MutableLiveData<>();
    Retrofit retrofit = RetrofitFactory.getRetrofit();

    public void login(String phone, String password) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<LoginResponse> call = retrofitService.loginUser(phone, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse user = response.body();
                if (user != null) {
                    credentials.setValue(user);
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
