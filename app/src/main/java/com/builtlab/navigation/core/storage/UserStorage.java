package com.builtlab.navigation.core.storage;

import android.content.Context;

import com.builtlab.navigation.model.User;
import com.builtlab.navigation.model.response.LoginResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class UserStorage extends StorageManager {
    private static User user;
    private static final String KEY_USER = "user";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";

    public UserStorage(Context context) throws GeneralSecurityException, IOException {
        super(context);
    }

    public void saveCrrUser(User userName) {
        this.prefs.edit().putString(KEY_USER, userName.toString()).apply();
    }

    public User getCrrUser() {
        return User.fromString(prefs.getString(KEY_USER, null));
    }

    public void saveToken(String accessToken, String refreshToken) {
        securePrefs.edit().putString(KEY_ACCESS_TOKEN, accessToken).putString(KEY_REFRESH_TOKEN, refreshToken).apply();
    }

    public String getAccessToken() {
        return securePrefs.getString(KEY_ACCESS_TOKEN, null);
    }

    public String getRefreshToken() {
        return securePrefs.getString(KEY_REFRESH_TOKEN, null);
    }

    public void saveLoginResponse(LoginResponse response) {
        saveCrrUser(response.getUser());
        saveToken(response.getAccessToken(), response.getRefreshToken());
    }

    @Override
    public void clearAll() {
        prefs.edit().remove(KEY_USER).apply();
        securePrefs.edit().remove(KEY_ACCESS_TOKEN).remove(KEY_REFRESH_TOKEN).apply();
    }
}
