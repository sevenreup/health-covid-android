package com.skybox.seven.covid.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.network.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainViewModel extends ViewModel {
    public MutableLiveData<LoginResponse> credentials = new MutableLiveData<>();
    public MutableLiveData<LoginResponse> temp = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRegistered = new MutableLiveData<>();

    private Retrofit retrofit = RetrofitFactory.getRetrofit();
    private FirebaseAuth auth;

    public MainViewModel() {
        super();
        auth = FirebaseAuth.getInstance();
    }

    public void login(String phone, String password) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<LoginResponse> call = retrofitService.loginUser(phone, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse user = response.body();
                if (user != null) {

                    auth.signInWithCustomToken(user.getType() + " " + user.getToken()).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            credentials.setValue(user);
                        } else {
                            task.getException().printStackTrace();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                LoginResponse user = new LoginResponse();
                user.setName("Chisomo kasenda");
                user.setPhone("2568834329");
                credentials.setValue(user);
            }
        });
    }

    public void setCredentials(String username, String phone) {
        LoginResponse response = new LoginResponse();
        response.setPhone(phone);
        response.setName(username);

        temp.setValue(response);
    }

    public void register(String fname, String lname, String number, String gender) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<String> call = retrofitService.register(fname, lname, number, gender);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("TAG", "onResponse: " + response);
                if (response.isSuccessful()) {
                    isRegistered.setValue(true);
                } else {
                    isRegistered.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                isRegistered.setValue(false);
            }
        });
    }
}
