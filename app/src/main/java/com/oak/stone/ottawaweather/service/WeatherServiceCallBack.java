package com.oak.stone.ottawaweather.service;

import com.oak.stone.ottawaweather.data.Channel;

/**
 * Created by Gabriel on 2016-01-17.
 */
public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
