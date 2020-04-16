package com.skybox.seven.covid.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.network.RetrofitService;

import retrofit2.Retrofit;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "CovidFirebaseMsgService";

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
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        RetrofitService service = retrofit.create(RetrofitService.class);
        service.pushToken(message);
    }

    private void sendNotification(String message) {

    }
}
