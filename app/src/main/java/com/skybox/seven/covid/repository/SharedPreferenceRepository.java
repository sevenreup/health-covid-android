package com.skybox.seven.covid.repository;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.Preference;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.util.Constants;

public class SharedPreferenceRepository {
    public static String TOKEN = "token", FIREBASE_MESSAGING = "firebase_message", ONBOARDING = "onboarding_status", LANGUAGE_INT = "language_int";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceRepository(SharedPreferences sharedPreferences, Context context) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
        LANGUAGE_INT = context.getString(R.string.language_key);
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
        editor.putString(LANGUAGE_INT, Integer.toString(id)).commit();
    }

    public int getActiveLanguage() {
        return Integer.parseInt(sharedPreferences.getString(LANGUAGE_INT, Integer.toString(Constants.ENGLISH)));
    }
}
