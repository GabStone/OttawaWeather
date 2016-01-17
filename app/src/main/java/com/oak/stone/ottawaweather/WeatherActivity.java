package com.oak.stone.ottawaweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oak.stone.ottawaweather.data.Channel;
import com.oak.stone.ottawaweather.data.Item;
import com.oak.stone.ottawaweather.service.WeatherServiceCallBack;
import com.oak.stone.ottawaweather.service.YahooWeatherService;

public class WeatherActivity extends Activity implements WeatherServiceCallBack {

    private ImageView weatherIconImageView;
    private TextView tempTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView windChillTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIconImageView = (ImageView) findViewById(R.id.imageView2);
        tempTextView = (TextView) findViewById(R.id.textView3);
        conditionTextView = (TextView) findViewById(R.id.textView4);
        locationTextView = (TextView) findViewById(R.id.textView5);
        windChillTextView = (TextView) findViewById(R.id.textView6);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        dialog.show();


        service.refreshWeather("Ottawa, Ontario");
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();

        locationTextView.setText(service.getLocation());

        int resourceId = getResources().getIdentifier("drawable/icon_" + channel.getItem().getCondition().getCode(), null, getPackageName());

        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        int temp_f = item.getCondition().getTemp();

        temp_f = temp_f - 32;
        temp_f = (temp_f *5) / 9;

        String og_units = channel.getUnits().getTemp();

        tempTextView.setText(temp_f + "\u00B0" + "C");
        conditionTextView.setText(item.getCondition().getDescription());

        int og_chill = channel.getWind().getChill();

        og_chill = og_chill - 32;
        og_chill = (og_chill*5) / 9;

        windChillTextView.setText(og_chill + " this is the chill ");



    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
