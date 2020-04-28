package com.skybox.seven.covid.network;

import com.google.android.gms.maps.model.LatLng;
import com.skybox.seven.covid.model.FamMember;
import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.ui.adapters.ContactModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
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
    @FormUrlEncoded
    @POST("contact/add")
    Call<String> saveContacts(@Header("Authorization") String Authtoken, @Field("contacts") ArrayList<FamMember> contacts, @Field("location") LatLng latLng);

    @Headers({"Accept: application/json"})
    @GET("users/contacts")
    Call<ArrayList<ContactModel.ContactUsersContacts>> getAllContacts(@Header("Authorization") String Authtoken);

    @Headers({"Accept: application/json"})
    @GET("users/{userId}/contacts/rejected")
    Call<String> getPendingContacts(@Header("Authorization") String Authtoken, @Path("userId") String userID);
}
