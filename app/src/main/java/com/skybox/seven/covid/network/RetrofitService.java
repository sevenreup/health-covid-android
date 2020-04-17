package com.skybox.seven.covid.network;

import com.skybox.seven.covid.network.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Field("phone") String phone, @Field("password") String password);
    @FormUrlEncoded
    @POST("updateFirebaseToken")
    Call<String> pushToken(@Field("token") String token);
    @FormUrlEncoded
    @POST("auth/register")
    Call<String> register(@Field("name")String fname, @Field("last_name")String lname, @Field("phone")String number, @Field("gender")String gender);
}
