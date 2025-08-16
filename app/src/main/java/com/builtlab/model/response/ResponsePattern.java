package com.builtlab.model.response;

public class ResponsePattern<T> {
    private Boolean success;
    private T data;
    private String timestamp;

    public ResponsePattern(Boolean success, T data, String timestamp) {
        this.success = success;
        this.data = data;
        this.timestamp = timestamp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
