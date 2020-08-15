package com.skybox.seven.covid.network;

import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.network.responses.ContactRequest;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.model.ContactModel;
import com.skybox.seven.covid.model.ContactRequestModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HealthService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("auth/login")
    Call<AccessToken> loginUser(@Field("phone") String phone, @Field("password") String password);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("updateFirebaseToken")
    Call<GenericResponse> pushToken(@Header("Authorization") String Authtoken, @Field("firebase_token") String token);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("auth/register")
    Call<GenericResponse> register(@Field("first_name")String fname, @Field("last_name")String lname, @Field("phone")String number, @Field("password")String gender);

    @Headers({"Accept: application/json"})
    @POST("contact/add")
    Call<GenericResponse> saveContacts(@Header("Authorization") String Authtoken, @Body ArrayList<ContactRequest> members);

    @Headers({"Accept: application/json"})
    @GET("users/contacts")
    Call<ArrayList<ContactModel.ContactUsersContacts>> getAllContacts(@Header("Authorization") String Authtoken);

    @Headers({"Accept: application/json"})
    @GET("users/contacts/pending")
    Call<ArrayList<ContactRequestModel.PendingContacts>> getPendingContacts(@Header("Authorization") String Authtoken);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("contact/verify/user")
    Call<GenericResponse> verifyContact(@Header("Authorization") String Authtoken, @Field("id") int id, @Field("status") String status);
}