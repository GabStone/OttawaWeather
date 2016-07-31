package com.oak.stone.ottawaweather;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.oak.stone.ottawaweather.data.Channel;
import com.oak.stone.ottawaweather.data.Forecast;
import com.oak.stone.ottawaweather.data.Item;
import com.oak.stone.ottawaweather.service.WeatherServiceCallBack;
import com.oak.stone.ottawaweather.service.YahooWeatherService;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ForecastActivity extends Activity implements WeatherServiceCallBack {

    // This is for day 1
    private ImageView codeImageView_day1;
    private TextView dateTextView_day1;
    private TextView dayTextView_day1;
    private TextView highTextView_day1;
    private TextView lowTextView_day1;
    private TextView descriptionTextView_day1;


    // This is for day 2
    private ImageView codeImageView_day2;
    private TextView dateTextView_day2;
    private TextView dayTextView_day2;
    private TextView highTextView_day2;
    private TextView lowTextView_day2;
    private TextView descriptionTextView_day2;


    // This is for day 3
    private ImageView codeImageView_day3;
    private TextView dateTextView_day3;
    private TextView dayTextView_day3;
    private TextView highTextView_day3;
    private TextView lowTextView_day3;
    private TextView descriptionTextView_day3;

    private YahooWeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove Title Bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forecast);





        // For day 1
        codeImageView_day1 = (ImageView) findViewById(R.id.day1_code);
        dateTextView_day1 = (TextView) findViewById(R.id.day1_date);
        dayTextView_day1 = (TextView) findViewById(R.id.day1_day);
        highTextView_day1 = (TextView) findViewById(R.id.day1_high);
        lowTextView_day1 = (TextView) findViewById(R.id.day1_low);
        descriptionTextView_day1 = (TextView) findViewById(R.id.day1_description);

        // For day 2
        codeImageView_day2 = (ImageView) findViewById(R.id.day2_code);
        dateTextView_day2 = (TextView) findViewById(R.id.day2_date);
        dayTextView_day2 = (TextView) findViewById(R.id.day2_day);
        highTextView_day2 = (TextView) findViewById(R.id.day2_high);
        lowTextView_day2 = (TextView) findViewById(R.id.day2_low);
        descriptionTextView_day2 = (TextView) findViewById(R.id.day2_description);

        // For day 3
        codeImageView_day3 = (ImageView) findViewById(R.id.day3_code);
        dateTextView_day3 = (TextView) findViewById(R.id.day3_date);
        dayTextView_day3 = (TextView) findViewById(R.id.day3_day);
        highTextView_day3 = (TextView) findViewById(R.id.day3_high);
        lowTextView_day3 = (TextView) findViewById(R.id.day3_low);
        descriptionTextView_day3 = (TextView) findViewById(R.id.day3_description);

        service = new YahooWeatherService(this);
        service.refreshWeather("Ottawa, Ontario");

                // Ad Mob
                AdView adView = (AdView) findViewById(R.id.adViewForecast);
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
    }

    /*
    Weather Service Callback methods
     */

    @Override
    public void serviceSuccess(Channel channel) {
        // When the api call is a success

        Item item = channel.getItem();
        Forecast [] forecast = item.getForecast();

        // Setting the data for the first day
        int resourceId_day1 = getResources().getIdentifier("drawable/icon_" + channel.getItem().getForecast()[0].getCode(), null, getPackageName());
        Drawable weatherIconDrawable_day1 = getResources().getDrawable(resourceId_day1);
        codeImageView_day1.setImageDrawable(weatherIconDrawable_day1);

        dateTextView_day1.setText(forecast[0].getDate());
        dayTextView_day1.setText(forecast[0].getDay());

        // High in Celsius
        int high_c = ((forecast[0].getHigh() - 32) * 5) / 9;
        highTextView_day1.setText("High " + high_c + "\u00B0" + "C");

        // Low in Celsius
        int low_c = ((forecast[0].getLow() -32) * 5) / 9;
        lowTextView_day1.setText("Low " + low_c + "\u00B0" + "C");

        descriptionTextView_day1.setText(forecast[0].getDescription() + "");

        // Setting the data for the second day
        int resourceId_day2 = getResources().getIdentifier("drawable/icon_" + channel.getItem().getForecast()[1].getCode(), null, getPackageName());
        Drawable weatherIconDrawable_day2 = getResources().getDrawable(resourceId_day2);
        codeImageView_day2.setImageDrawable(weatherIconDrawable_day2);

        dateTextView_day2.setText(forecast[1].getDate());
        dayTextView_day2.setText(forecast[1].getDay());

        // High in Celsius
        int high_c_day2 = ((forecast[1].getHigh() - 32) * 5) / 9;
        highTextView_day2.setText("High " + high_c_day2 + "\u00B0" + "C");

        // Low in Celsius
        int low_c_day2 = ((forecast[1].getLow() -32) * 5) / 9;
        lowTextView_day2.setText("Low " + low_c_day2 + "\u00B0" + "C");

        descriptionTextView_day2.setText(forecast[1].getDescription() + "");


        // Setting the data for the third day
        int resourceId_day3 = getResources().getIdentifier("drawable/icon_" + channel.getItem().getForecast()[2].getCode(), null, getPackageName());
        Drawable weatherIconDrawable_day3 = getResources().getDrawable(resourceId_day3);
        codeImageView_day3.setImageDrawable(weatherIconDrawable_day3);

        dateTextView_day3.setText(forecast[2].getDate());
        dayTextView_day3.setText(forecast[2].getDay());

        // High in Celsius
        int high_c_day3 = ((forecast[2].getHigh() - 32) * 5) / 9;
        highTextView_day3.setText("High " + high_c_day3 + "\u00B0" + "C");

        // Low in Celsius
        int low_c_day3 = ((forecast[2].getLow() -32) * 5) / 9;
        lowTextView_day3.setText("Low " + low_c_day3 + "\u00B0" + "C");

        descriptionTextView_day3.setText(forecast[2].getDescription() + "");

    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void back(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }
}