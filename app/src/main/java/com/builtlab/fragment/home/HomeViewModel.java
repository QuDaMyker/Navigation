package com.builtlab.fragment.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.builtlab.model.User;
import com.builtlab.core.repository.GemSpeakRepositoryImpl;
import com.builtlab.core.repository.IGemSpeakRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private final IGemSpeakRepository gemSpeakRepository;
    private MutableLiveData<List<User>> users;

    public HomeViewModel() {
        this.gemSpeakRepository = new GemSpeakRepositoryImpl();
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
