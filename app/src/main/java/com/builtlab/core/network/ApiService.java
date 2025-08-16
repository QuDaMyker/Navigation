package com.builtlab.core.network;

import com.builtlab.model.request.LoginRequest;
import com.builtlab.model.response.LoginResponse;
import com.builtlab.model.response.ResponsePattern;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("hello-world")
    Call<ResponsePattern<String>> getHelloWorld();

    @POST("auth/sign-in")
    Call<ResponsePattern<LoginResponse>> loginUser(LoginRequest request);
}
