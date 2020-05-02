package com.skybox.seven.covid.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.iid.FirebaseInstanceId;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.model.FamMember;
import com.skybox.seven.covid.model.InfoGraphic;
import com.skybox.seven.covid.model.Myth;
import com.skybox.seven.covid.model.MythGraphicInfo;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.repository.HealthRepository;
import com.skybox.seven.covid.repository.MythRepository;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainViewModel extends ViewModel {
    private String TAG = "MAINVIEWMODEL";
    private SharedPreferenceRepository preferenceRepository;
    private HealthRepository healthRepository = new HealthRepository();
    private MythRepository mythRepository = new MythRepository();

    public MutableLiveData<AccessToken> credentials = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRegistered = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoginNotification = new MutableLiveData<>(true);

    public MutableLiveData<List<Advice>> adviceList = new MutableLiveData<>();
    public MutableLiveData<List<InfoGraphic>> infoGraphicList = new MutableLiveData<>();

    public MutableLiveData<List<Myth>> mythList = new MutableLiveData<>();
    public MutableLiveData<List<MythGraphicInfo>> mythGraphicInfoList = new MutableLiveData<>();

    public MutableLiveData<Locale> changeLanguage = new MutableLiveData<>();
    public MutableLiveData<Boolean> contactsRefresh = new MutableLiveData<>();

    private Retrofit retrofit;

    public MainViewModel(Application application) {
        super();
        preferenceRepository = new SharedPreferenceRepository(application.getSharedPreferences(application.getString(R.string.shared_preference_key), Context.MODE_PRIVATE));
        retrofit = RetrofitFactory.getRetrofit(preferenceRepository);
    }

    public void login(String phone, String password) {
        Log.e(TAG, "login: started");
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<AccessToken> call = retrofitService.loginUser(phone, password);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                AccessToken user = response.body();
                if (user != null) {
                    credentials.setValue(user);
                    Log.e(TAG, "onResponse: " + user);
                    preferenceRepository.setToken(user);
                    String fireToken = preferenceRepository.getFirebaseToken();
                    if (fireToken != null) {
                        Call<GenericResponse> fToken = retrofitService.pushToken(user.getType() + " " + user.getToken(), fireToken);
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
                                Call<GenericResponse> fToken = retrofitService.pushToken(user.getType() + " " + user.getToken(), newToken);
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
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "onFailure: failed to log in");
            }
        });
    }

    public void register(String fname, String lname, String number, String gender) {
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<GenericResponse> call = retrofitService.register(fname, lname, number, gender);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                Log.e("TAG", "onResponse: " + response);
                if (response.isSuccessful()) {
                    isRegistered.setValue(true);
                } else {
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

    public void logout() {
        preferenceRepository.deleteToken();
    }

    public void getAdviceList() {
        infoGraphicList.setValue(healthRepository.getInfoGraphicList());
        adviceList.setValue(healthRepository.getAdviceList());
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = preferenceRepository.getToken();
        Log.e(TAG, "isLoggedIn: " + accessToken.toString());
        return accessToken.getToken() != null;
    }

    public void getMythList() {
        mythGraphicInfoList.setValue(mythRepository.getMythGraphicInfoList());
        mythList.setValue(mythRepository.getMythList());
    }

    public void saveContacts(ArrayList<FamMember> contacts, LatLng userLocation) {
        RetrofitService service = retrofit.create(RetrofitService.class);
        service.saveContacts(getToken(), contacts, userLocation).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                // TODO: Get some actual response sent to the UI
                System.out.println(response + "wellooooo");
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                System.out.println(call + "well damn");
            }

        });
    }

    public String getToken() {
        AccessToken token = preferenceRepository.getToken();
        if (token.getToken() != null) {
            return token.getType() + " " + token.getToken();
        }
        return "";
    }

    public void registerPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferenceRepository.registerOnChangeListener(listener);
    }

    public void  removePreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferenceRepository.unRegisterOnChangeListener(listener);
    }
}
