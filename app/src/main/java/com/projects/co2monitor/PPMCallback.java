package com.projects.co2monitor;

import org.json.JSONObject;

public interface PPMCallback {
    void onSuccess(JSONObject response);
    void onFailure();
}
