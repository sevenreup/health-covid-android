package com.skybox.seven.covid.network;

import com.skybox.seven.covid.network.responses.AccessToken;
import com.skybox.seven.covid.network.responses.ContactRequest;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.model.ContactModel;
import com.skybox.seven.covid.model.ContactRequestModel;
import com.skybox.seven.covid.network.responses.selftest.SelfTestResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
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
    Single<AccessToken> loginUser(@Field("phone") String phone, @Field("password") String password);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("updateFirebaseToken")
    Call<GenericResponse> pushToken(@Header("Authorization") String Authtoken, @Field("firebase_token") String token);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("auth/register")
    Single<GenericResponse> register(@Field("first_name")String fname, @Field("last_name")String lname, @Field("phone")String number, @Field("password")String password);

    @Headers({"Accept: application/json"})
    @POST("contact/add")
    Call<GenericResponse> saveContacts(@Header("Authorization") String Authtoken, @Body ArrayList<ContactRequest> members);

    @Headers({"Accept: application/json"})
    @GET("users/contacts")
    Single<List<ContactModel.ContactUsersContacts>> getAllContacts(@Header("Authorization") String Authtoken);

    @Headers({"Accept: application/json"})
    @GET("users/contacts/pending")
    Single<List<ContactRequestModel.PendingContacts>> getPendingContacts(@Header("Authorization") String Authtoken);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("contact/verify/user")
    Single<GenericResponse> verifyContact(@Header("Authorization") String Authtoken, @Field("id") int id, @Field("status") String status);

    @Headers({"Accept: application/json"})
    @GET("selftest/questions")
    Call<SelfTestResponse> getSelfTests(@Header("Authorization") String Authtoken);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("selftest/answers")
    Single<GenericResponse> insertSelfTestAnswer(@Header("Authorization") String Authtoken, @Field("question_id") int id, @Field("answer_array") List<Integer> array,
                                                 @Field("answer_boolean") Boolean answerBoolean, @Field("long_answer") String longAnswer);
}
