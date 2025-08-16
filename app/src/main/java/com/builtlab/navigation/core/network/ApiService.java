package com.builtlab.navigation.core.network;

import com.builtlab.navigation.model.Question;
import com.builtlab.navigation.model.Topic;
import com.builtlab.navigation.model.request.LoginRequest;
import com.builtlab.navigation.model.response.LoginResponse;
import com.builtlab.navigation.model.response.ResponsePattern;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("hello-world")
    Call<ResponsePattern<String>> getHelloWorld();

    @POST("auth/refresh-tokens")
    Call<ResponsePattern<String>> refreshToken(@Body String refreshToken);

    @POST("auth/sign-in")
    Call<ResponsePattern<LoginResponse>> loginUser(@Body LoginRequest request);

    @GET("questions/{id}")
    Call<ResponsePattern<List<Question>>> getQuestionsById(@Path("id") String id);

    @GET("speaking-topics")
    Call<ResponsePattern<List<Topic>>> getTopics();

}
