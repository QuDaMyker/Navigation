package com.builtlab.navigation.core.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.builtlab.navigation.core.network.ApiClient;
import com.builtlab.navigation.core.network.ApiService;
import com.builtlab.navigation.model.request.LoginRequest;
import com.builtlab.navigation.model.response.LoginResponse;
import com.builtlab.navigation.model.response.ResponsePattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GemSpeakRepositoryImpl implements IGemSpeakRepository {
    private ApiService apiService;

    public GemSpeakRepositoryImpl() {
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public LiveData<String> getHelloWorld() {
        MutableLiveData<String> data = new MutableLiveData<>();
        apiService.getHelloWorld().enqueue(new Callback<ResponsePattern<String>>() {
            @Override
            public void onResponse(Call<ResponsePattern<String>> call, Response<ResponsePattern<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body().getData());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponsePattern<String>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    @Override
    public LiveData<LoginResponse> login(LoginRequest request) {
        MutableLiveData<LoginResponse> res = new MutableLiveData<>();

        apiService.loginUser(request).enqueue(new Callback<ResponsePattern<LoginResponse>>() {
            @Override
            public void onResponse(Call<ResponsePattern<LoginResponse>> call,
                                   Response<ResponsePattern<LoginResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse responsePattern = response.body().getData();

                    // Now getData() returns LoginResponse
                    res.setValue(responsePattern);
                } else {
                    res.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponsePattern<LoginResponse>> call, Throwable t) {
                res.setValue(null);
            }
        });

        return res;
    }


}
