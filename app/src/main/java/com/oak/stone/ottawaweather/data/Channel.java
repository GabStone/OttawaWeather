package com.oak.stone.ottawaweather.data;

import org.json.JSONObject;

/**
 * Created by Gabriel on 2016-01-17.
 */
public class Channel implements JSONpopulator {


    private Item item;
    private Units units;
    private Wind wind;



    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));
    }

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    public Wind getWind() {
        return wind;
    }
}
