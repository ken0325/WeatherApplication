package com.example.weatherapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class ApiWeatherDisasterWarning extends Thread {
    private static final String TAG = "ApiWeatherDisasterWarning";
    private static final String defaultLat = "22.3529682", defaultLong = "113.962891";

    public static final ModelWeatherDisasterWarning weatherDisasterWarning = new ModelWeatherDisasterWarning();

    public void run() {
        String dayStr = makeRequest();
        Log.e(TAG, "Response from url: " + dayStr);
        if (dayStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(dayStr);
                weatherDisasterWarning.setCode(jsonObj.getString("code"));
                weatherDisasterWarning.setUpdateTime(jsonObj.getString("updateTime"));
                weatherDisasterWarning.setFxLink(jsonObj.getString("fxLink"));
                JSONArray warning = jsonObj.getJSONArray("warning");
                ArrayList<Warning> warnings = new ArrayList<>();
                for (int i = 0; i < warning.length(); i++) {
                    JSONObject c = warning.getJSONObject(i);
                    Warning warning1 = new Warning();
                    warning1.setId(c.getString("id"));
                    warning1.setSender(c.getString("sender"));
                    warning1.setPubTime(c.getString("pubTime"));
                    warning1.setTitle(c.getString("title"));
                    warning1.setStartTime(c.getString("startTime"));
                    warning1.setEndTime(c.getString("status"));
                    warning1.setLevel(c.getString("level"));
                    warning1.setSeverity(c.getString("severity"));
                    warning1.setSeverityColor(c.getString("severityColor"));
                    warning1.setType(c.getString("type"));
                    warning1.setTypeName(c.getString("typeName"));
                    warning1.setUrgency(c.getString("urgency"));
                    warning1.setCertainty(c.getString("certainty"));
                    warning1.setText(c.getString("text"));
                    warning1.setRelated(c.getString("related"));
                    warnings.add(warning1);
                }
                weatherDisasterWarning.setWarning(warnings);
                JSONObject refer = jsonObj.getJSONObject("refer");
                JSONArray sources = refer.getJSONArray("sources");
                JSONArray license = refer.getJSONArray("license");
                Refer refer1 = new Refer();
                ArrayList<String> s = new ArrayList<>();
                for (int i = 0; i < sources.length(); i++) {
                    s.add(sources.getString(i));
                }
                ArrayList<String> l = new ArrayList<>();
                for (int i = 0; i < license.length(); i++) {
                    l.add(license.getString(i));
                }
                refer1.setSources(s);
                refer1.setLicense(l);
                weatherDisasterWarning.setRefer(refer1);
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from.");
        }
    }

    public static String makeRequest() {
        String response = null;
        String jsonUrl;

        String currentLat = MainActivity.lat;
        String currentLong = MainActivity.lon;
        String apiKey = "06043441d8b844f2a99b63cf61c67109";
        String lang = "zh";
        if (currentLat == null) {
            jsonUrl = "https://devapi.qweather.com/v7/warning/now?location=" + defaultLong + ",=" + defaultLat + "&lang=" + lang + "&key=" + apiKey;
        } else {
            jsonUrl = "https://devapi.qweather.com/v7/warning/now?location=" + currentLong + "," + currentLat + "&lang=" + lang + "&key=" + apiKey;
        }

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
