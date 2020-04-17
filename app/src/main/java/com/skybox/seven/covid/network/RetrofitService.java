package com.skybox.seven.covid.network;

import com.google.android.gms.maps.model.LatLng;
import com.skybox.seven.covid.model.FamMember;
import com.skybox.seven.covid.network.responses.LoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Field("phone") String phone, @Field("password") String password);
    @FormUrlEncoded
    @POST("")
    Call<String> pushToken(@Field("token") String token);
    @FormUrlEncoded
    @POST("")
    Call<String> register();
    @FormUrlEncoded
    @POST("contact/add")
    Call<String> saveContacts(@Field("contacts") ArrayList<FamMember> contacts, @Field("location") LatLng latLng);
}
