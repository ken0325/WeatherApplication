package com.example.weatherapplication;

public class ModelChineseDayInfo {
    private String LunarYear;
    private String LunarDate;

    public ModelChineseDayInfo() {

    }

    public String getLunarYear() {
        return LunarYear;
    }

    public void setLunarYear(String lunarYear) {
        LunarYear = lunarYear;
    }

    public String getLunarDate() {
        return LunarDate;
    }

    public void setLunarDate(String lunarDate) {
        LunarDate = lunarDate;
    }

    @Override
    public String toString() {
        return "ChineseDayInfo{" +
                "LunarYear='" + LunarYear + '\'' +
                ", LunarDate='" + LunarDate + '\'' +
                '}';
    }
}
