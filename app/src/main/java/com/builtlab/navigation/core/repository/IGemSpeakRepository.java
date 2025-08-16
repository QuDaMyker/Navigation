package com.builtlab.navigation.core.repository;

import androidx.lifecycle.LiveData;

import com.builtlab.navigation.model.request.LoginRequest;
import com.builtlab.navigation.model.response.LoginResponse;

public interface IGemSpeakRepository {
    LiveData<String> getHelloWorld();
    LiveData<LoginResponse> login(LoginRequest request);
}
