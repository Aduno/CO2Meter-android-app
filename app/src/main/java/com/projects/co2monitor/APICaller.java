package com.projects.co2monitor;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This is a helper class for any server related functionality for the application
 */
public class APICaller {
    //Server URL -- May be implement this such that it is not static (allow application user to change the URL
    public static final String url = new String("http://192.168.1.3:3000/api/reading");
    /**
     * This method sends a request to the server and passes the response to the callback
     * function. If successful
     * @param callback
     * @param context
     */
    public static void getPPMReading(PPMCallback callback, Context context, SensorOptions sensor){
        String sensorURL = null;
        // Set URL correctly to the sensor value
        if(sensor == SensorOptions.sensor1){
            sensorURL = url + "/1";
        }else if(sensor == SensorOptions.sensor2){
            sensorURL = url +"/2";
        }else if(sensor == SensorOptions.sensor3){
            sensorURL = url +"/3";
        }

        //Applying singleton pattern as suggested by the documentation as the application makes
        // constant use of the network and will be more efficient to setup a single requestqueue instance
        // for the lifetime of the application
        RequestQueueSingleton requestQueueSingleton = RequestQueueSingleton.getInstance(context);

        //Call to server for ppm value
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,sensorURL,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Output", response.toString());
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Output", error.toString());
                callback.onFailure();
            }
        });
        requestQueueSingleton.addToRequestQueue(req);
    }
}
