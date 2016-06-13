package com.oak.stone.ottawaweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oak.stone.ottawaweather.data.Channel;
import com.oak.stone.ottawaweather.data.Forecast;
import com.oak.stone.ottawaweather.data.Item;
import com.oak.stone.ottawaweather.service.WeatherServiceCallBack;
import com.oak.stone.ottawaweather.service.YahooWeatherService;

public class ForecastActivity extends Activity implements WeatherServiceCallBack {

    // This is only for one day
    private ImageView codeImageView;
    private TextView dateTextView;
    private TextView dayTextView;
    private TextView highTextView;
    private TextView lowTextView;
    private TextView descriptionTextView;

    private YahooWeatherService service;


    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove Title Bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forecast);

        // Get all textviews (for one day)
        // prob need to make an object that contains all this

        codeImageView = (ImageView) findViewById(R.id.imageView4);
        dateTextView = (TextView) findViewById(R.id.textView7);
        dayTextView = (TextView) findViewById(R.id.textView8);
        highTextView = (TextView) findViewById(R.id.textView9);
        lowTextView = (TextView) findViewById(R.id.textView10);
        descriptionTextView = (TextView) findViewById(R.id.textView11);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        //dialog.setMessage("loading...");
        //dialog.show();
        service.refreshWeather("Ottawa, Ontario");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
    Weather Service Callback methods
     */

    @Override
    public void serviceSuccess(Channel channel) {
        // When the api call is a success

        Item item = channel.getItem();
        Forecast [] forecast = item.getForecast();


        int resourceId = getResources().getIdentifier("drawable/icon_" + channel.getItem().getCondition().getCode(), null, getPackageName());
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
        codeImageView.setImageDrawable(weatherIconDrawable);





        dateTextView.setText(forecast[0].getDate());
        dayTextView.setText(forecast[0].getDay());
        highTextView.setText(forecast[0].getHigh() + "");
        lowTextView.setText(forecast[0].getLow() + "");
        descriptionTextView.setText(forecast[0].getDescription() + "");








    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }


    public void back(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }
}
