package com.example.weatherapplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelNineDayWeatherForecast {
    private String generalSituation;
    public static ArrayList<HashMap<String, String>> weatherForecastList = new ArrayList<>();
    public static String FORECASTDATE = "forecastDate";
    public static String WEEK = "week";
    public static String FORECASTWIND = "forecastWind";
    public static String FORECASTWEATHER = "forecastWeather";
    public static String FORECASTMAXMINTEMP = "forecastMaxMintemp";
    public static String FORECASTMAXMINRH = "forecastMaxMinrh";
    public static String FORECASTICON = "forecastIcon";
    public static String PSR = "psr";
    public static String ENPSR = "enPsr";
    private JSONObject seaTemp;
    private String updateTime;
    public static ArrayList<HashMap<String, String>> soilTempList = new ArrayList<>();
    public static String PLACE = "place";
    public static String VALUE = "value";
    public static String UNIT = "unit";
    public static String RECORDTIME = "recordTime";
    public static String DEPTH = "depth";

    public static void addWeatherForecast(String forecastDate, String week, String forecastWind, String forecastWeather, String forecastMaxMintemp, String forecastMaxMinrh, String forecastIcon, String psr, String enPsr) {
        HashMap<String, String> weather = new HashMap<>();
        weather.put(FORECASTDATE, forecastDate);
        weather.put(WEEK, week);
        weather.put(FORECASTWIND, forecastWind);
        weather.put(FORECASTWEATHER, forecastWeather);
        weather.put(FORECASTMAXMINTEMP, forecastMaxMintemp);
        weather.put(FORECASTMAXMINRH, forecastMaxMinrh);
        weather.put(FORECASTICON, forecastIcon);
        weather.put(PSR, psr);
        weather.put(ENPSR, enPsr);

        weatherForecastList.add(weather);
    }

    public static void addSoilTemp(String place, String value, String unit, String recordTime, String depth) {
        HashMap<String, String> soil = new HashMap<>();
        soil.put(PLACE, place);
        soil.put(VALUE, value);
        soil.put(UNIT, unit);
        soil.put(RECORDTIME, recordTime);
        soil.put(DEPTH, depth);

        soilTempList.add(soil);
    }


    public String getGeneralSituation() {
        return generalSituation;
    }

    public void setGeneralSituation(String generalSituation) {
        this.generalSituation = generalSituation;
    }

    public ArrayList getWeatherForecastList() {
        return weatherForecastList;
    }

    public void setWeatherForecastList(ArrayList weatherForecastList) {
        this.weatherForecastList = weatherForecastList;
    }

    public JSONObject getSeaTemp() {
        return seaTemp;
    }

    public void setSeaTemp(JSONObject seaTemp) {
        this.seaTemp = seaTemp;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

//    public ArrayList getSoilTempList() {
//        return soilTempList;
//    }
//
//    public void setSoilTempList(ArrayList soilTempList) {
//        this.soilTempList = soilTempList;
//    }
}
