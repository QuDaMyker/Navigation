package com.builtlab.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> data = new MutableLiveData<>();

    public MutableLiveData<String> getData() {
        return data;
    }

    public void setData(String value) {
        data.setValue(value);
    }
}
