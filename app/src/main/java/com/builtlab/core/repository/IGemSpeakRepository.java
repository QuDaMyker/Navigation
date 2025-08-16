package com.builtlab.core.repository;

import androidx.lifecycle.LiveData;

import com.builtlab.model.request.LoginRequest;
import com.builtlab.model.response.LoginResponse;

public interface IGemSpeakRepository {
    LiveData<String> getHelloWorld();
    LiveData<LoginResponse> login(LoginRequest request);
}
