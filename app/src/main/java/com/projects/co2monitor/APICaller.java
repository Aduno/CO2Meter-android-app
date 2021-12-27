package com.projects.co2monitor;

public class APICaller {
    public static int getInformation(PPMCallback callback){
        //Call to server for ppm value
        int ppm = 0;

        callback.onSuccess(ppm);
    }
}
