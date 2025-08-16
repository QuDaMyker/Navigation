package com.builtlab.navigation.actions;

public interface onTwoSideActions<T> {
    void onSuccess(T users);

    void onError(String errorMessage);
}
