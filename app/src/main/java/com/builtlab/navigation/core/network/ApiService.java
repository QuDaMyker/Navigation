package com.builtlab.navigation.core.network;

import com.builtlab.navigation.model.request.LoginRequest;
import com.builtlab.navigation.model.response.LoginResponse;
import com.builtlab.navigation.model.response.ResponsePattern;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("hello-world")
    Call<ResponsePattern<String>> getHelloWorld();

    @POST("auth/sign-in")
    Call<ResponsePattern<LoginResponse>> loginUser(@Body LoginRequest request);
}
