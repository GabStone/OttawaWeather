package com.oak.stone.ottawaweather.data;

import org.json.JSONObject;

/**
 * Created by Gabriel on 2016-01-17.
 */
public class Units implements JSONpopulator {

    private String temp;


    @Override
    public void populate(JSONObject data) {
        temp = data.optString("temperature");
    }

    public String getTemp() {
        return temp;
    }
}
