package com.builtlab.navigation.modules.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.builtlab.navigation.core.repository.IGemSpeakRepository;

public class SplashViewModel extends ViewModel {
    private LiveData<String> helloRs;
    private IGemSpeakRepository gemSpeakRepository;

    public SplashViewModel(IGemSpeakRepository gemSpeakRepository) {
        this.gemSpeakRepository = gemSpeakRepository;
    }

    public LiveData<String> getHelloRs(){
        if (helloRs == null) {
            helloRs = gemSpeakRepository.getHelloWorld();
        }
        return helloRs;
    }
}
