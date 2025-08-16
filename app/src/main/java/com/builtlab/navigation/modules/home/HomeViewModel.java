package com.builtlab.navigation.modules.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.builtlab.navigation.core.repository.IGemSpeakRepository;
import com.builtlab.navigation.core.storage.UserStorage;
import com.builtlab.navigation.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final IGemSpeakRepository gemSpeakRepository;
    private final UserStorage userStorage;
    public final MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>(new ArrayList<>());
    private static final String EXAMPLE_ID = "d454f839-d80e-47c1-8f66-d379d9375664";

    @Inject
    public HomeViewModel(IGemSpeakRepository gemSpeakRepository, UserStorage userStorage) {
        this.gemSpeakRepository = gemSpeakRepository;
        this.userStorage = userStorage;

        getListQuestions();
    }

    public void getListQuestions() {
        gemSpeakRepository.getQuestionsById(EXAMPLE_ID).observeForever(data -> {
            questionsLiveData.setValue(Objects.requireNonNullElseGet(data, ArrayList::new));
        });
    }

    public String getCurrentEmail() {
        return userStorage.getCrrUser().getEmail();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}