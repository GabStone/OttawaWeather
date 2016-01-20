package com.oak.stone.ottawaweather.service;

import android.net.Uri;
import android.os.AsyncTask;
import com.oak.stone.ottawaweather.data.Channel;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class YahooWeatherService {

    private WeatherServiceCallBack callBack;
    private String location;
    private Exception error;

    public  YahooWeatherService(WeatherServiceCallBack callBack) {
        this.callBack = callBack;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(String l) {

        this.location = l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {

                // Ottawa WOEID = 29375164
                String YQL = String.format("select * from weather.forecast where woeid = 29375164", strings[0]);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return result.toString();

                } catch (Exception e) {
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null) {
                    callBack.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResult = data.getJSONObject("query");
                    int count = queryResult.optInt("count");

                    // not valid city or no data for that city
                    if (count == 0) {
                        callBack.serviceFailure(new LocationWeatherException("No Weather Information found for + " + location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResult.optJSONObject("results").optJSONObject("channel"));

                    callBack.serviceSuccess(channel);

                } catch (JSONException e) {
                    callBack.serviceFailure(e);
                }
            }
        }.execute(location);
    }

    public class LocationWeatherException extends Exception {
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}