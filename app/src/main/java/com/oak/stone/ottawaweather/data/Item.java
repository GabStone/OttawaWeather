package com.oak.stone.ottawaweather.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item implements JSONpopulator {

    private final int FORECAST_DAYS = 3;
    private Condition condition;
    private Forecast [] forecast;

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

        // Depends how many days we want to forecast
        // mel said 3 but maybe 5 would be nice
        forecast = new Forecast[FORECAST_DAYS];

        JSONArray forecastArray = data.optJSONArray("forecast");

        forecast[0] = new Forecast();
        forecast[1] = new Forecast();
        forecast[2] = new Forecast();


        try {
            forecast[0].populate((JSONObject) forecastArray.get(1));
            forecast[1].populate((JSONObject) forecastArray.get(2));
            forecast[2].populate((JSONObject) forecastArray.get(3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Condition getCondition() {
        return condition;
    }

    public Forecast [] getForecast() {return forecast;}
}
