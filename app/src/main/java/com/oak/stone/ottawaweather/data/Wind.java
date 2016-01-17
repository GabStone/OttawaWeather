package com.oak.stone.ottawaweather.data;

import org.json.JSONObject;

/**
 * Created by Gabriel on 2016-01-17.
 */
public class Wind implements JSONpopulator {

    private int chill;


    @Override
    public void populate(JSONObject data) {
        chill = data.optInt("chill");
    }

    public int getChill(){
        return chill;
    }
}
