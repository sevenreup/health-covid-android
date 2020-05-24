package com.skybox.seven.covid.network;

import com.skybox.seven.covid.network.responses.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsService {
    @Headers({"Accept: application/json"})
    @GET("everything")
    Call<NewsResponse> getNews(@Query("q") String query, @Query("qInTitle") String title,
                               @Query("pageSize") int pageSize, @Query("from") String from,
                               @Query("to") String to, @Query("sortBy") String sort,
                               @Query("apiKey") String apiKey);
}
