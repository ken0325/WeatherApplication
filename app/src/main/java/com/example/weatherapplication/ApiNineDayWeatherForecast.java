package com.example.weatherapplication;

import android.util.Log;

import org.json.JSONArray;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ApiNineDayWeatherForecast extends Thread {
    private static final String TAG = "ApiNineDatWeatherForecast";

    public static final ModelNineDayWeatherForecast hkoNineDayWeatherForecast = new ModelNineDayWeatherForecast();

    public static String makeRequest() {
        String response = null;
        String jsonUrl = "https://data.weather.gov.hk/weatherAPI/opendata/weather.php?dataType=fnd&lang=" + ApiController.language;

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

    public void run() {
        String dayStr = makeRequest();
        Log.e(TAG, "Response from url: " + dayStr);
        if (dayStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(dayStr);
                hkoNineDayWeatherForecast.setGeneralSituation(jsonObj.getString("generalSituation"));
                JSONArray nineWeather = jsonObj.getJSONArray("weatherForecast");
                for (int i = 0; i < nineWeather.length(); i++) {
                    JSONObject c = nineWeather.getJSONObject(i);
                    String forecastDate = c.getString("forecastDate");

                    LocalDate date = LocalDate.parse(forecastDate, DateTimeFormatter.BASIC_ISO_DATE);

                    DateTimeFormatter pattern;
                    if (Locale.getDefault().getCountry().equals("CN") || Locale.getDefault().getCountry().equals("HK")) {
                        pattern = DateTimeFormatter.ofPattern("MM月dd日", Locale.CHINA);
                    } else {
                        pattern = DateTimeFormatter.ofPattern("dd-MMM", Locale.ENGLISH);
                    }
                    String formatedForecastDate = date.format(pattern);
                    String week = c.getString("week");
                    String forecastWind = c.getString("forecastWind");
                    String forecastWeather = c.getString("forecastWeather");
                    String forecastMaxMintemp = String.valueOf(c.getJSONObject("forecastMaxtemp").getInt("value")) + " - " + String.valueOf(c.getJSONObject("forecastMintemp").getInt("value")) + " °" + c.getJSONObject("forecastMintemp").getString("unit");
                    String forecastMaxMinrh = String.valueOf(c.getJSONObject("forecastMaxrh").getInt("value")) + " - " + String.valueOf(c.getJSONObject("forecastMinrh").getInt("value")) + "%";
                    String forecastIcon = String.valueOf(c.getInt("ForecastIcon"));
                    String psr = c.getString("PSR");
                    String enPsr = "";
                    if (ApiController.language.equals("sc") || ApiController.language.equals("tc")) {
                        switch (psr) {
                            case "低":
                                enPsr = "Low";
                                break;
                            case "中低":
                                enPsr = "Medium Low";
                                break;
                            case "中":
                                enPsr = "Medium";
                                break;
                            case "中高":
                                enPsr = "Medium High";
                                break;
                            case "高":
                                enPsr = "High";
                                break;
                            default:
                                break;
                        }
                    } else {
                        enPsr = psr;
                    }
                    ModelNineDayWeatherForecast.addWeatherForecast(formatedForecastDate, week, forecastWind, forecastWeather, forecastMaxMintemp, forecastMaxMinrh, forecastIcon, psr, enPsr);
                }
                hkoNineDayWeatherForecast.setUpdateTime(jsonObj.getString("updateTime"));
                hkoNineDayWeatherForecast.setSeaTemp(jsonObj.getJSONObject("seaTemp"));
                JSONArray soilTempList = jsonObj.getJSONArray("soilTemp");
                for (int i = 0; i < soilTempList.length(); i++) {
                    JSONObject c = soilTempList.getJSONObject(i);
                    String place = c.getString("place");
                    String value = String.valueOf(c.getInt("value"));
                    String unit = c.getString("unit");
                    String recordTime = c.getString("recordTime");
                    String depth = String.valueOf(c.getJSONObject("depth").getInt("value")) + c.getJSONObject("depth").getString("unit");
                    ModelNineDayWeatherForecast.addSoilTemp(place, value, unit, recordTime, depth);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from HKO server.");
        }
    }

}
