package com.example.weatherapplication;


import java.util.ArrayList;

public class ModelHourlyRainfall {
    public String obsTime;
    public static ArrayList<HourlyRainfall> hourlyRainfall;

    public ModelHourlyRainfall() {
    }

    public String getObsTime() {
        return obsTime;
    }

    public void setObsTime(String obsTime) {
        this.obsTime = obsTime;
    }

    public static ArrayList<HourlyRainfall> getHourlyRainfall() {
        return hourlyRainfall;
    }

    public void setHourlyRainfall(ArrayList<HourlyRainfall> hourlyRainfall) {
        this.hourlyRainfall = hourlyRainfall;
    }

    @Override
    public String toString() {
        return "ModelHourlyRainfall{" +
                "obsTime='" + obsTime + '\'' +
                ", hourlyRainfall=" + hourlyRainfall +
                '}';
    }
}

class HourlyRainfall {
    public String automaticWeatherStation;
    public String automaticWeatherStationID;
    public String value;
    public String unit;

    public HourlyRainfall() {
    }

    public String getAutomaticWeatherStation() {
        return automaticWeatherStation;
    }

    public void setAutomaticWeatherStation(String automaticWeatherStation) {
        this.automaticWeatherStation = automaticWeatherStation;
    }

    public String getAutomaticWeatherStationID() {
        return automaticWeatherStationID;
    }

    public void setAutomaticWeatherStationID(String automaticWeatherStationID) {
        this.automaticWeatherStationID = automaticWeatherStationID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "HourlyRainfall{" +
                "automaticWeatherStation='" + automaticWeatherStation + '\'' +
                ", automaticWeatherStationID='" + automaticWeatherStationID + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}