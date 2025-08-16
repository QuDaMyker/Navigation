package com.builtlab.navigation.core.repository;

import androidx.lifecycle.LiveData;

import com.builtlab.navigation.model.Question;
import com.builtlab.navigation.model.Topic;
import com.builtlab.navigation.model.request.LoginRequest;
import com.builtlab.navigation.model.response.LoginResponse;

import java.util.List;

public interface IGemSpeakRepository {
    LiveData<String> getHelloWorld();

    LiveData<String> refreshToken(String refreshToken);

    LiveData<LoginResponse> login(LoginRequest request);

    LiveData<List<Question>> getQuestionsById(String id);

    LiveData<List<Topic>> getTopics();

}
