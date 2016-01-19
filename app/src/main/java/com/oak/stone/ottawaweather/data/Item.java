package com.oak.stone.ottawaweather.data;

import org.json.JSONObject;

public class Item implements JSONpopulator {

    private Condition condition;

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }

    public Condition getCondition() {
        return condition;
    }
}
