package com.example.weatherapplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiLocalWeatherForecast extends Thread {
    private static final String TAG = "ApiLocalWeatherForecast";
    public static final ModelLocalWeatherForecast localWeatherForecast = new ModelLocalWeatherForecast();

    public void run() {
        String localWeatherData = makeRequest();
        Log.e(TAG, "Response from url: " + localWeatherData);
        if (localWeatherData != null) {
            try {
                JSONObject jsonObj = new JSONObject(localWeatherData);
                localWeatherForecast.setGeneralSituation(jsonObj.getString("generalSituation"));
                localWeatherForecast.setTcInfo(jsonObj.getString("tcInfo"));
                localWeatherForecast.setFireDangerWarning(jsonObj.getString("fireDangerWarning"));
                localWeatherForecast.setForecastPeriod(jsonObj.getString("forecastPeriod"));
                localWeatherForecast.setForecastDesc(jsonObj.getString("forecastDesc"));
                localWeatherForecast.setOutlook(jsonObj.getString("outlook"));
                localWeatherForecast.setUpdateTime(jsonObj.getString("updateTime"));
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get localWeatherForecast json from HKO server.");
        }
    }

    public static String makeRequest() {
        String response = null;
        String jsonUrl = "https://data.weather.gov.hk/weatherAPI/opendata/weather.php?dataType=flw&lang=" + ApiController.language;

        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = ApiController.publicInputStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }
}
