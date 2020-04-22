package com.skybox.seven.covid.repository;

import android.content.SharedPreferences;

import com.skybox.seven.covid.network.responses.AccessToken;

public class SharedPreferenceRepository {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

    public void setToken(AccessToken response) {
        editor.putString("token", response.getToken());
        editor.putString("token_type", response.getType());
        editor.apply();
    }

    public AccessToken getToken() {
        AccessToken accessToken = new AccessToken();

        accessToken.setToken(sharedPreferences.getString("token", null));
        accessToken.setType(sharedPreferences.getString("token_type", null));

        return accessToken;
    }
}
