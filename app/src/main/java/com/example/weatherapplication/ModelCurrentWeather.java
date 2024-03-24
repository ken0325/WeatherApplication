package com.example.weatherapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelCurrentWeather {
    private Double latitude;
    private Double longitude;
    private Double generationtime_ms;
    private Integer utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private Integer elevation;
    private JSONObject current_units;
    private JSONObject current;
    private JSONObject daily_units;
    private JSONObject daily;

    public ModelCurrentWeather() {

    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getGenerationtime_ms() {
        return generationtime_ms;
    }

    public void setGenerationtime_ms(Double generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }

    public Integer getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public void setUtc_offset_seconds(Integer utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public JSONObject getCurrent_units() {
        return current_units;
    }

    public void setCurrent_units(JSONObject current_units) {
        this.current_units = current_units;
    }

    public JSONObject getCurrent() {
        return current;
    }

    public void setCurrent(JSONObject current) {
        this.current = current;
    }

    public JSONObject getDaily_units() {
        return daily_units;
    }

    public void setDaily_units(JSONObject daily_units) {
        this.daily_units = daily_units;
    }

    public JSONObject getDaily() {
        return daily;
    }

    public void setDaily(JSONObject daily) {
        this.daily = daily;
    }

    @Override
    public String toString() {
        return "ModleCurrentWeather{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", generationtime_ms=" + generationtime_ms +
                ", utc_offset_seconds=" + utc_offset_seconds +
                ", timezone='" + timezone + '\'' +
                ", timezone_abbreviation='" + timezone_abbreviation + '\'' +
                ", elevation=" + elevation +
                ", current_units=" + current_units +
                ", current=" + current +
                ", daily_units=" + daily_units +
                ", daily=" + daily +
                '}';
    }

//    public String returnCurrentTemp() throws JSONException {
//        Double currentTemp = current.getDouble("temperature_2m");
//        return currentTemp.toString();
//    }
//
//    public String returnCurrentTempUnits() throws JSONException {
//        String currentTempUnits = current.getString("temperature_2m");
//        return currentTempUnits;
//    }
}
