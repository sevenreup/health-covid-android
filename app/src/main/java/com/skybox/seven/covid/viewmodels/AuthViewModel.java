package com.skybox.seven.covid.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.network.HealthService;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.network.responses.ValidationErrors;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthViewModel extends AndroidViewModel {
    private String TAG = "AUTHVIEWMODEL";

    private Retrofit retrofit;
    private SharedPreferenceRepository preferenceRepository;

    public MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public MutableLiveData<AccessToken> credentials = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRegistered = new MutableLiveData<>();
    public MutableLiveData<ValidationErrors> validationErrors = new MutableLiveData<>();
    public MutableLiveData<String> authErrors = new MutableLiveData<>();

    public AuthViewModel(Application application) {
        super(application);
        preferenceRepository = new SharedPreferenceRepository(application.getSharedPreferences(application.getString(R.string.shared_preference_key), Context.MODE_PRIVATE));
        retrofit = RetrofitFactory.getRetrofit(application);
    }

    public void login(String phone, String password) {
        Log.e(TAG, "login: started");
        loading.setValue(true);
        HealthService healthService = retrofit.create(HealthService.class);
        Call<AccessToken> call = healthService.loginUser(phone, password);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                loading.setValue(false);
                if (response.isSuccessful()) {
                    AccessToken user = response.body();
                    credentials.setValue(user);
                    Log.e(TAG, "onResponse: " + user);
                    preferenceRepository.setToken(user);
                    String fireToken = preferenceRepository.getFirebaseToken();
                    if (fireToken != null) {
                        Call<GenericResponse> fToken = healthService.pushToken(user.getType() + " " + user.getToken(), fireToken);
                        fToken.enqueue(new Callback<GenericResponse>() {
                            @Override
                            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                                Log.e(TAG, "onResponse: succeful updated firebase token");
                            }

                            @Override
                            public void onFailure(Call<GenericResponse> call, Throwable t) {
                                Log.e(TAG, "onFailure: failled to update firebase");
                            }
                        });
                    } else {
                        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                String newToken = task.getResult().getToken();
                                preferenceRepository.setFirebaseMessagingToken(newToken);
                                Call<GenericResponse> fToken = healthService.pushToken(user.getType() + " " + user.getToken(), newToken);
                                fToken.enqueue(new Callback<GenericResponse>() {
                                    @Override
                                    public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                                        Log.e(TAG, "onResponse: succeful updated firebase token");
                                    }

                                    @Override
                                    public void onFailure(Call<GenericResponse> call, Throwable t) {
                                        Log.e(TAG, "onFailure: failled to update firebase");
                                    }
                                });
                            }
                        });
                    }
                } else {
                    loading.setValue(false);
                    if (response.raw().code() == 401) {
                        authErrors.setValue("Phone number or password is invalid");
                    } else {
                        try {
                            String responseTemp = response.errorBody().string();
                            if (responseTemp != null) {
                                ValidationErrors errors = new Gson().fromJson(responseTemp, ValidationErrors.class);
                                validationErrors.setValue(errors);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                t.printStackTrace();
                loading.setValue(false);
                Log.e(TAG, "onFailure: failed to log in");
            }
        });
    }

    public void register(String fname, String lname, String number, String gender) {
        loading.setValue(true);
        HealthService healthService = retrofit.create(HealthService.class);
        Call<GenericResponse> call = healthService.register(fname, lname, number, gender);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                loading.setValue(false);
                if (response.isSuccessful()) {
                    isRegistered.setValue(true);
                } else {
                    try {
                        String responseTemp = response.errorBody().string();
                        if (responseTemp != null) {
                            ValidationErrors errors = new Gson().fromJson(responseTemp, ValidationErrors.class);
                            validationErrors.setValue(errors);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    isRegistered.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                t.printStackTrace();
                isRegistered.setValue(false);
            }
        });
    }

    public AuthViewModel newInstance(Application application) {
        return new AuthViewModel(application);
    }
}
