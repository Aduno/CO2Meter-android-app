package com.projects.co2monitor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQueueSingleton {
    private static RequestQueueSingleton instance;
    private RequestQueue reqQueue;
    private static Context context;

    private RequestQueueSingleton(Context context){
        this.context = context;
        reqQueue = getRequestQueue();
    }

    public static synchronized RequestQueueSingleton getInstance(Context context){
        if(instance==null){
            instance = new RequestQueueSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(reqQueue==null){
            reqQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return reqQueue;
    }

    public<T> void addToRequestQueue(Request<T> request){
        reqQueue.add(request);
    }
}
