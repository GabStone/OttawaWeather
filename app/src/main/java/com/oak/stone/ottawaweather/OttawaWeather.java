package com.oak.stone.ottawaweather;

import android.app.Application;
import android.content.res.Configuration;

import com.onesignal.OneSignal;

/**
 * Created by Gabriel on 2016-06-07.
 */
public class OttawaWeather extends Application {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this).init();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
