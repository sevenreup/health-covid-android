package com.skybox.seven.covid.repository;

import android.content.SharedPreferences;

import com.skybox.seven.covid.network.responses.AccessToken;

public class SharedPreferenceRepository {
    public static String TOKEN = "token", TOKEN_TYPE = "token_type", FIREBASE_MESSAGING = "firebase_message", ONBOARDING = "onboarding_status";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

    public void setToken(AccessToken response) {
        editor.putString(TOKEN, response.getToken());
        editor.putString(TOKEN_TYPE, response.getType());
        editor.apply();
    }

    public AccessToken getToken() {
        AccessToken accessToken = new AccessToken();

        accessToken.setToken(sharedPreferences.getString(TOKEN, null));
        accessToken.setType(sharedPreferences.getString(TOKEN_TYPE, null));

        return accessToken;
    }

    public void deleteToken() {
        editor.remove(TOKEN);
        editor.remove(TOKEN_TYPE);
        editor.apply();
    }

    public void setFirebaseMessagingToken(String s) {
        editor.putString(FIREBASE_MESSAGING, s);
        editor.apply();
    }

    public String getFirebaseToken() {
        return sharedPreferences.getString(FIREBASE_MESSAGING, null);
    }

    public void registerOnChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unRegisterOnChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public boolean getOnBoardingPref() {
        return sharedPreferences.getBoolean(ONBOARDING, false);
    }

    public void setOnBoardingPref(boolean value) {
        editor.putBoolean(ONBOARDING, value);
        editor.apply();
    }
}
