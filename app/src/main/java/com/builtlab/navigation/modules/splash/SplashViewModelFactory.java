package com.builtlab.navigation.modules.splash;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.builtlab.navigation.core.repository.IGemSpeakRepository;
import com.builtlab.navigation.core.storage.UserStorage;

public class SplashViewModelFactory implements ViewModelProvider.Factory {
    private final IGemSpeakRepository gemSpeakRepository;
    private final UserStorage userStorage;
    public SplashViewModelFactory(IGemSpeakRepository gemSpeakRepository, UserStorage storage) {
        this.gemSpeakRepository = gemSpeakRepository;
        this.userStorage = storage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(gemSpeakRepository, userStorage);
        }
        return ViewModelProvider.Factory.super.create(modelClass);
    }

//    @Override
//    public <T extends androidx.lifecycle.ViewModel> T create(Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
//            return (T) new SplashViewModel(gemSpeakRepository);
//        }
//        throw new IllegalArgumentException("Unknown ViewModel class");
//    }
}
