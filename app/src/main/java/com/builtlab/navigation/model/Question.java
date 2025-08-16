package com.builtlab.navigation.model;


import com.builtlab.navigation.model.enums.LevelQuestion;
import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

//{
//        "id": "7fab155c-ee42-4b97-9128-3630086aae3f",
//        "question_text": "What time do you usually wake up?",
//        "language_code": "en-US",
//        "difficulty_level": "medium",
//        "topic": "d454f839-d80e-47c1-8f66-d379d9375664",
//        "created_at": "2025-07-19T01:23:44.426Z"
//        },
@Serializable
public class Question {
    private String id;
    @SerializedName(value = "question_text")
    private String questionText;
    @SerializedName(value = "language_code")
    private String languageCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public LevelQuestion getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(LevelQuestion difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @SerializedName(value = "difficulty_level")
    private LevelQuestion difficultyLevel;
    @SerializedName(value = "topic")
    private String topicId;
    @SerializedName(value = "created_at")
    private String createdAt;

}
