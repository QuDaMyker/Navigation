package com.builtlab.navigation.model.enums;

import androidx.annotation.NonNull;

public enum LevelQuestion {
    HARD,
    MEDIUM,
    EASY;

    @NonNull
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public static LevelQuestion fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        try {
            return valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value for LevelQuestion: " + value);
        }
    }
}