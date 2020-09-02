package com.skybox.seven.covid.repository;

import android.content.SharedPreferences;

import com.skybox.seven.covid.network.responses.AccessToken;

public class SharedPreferenceRepository {
    public static String TOKEN = "token", FIREBASE_MESSAGING = "firebase_message", ONBOARDING = "onboarding_status", LANGUAGE_INT = "language_int";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

    public void setToken(AccessToken response) {
        editor.putString(TOKEN, response.getType() + " " +response.getToken());
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }

    public Boolean isLoggedIn() {
        return getToken() != null;
    }

    public void deleteToken() {
        editor.remove(TOKEN);
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

    public void setActiveLanguage(int id) {
        editor.putInt(LANGUAGE_INT, id);
        editor.apply();
    }

    public int getActiveLanguage() {
        return sharedPreferences.getInt(LANGUAGE_INT, 1);
    }
}
