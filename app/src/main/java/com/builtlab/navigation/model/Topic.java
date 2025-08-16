package com.builtlab.navigation.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kotlinx.serialization.Serializable;

@Serializable
public class Topic {
    private String id;
    private String title;
    private String description;
    @SerializedName(value = "create_at")
    private String createAt;
    private List<Question> questions;
}
