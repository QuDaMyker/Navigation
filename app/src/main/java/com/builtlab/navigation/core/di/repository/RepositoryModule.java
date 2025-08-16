package com.builtlab.navigation.core.di.repository;

import android.content.Context;

import com.builtlab.navigation.core.repository.GemSpeakRepositoryImpl;
import com.builtlab.navigation.core.repository.IGemSpeakRepository;
import com.builtlab.navigation.core.storage.UserStorage;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Provides
    @Singleton
    public IGemSpeakRepository provideGemSpeakRepository() {
        return new GemSpeakRepositoryImpl();
    }

    @Provides
    @Singleton
    public UserStorage provideUserStorage(@ApplicationContext Context context){
        try {
            return new UserStorage(context);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Failed to create UserStorage", e);
        }
    }
}
