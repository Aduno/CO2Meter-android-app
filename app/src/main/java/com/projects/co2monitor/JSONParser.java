package com.projects.co2monitor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class JSONParser {
    /**
     * Provided a list of keys to search for in the JSON response, locates and converts to a string.
     * NOTE: Does NOT work for nested items!
     * @param list
     * @param response
     * @return Hashmap with the key-value pair found in the JSON object
     */
    public static HashMap<String,String> getItemsToString(HashMap<String,String> list, JSONObject response){
        HashMap<String,String> newList = new HashMap<>();
        list.forEach((k,v)->{
            try {
                newList.put(k,response.get(k).toString());
            } catch (JSONException e) {
                System.out.println("Unable to locate "+k+" in the response");
                e.printStackTrace();
            }
        });
        return newList;
    }
    public static HashMap<String,String> getItemsToString(List<String> list, JSONObject response){
        HashMap<String,String> newList = new HashMap<>();
        list.forEach((k)->{
            try {
                newList.put(k,response.get(k).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        return newList;
    }
}
