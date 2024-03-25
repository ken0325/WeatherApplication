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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class ApiCurrentWeatherReport extends Thread {
    private static final String TAG = "ApiCurrentWeatherReport";
    public static final ModelCurrentWeatherReport currentWeatherReport = new ModelCurrentWeatherReport();
    public static final ModelHourlyRainfall hourlyRainfall = new ModelHourlyRainfall();

    public static String makeRequest() {
        String response = null;
        String jsonUrl = "https://data.weather.gov.hk/weatherAPI/opendata/weather.php?dataType=rhrread&lang=" + ApiController.language;

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

    public static String makeRequest2() {
        String response = null;
        String jsonUrl = "https://data.weather.gov.hk/weatherAPI/opendata/hourlyRainfall.php?lang=" + ApiController.language;

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
                Rainfall rainfall = new Rainfall();
                ArrayList<Datum> rainfallDatumList = new ArrayList<>();
                JSONArray rainfallDataList = jsonObj.getJSONObject("rainfall").getJSONArray("data");
                if (rainfallDataList != null) {
                    for (int i = 0; i < rainfallDataList.length(); i++) {
                        Datum rainfallDatum = new Datum();
                        rainfallDatum.setUnit(rainfallDataList.getJSONObject(i).getString("unit"));
                        rainfallDatum.setPlace(rainfallDataList.getJSONObject(i).getString("place"));
                        rainfallDatum.setMax(rainfallDataList.getJSONObject(i).getInt("max"));
                        rainfallDatum.setMain(rainfallDataList.getJSONObject(i).getString("main"));
                        rainfallDatumList.add(rainfallDatum);
                    }
                }
                rainfall.setData(rainfallDatumList);
                rainfall.setStartTime(jsonObj.getJSONObject("rainfall").getString("startTime"));
                rainfall.setEndTime(jsonObj.getJSONObject("rainfall").getString("endTime"));
                currentWeatherReport.setRainfall(rainfall);
                currentWeatherReport.setWarningMessage(jsonObj.getString("warningMessage"));
                JSONArray icon = jsonObj.getJSONArray("icon");
                ArrayList<Integer> s = new ArrayList<>();
                if (icon != null) {
                    for (int i = 0; i < icon.length(); i++) {
                        Integer rec = icon.getInt(i);
                        s.add(rec);
                    }
                }
                currentWeatherReport.setIcon(s);
                currentWeatherReport.setIconUpdateTime(jsonObj.getString("iconUpdateTime"));
                if (jsonObj.get("uvindex").toString().equals("")) {
                    currentWeatherReport.setUvindex(null);
                } else {
                    Uvindex uvindex = new Uvindex();
                    JSONArray uvindexDataList = jsonObj.getJSONObject("uvindex").getJSONArray("data");
                    ArrayList<Datum> uvindexDatumList = new ArrayList<>();
                    if (uvindexDataList != null) {
                        for (int i = 0; i < uvindexDataList.length(); i++) {
                            Datum uvindexDatum = new Datum();
                            uvindexDatum.setPlace(uvindexDataList.getJSONObject(i).getString("place"));
                            uvindexDatum.setValue(uvindexDataList.getJSONObject(i).getDouble("value"));
                            uvindexDatum.setDesc(uvindexDataList.getJSONObject(i).getString("desc"));
                            uvindexDatumList.add(uvindexDatum);
                        }
                    }
                    uvindex.setData(uvindexDatumList);
                    uvindex.setRecordDesc(jsonObj.getJSONObject("uvindex").getString("recordDesc"));
                    currentWeatherReport.setUvindex(uvindex);
                }
                currentWeatherReport.setUpdateTime(jsonObj.getString("updateTime"));
                ArrayList<Temperature> temperatureList = new ArrayList<>();
                Temperature temperature = new Temperature();
                JSONArray temperatureDataList = jsonObj.getJSONObject("temperature").getJSONArray("data");
                ArrayList<Datum> temperatureDatumList = new ArrayList<>();
                if (temperatureDataList != null) {
                    for (int i = 0; i < temperatureDataList.length(); i++) {
                        Datum temperatureDatum = new Datum();
                        temperatureDatum.setPlace(temperatureDataList.getJSONObject(i).getString("place"));
                        temperatureDatum.setValue(temperatureDataList.getJSONObject(i).getInt("value"));
                        temperatureDatum.setUnit(temperatureDataList.getJSONObject(i).getString("unit"));
                        temperatureDatumList.add(temperatureDatum);
                    }
                }
                temperature.setData(temperatureDatumList);
                temperature.setRecordTime(jsonObj.getJSONObject("temperature").getString("recordTime"));
                temperatureList.add(temperature);
                currentWeatherReport.setTemperature(temperatureList);
                currentWeatherReport.setTcmessage(jsonObj.getString("tcmessage"));
                currentWeatherReport.setMintempFrom00To09(jsonObj.getString("mintempFrom00To09"));
                currentWeatherReport.setRainfallFrom00To12(jsonObj.getString("rainfallFrom00To12"));
                currentWeatherReport.setRainfallLastMonth(jsonObj.getString("rainfallLastMonth"));
                currentWeatherReport.setRainfallLastMonth(jsonObj.getString("rainfallJanuaryToLastMonth"));
                Humidity humidity = new Humidity();
                JSONArray humidityDataList = jsonObj.getJSONObject("humidity").getJSONArray("data");
                ArrayList<Datum> humidityDatumList = new ArrayList<>();
                if (humidityDataList != null) {
                    for (int i = 0; i < humidityDataList.length(); i++) {
                        Datum humidityDatum = new Datum();
                        humidityDatum.setUnit(humidityDataList.getJSONObject(i).getString("unit"));
                        humidityDatum.setPlace(humidityDataList.getJSONObject(i).getString("place"));
                        humidityDatum.setValue(humidityDataList.getJSONObject(i).getInt("value"));
                        humidityDatumList.add(humidityDatum);
                    }
                }
                humidity.setData(humidityDatumList);
                humidity.setRecordTime(jsonObj.getJSONObject("humidity").getString("recordTime"));
                currentWeatherReport.setHumidity(humidity);
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from HKO server.");
        }

        String dayStr2 = makeRequest2();
        Log.e(TAG, "Response from url: " + dayStr2);
        if (dayStr2 != null) {
            try {
                JSONObject jsonObj = new JSONObject(dayStr2);
                ArrayList<HourlyRainfall> hourlyRainfallList = new ArrayList<>();
                JSONArray hourlyRainfallDataList = jsonObj.getJSONArray("hourlyRainfall");
                if (hourlyRainfallDataList != null) {
                    for (int i = 0; i < hourlyRainfallDataList.length(); i++) {
                        HourlyRainfall hourlyRainfall1 = new HourlyRainfall();
                        hourlyRainfall1.setAutomaticWeatherStation(hourlyRainfallDataList.getJSONObject(i).getString("automaticWeatherStation"));
                        hourlyRainfall1.setAutomaticWeatherStationID(hourlyRainfallDataList.getJSONObject(i).getString("automaticWeatherStationID"));
                        hourlyRainfall1.setValue(hourlyRainfallDataList.getJSONObject(i).getString("value"));
                        hourlyRainfall1.setUnit(hourlyRainfallDataList.getJSONObject(i).getString("unit"));
                        hourlyRainfallList.add(hourlyRainfall1);
                    }
                }
                hourlyRainfall.setHourlyRainfall(hourlyRainfallList);
                hourlyRainfall.setObsTime(jsonObj.getString("obsTime"));
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from HKO server.");
        }
    }
}
