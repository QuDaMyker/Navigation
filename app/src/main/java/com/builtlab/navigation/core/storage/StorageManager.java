package com.builtlab.navigation.core.storage;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class StorageManager {
    private static final String PREF_NAME = "app_prefs";

    protected SharedPreferences prefs;
    protected SharedPreferences securePrefs;

    public StorageManager(Context context) throws GeneralSecurityException, IOException {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        MasterKey masterKey = new MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

        securePrefs = EncryptedSharedPreferences.create(
                context,
                PREF_NAME + "_secure",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }

    public void clearAll() {
        prefs.edit().clear().apply();
        securePrefs.edit().clear().apply();
    }
}
