package com.oak.stone.ottawaweather.service;

import com.oak.stone.ottawaweather.data.Channel;

public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
