package com.oak.stone.ottawaweather.data;

import org.json.JSONObject;

public class Channel implements JSONpopulator {

    private Item item;
    private Wind wind;
    private Forecast forecast;

    @Override
    public void populate(JSONObject data) {
        item = new Item();
        item.populate(data.optJSONObject("item"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));
    }

    public Item getItem() {return item;}

    public Wind getWind() {
        return wind;
    }
}
