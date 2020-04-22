package com.skybox.seven.covid.network;

import com.google.android.gms.maps.model.LatLng;
import com.skybox.seven.covid.model.FamMember;
import com.skybox.seven.covid.network.responses.AccessToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("auth/login")
    Call<AccessToken> loginUser(@Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("updateFirebaseToken")
    Call<String> pushToken(@Header("Authorization") String Authtoken, @Field("token") String token);

    @FormUrlEncoded
    @POST("auth/register")
    Call<String> register(@Field("name")String fname, @Field("last_name")String lname, @Field("phone")String number, @Field("password")String gender);

    @FormUrlEncoded
    @POST("contact/add")
    Call<String> saveContacts(@Header("Authorization") String Authtoken, @Field("contacts") ArrayList<FamMember> contacts, @Field("location") LatLng latLng);

    @GET("")
    Call<String> getAllContacts(@Header("Authorization") String Authtoken);
}
