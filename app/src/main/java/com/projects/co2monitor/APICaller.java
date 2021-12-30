package com.projects.co2monitor;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * This is a helper class for any server related functionality for the application
 */
public class APICaller {
    //Server URL -- May be implement this such that it is not static (allow application user to change the URL
    public static String URL = "http://192.168.1.12:3000/meter";
    /**
     * This method sends a request to the server and passes the response to the callback
     * function. If successful
     * @param callback
     * @param context
     */
    public static void getPPMReading(PPMCallback callback, Context context, SensorOptions sensor){

        // Set URL correctly to the sensor value
        if(sensor == SensorOptions.sensor1){
            URL = URL +"/1";
        }else if(sensor == SensorOptions.sensor2){
            URL = URL +"/2";
        }else if(sensor == SensorOptions.sensor3){
            URL = URL +"/3";
        }

        //Applying singleton pattern as suggested by the documentation as the application makes
        // constant use of the network and will be more efficient to setup a single requestqueue instance
        // for the lifetime of the application
        RequestQueueSingleton requestQueueSingleton = RequestQueueSingleton.getInstance(context);
        RequestQueue queue = requestQueueSingleton.getRequestQueue();

        //Call to server for ppm value
        StringRequest req = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure();
            }
        });
        queue.add(req);
    }
}
