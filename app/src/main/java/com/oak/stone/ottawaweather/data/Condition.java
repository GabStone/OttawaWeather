package com.oak.stone.ottawaweather.data;

import org.json.JSONObject;

public class Condition implements JSONpopulator {

    private int code;
    private int temp;
    private String description;

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temp = data.optInt("temp");
        description = data.optString("text");
    }

    public int getCode() {
        return code;
    }
    public int getTemp() {
        return temp;
    }
    public String getDescription() {
        return description;
    }
}
