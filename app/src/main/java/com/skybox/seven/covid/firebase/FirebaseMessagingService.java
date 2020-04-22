package com.skybox.seven.covid.firebase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;

import retrofit2.Retrofit;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "CovidFirebaseMsgService";
    private SharedPreferenceRepository repository;

    public FirebaseMessagingService() {
        super();
        repository = new SharedPreferenceRepository(getSharedPreferences(getApplication().getString(R.string.shared_preference_key), Context.MODE_PRIVATE));
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "FROM: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG,"Message Payload" + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        registerToServer(s);
    }

    private void registerToServer(String message) {
        Log.d(TAG, "registerToServer: " + message);
        Retrofit retrofit = RetrofitFactory.getRetrofit(repository);
        RetrofitService service = retrofit.create(RetrofitService.class);
        AccessToken token = repository.getToken();

        if (token.getToken() != null) {
            service.pushToken(token.getType() + " " + token.getToken(), message);
        }

    }

    private void sendNotification(String message) {
    }
}
