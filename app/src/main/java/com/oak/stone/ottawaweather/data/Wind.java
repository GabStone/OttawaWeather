package com.oak.stone.ottawaweather.data;

import org.json.JSONObject;

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
