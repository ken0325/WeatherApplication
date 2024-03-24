package com.example.weatherapplication;

import android.util.*;

import org.json.*;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Locale;

public class ApiController extends Thread {
    private static final String TAG = "ApiController";

    // default lat long
    private static final String defaultLat = "22.3529682", defaultLong = "113.962891";

    // call model class
    public static final ModelChineseDayInfo chineseDayInfo = new ModelChineseDayInfo();
    public static final ModelCurrentWeather currentWeather = new ModelCurrentWeather();
    public static String language = "en";

    // 香港天文台Hong Kong Observatory API
    // 公曆與農曆對照表
    public static String makeRequest() {
        String response = null;
        String jsonUrl = "https://data.weather.gov.hk/weatherAPI/opendata/lunardate.php?date=" + java.time.LocalDate.now();

        if (Locale.getDefault().getCountry().equals("CN")) {
            language = "sc";
        } else if (Locale.getDefault().getCountry().equals("HK")) {
            language = "tc";
        } else {
            language = "en";
        }

        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = publicInputStreamToString(in);
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

    // default Current/Today HongKong Weather API
    // (using ECMWF IFS 0.4° Weather model, maxTemp, minTemp, nowTemp)
    public static String currentWeather() {
        String currentLat = MainActivity.lat;
        String currentLong = MainActivity.lon;

        String response = null;
        String jsonUrl;

        if (currentLat == null) {
            jsonUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + defaultLat + "&longitude=" + defaultLong + "&current=temperature_2m,relative_humidity_2m,wind_speed_10m,wind_direction_10m,wind_gusts_10m&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration,uv_index_max,precipitation_sum,rain_sum,showers_sum&timezone=auto&forecast_days=1&models=ecmwf_ifs04";
        } else {
            jsonUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + currentLat + "&longitude=" + currentLong + "&current=temperature_2m,relative_humidity_2m,wind_speed_10m,wind_direction_10m,wind_gusts_10m&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration,uv_index_max,precipitation_sum,rain_sum,showers_sum&timezone=auto&forecast_days=1&models=ecmwf_ifs04";
        }

        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = publicInputStreamToString(in);
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
    public static String publicInputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return sb.toString();
    }

    public void run() {
//        String test = Locale.getDefault().getDisplayLanguage();

        // 公曆與農曆對照表
        String dayStr = makeRequest();
        Log.e(TAG, "Response from url: " + dayStr);
        if (dayStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(dayStr);
                chineseDayInfo.setLunarYear(jsonObj.getString("LunarYear"));
                chineseDayInfo.setLunarDate(jsonObj.getString("LunarDate"));
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get 公曆與農曆對照表 json from HKO server.");
        }

        // currentWeather
        String currentWeatherData = currentWeather();
        Log.e(TAG, "Response from url: " + currentWeatherData);
        if (currentWeatherData != null) {
            try {
                JSONObject jsonObj = new JSONObject(currentWeatherData);
                currentWeather.setLatitude(jsonObj.getDouble("latitude"));
                currentWeather.setLongitude(jsonObj.getDouble("longitude"));
                currentWeather.setGenerationtime_ms(jsonObj.getDouble("generationtime_ms"));
                currentWeather.setUtc_offset_seconds(jsonObj.getInt("utc_offset_seconds"));
                currentWeather.setTimezone(jsonObj.getString("timezone"));
                currentWeather.setTimezone_abbreviation(jsonObj.getString("timezone_abbreviation"));
                currentWeather.setElevation(jsonObj.getInt("elevation"));
                currentWeather.setCurrent_units(jsonObj.getJSONObject("current_units"));
                currentWeather.setCurrent(jsonObj.getJSONObject("current"));
                currentWeather.setDaily_units(jsonObj.getJSONObject("daily_units"));
                currentWeather.setDaily(jsonObj.getJSONObject("daily"));
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get current Weather json from open-meteo server.");
        }
    }
}
