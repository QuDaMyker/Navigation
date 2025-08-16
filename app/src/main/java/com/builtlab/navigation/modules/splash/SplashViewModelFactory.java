package com.builtlab.navigation.modules.splash;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.builtlab.navigation.core.repository.IGemSpeakRepository;

public class SplashViewModelFactory implements ViewModelProvider.Factory {
    private final IGemSpeakRepository gemSpeakRepository;

    public SplashViewModelFactory(IGemSpeakRepository gemSpeakRepository) {
        this.gemSpeakRepository = gemSpeakRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(gemSpeakRepository);
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
