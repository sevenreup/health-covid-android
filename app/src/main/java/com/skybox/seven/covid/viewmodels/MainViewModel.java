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
import com.skybox.seven.covid.network.responses.ContactRequest;
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
    private MythRepository mythRepository = new MythRepository();

    public MutableLiveData<Boolean> showLoginNotification = new MutableLiveData<>(true);

    public MutableLiveData<List<Myth>> mythList = new MutableLiveData<>();
    public MutableLiveData<List<MythGraphicInfo>> mythGraphicInfoList = new MutableLiveData<>();

    public MutableLiveData<Locale> changeLanguage = new MutableLiveData<>();

    private Retrofit retrofit;

    public MainViewModel(Application application) {
        super();
        preferenceRepository = new SharedPreferenceRepository(application.getSharedPreferences(application.getString(R.string.shared_preference_key), Context.MODE_PRIVATE));
        retrofit = RetrofitFactory.getRetrofit(preferenceRepository);
    }

    public void logout() {
        preferenceRepository.deleteToken();
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

    public void saveContacts(ArrayList<ContactRequest> members, LatLng userLocation) {
        RetrofitService service = retrofit.create(RetrofitService.class);
<<<<<<< HEAD
        service.saveContacts(getToken(), members).enqueue(new Callback<GenericResponse>() {
=======
        service.saveContacts(getToken(), members, userLocation).enqueue(new Callback<ArrayList<FamMember>>() {
>>>>>>> parent of 28ea9b9... debug
            @Override
            public void onResponse(Call<ArrayList<FamMember>> call, Response<ArrayList<FamMember>> response) {
                // TODO: Get some actual response sent to the UI
                System.out.println(response + "wellooooo");
            }

            @Override
            public void onFailure(Call<ArrayList<FamMember>> call, Throwable t) {
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
