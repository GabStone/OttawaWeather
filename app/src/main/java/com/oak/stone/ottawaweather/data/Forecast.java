package com.oak.stone.ottawaweather.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Gabriel on 2016-06-12.
 */
public class Forecast implements JSONpopulator {

    private int code;
    private String date;
    private String day;
    private int high;
    private int low;
    private String description;

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        date = data.optString("date");
        day = data.optString("day");
        high = data.optInt("high");
        low = data.optInt("low");
        description = data.optString("text");
    }

    public int getCode() {return code;}
    public String getDate() {return date;}
    public String getDay() {return day;}
    public int getHigh() {return high;}
    public int getLow() {return low;}
    public String getDescription() {return description;}
}