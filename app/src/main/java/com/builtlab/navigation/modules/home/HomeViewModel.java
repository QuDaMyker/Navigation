package com.builtlab.navigation.modules.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.builtlab.navigation.core.repository.GemSpeakRepositoryImpl;
import com.builtlab.navigation.core.repository.IGemSpeakRepository;
import com.builtlab.navigation.core.storage.UserStorage;
import com.builtlab.navigation.model.User;
import com.builtlab.navigation.model.request.LoginRequest;
import com.builtlab.navigation.model.response.LoginResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private final IGemSpeakRepository gemSpeakRepository;
    private MutableLiveData<List<User>> users;
    private UserStorage userStorage;

    public HomeViewModel(Context context) throws GeneralSecurityException, IOException {
        this.gemSpeakRepository = new GemSpeakRepositoryImpl();
        this.userStorage = new UserStorage(context);
    }

    public LiveData<List<User>> getUsers() {
        LiveData<String> helloRs = this.gemSpeakRepository.getHelloWorld();
        if (helloRs != null) {
            Log.e("HomeViewModel", "getUsers: " + helloRs.getValue());
        } else {
            Log.e("HomeViewModel", "getUsers: helloRs is null");
        }
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    public LiveData<String> login(LoginRequest request) {
        MutableLiveData<String> emailLiveData = new MutableLiveData<>();

        if (userStorage.getCrrUser() == null) {
            LiveData<LoginResponse> loginResponse = this.gemSpeakRepository.login(request);
            loginResponse.observeForever(response -> {
                if (response != null && response.getUser() != null) {
                    userStorage.saveLoginResponse(response);
                    emailLiveData.setValue(response.getUser().getEmail());
                } else {
                    emailLiveData.setValue(null);
                }
            });

        } else {
            emailLiveData.setValue(userStorage.getCrrUser().getEmail());
        }

        return emailLiveData;
    }


    private void loadUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Nguyen Van A", "a@example.com"));
        userList.add(new User("Tran Thi B", "b@example.com"));
        userList.add(new User("Le Van C", "c@example.com"));
        userList.add(new User("Nguyen Van A", "a@example.com"));
        userList.add(new User("Tran Thi B", "b@example.com"));
        userList.add(new User("Le Van C", "c@example.com"));
        userList.add(new User("Nguyen Van A", "a@example.com"));
        userList.add(new User("Tran Thi B", "b@example.com"));
        userList.add(new User("Le Van C", "c@example.com"));
        userList.add(new User("Nguyen Van A", "a@example.com"));
        userList.add(new User("Tran Thi B", "b@example.com"));
        userList.add(new User("Le Van C", "c@example.com"));
        userList.add(new User("Nguyen Van A", "a@example.com"));
        userList.add(new User("Tran Thi B", "b@example.com"));
        userList.add(new User("Le Van C", "c@example.com"));
        users.setValue(userList);
    }
}
