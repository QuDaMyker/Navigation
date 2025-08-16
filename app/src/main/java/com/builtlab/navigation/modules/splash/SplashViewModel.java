package com.builtlab.navigation.modules.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.builtlab.navigation.core.network.ApiClient;
import com.builtlab.navigation.core.repository.IGemSpeakRepository;
import com.builtlab.navigation.core.storage.UserStorage;
import com.builtlab.navigation.model.request.LoginRequest;
import com.builtlab.navigation.model.response.LoginResponse;

public class SplashViewModel extends ViewModel {
    private LiveData<String> helloRs;
    private final IGemSpeakRepository gemSpeakRepository;

    private final UserStorage userStorage;

    public SplashViewModel(IGemSpeakRepository gemSpeakRepository, UserStorage userStorage) {
        this.gemSpeakRepository = gemSpeakRepository;
        this.userStorage = userStorage;
    }

    public LiveData<String> getHelloRs() {
        if (helloRs == null) {
            helloRs = gemSpeakRepository.getHelloWorld();
        }
        return helloRs;
    }

    public LiveData<Boolean> handleRefreshToken() {
        MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>(false);
        String accessToken = userStorage.getAccessToken();
        if (accessToken == null) {
            isAuthenticated.setValue(false);
        } else {
            String refreshToken = userStorage.getRefreshToken();
            if (refreshToken != null) {
                String newAccessToken = gemSpeakRepository.refreshToken(refreshToken).getValue();
                if (newAccessToken != null) {
                    isAuthenticated.setValue(true);
                } else {
                    isAuthenticated.setValue(false);
                }
            } else {
                userStorage.clearAll();
                return this.login();
            }
        }
        return isAuthenticated;
    }

    public LiveData<Boolean> login() {
        MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>(false);
        LiveData<LoginResponse> response = gemSpeakRepository.login(new LoginRequest("user@gmail.com", "123456"));

        response.observeForever(loginResponse -> {
            if (loginResponse == null) {
                isAuthenticated.setValue(false);
            } else {
                if (loginResponse.getAccessToken() != null && loginResponse.getRefreshToken() != null) {
                    userStorage.saveLoginResponse(loginResponse);
                    ApiClient.updateAccessToken(loginResponse.getAccessToken());
                    isAuthenticated.setValue(true);
                } else {
                    isAuthenticated.setValue(false);
                }
            }
        });

        return isAuthenticated;
    }
}
