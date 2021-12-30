package com.projects.co2monitor;

public interface PPMCallback {
    void onSuccess(String response);
    void onFailure();
}
