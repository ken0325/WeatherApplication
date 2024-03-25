package com.example.weatherapplication;

import java.util.ArrayList;

public class ModelCurrentWeatherReport {
    public Rainfall rainfall;
    public String warningMessage;
    public static ArrayList<Integer> icon;
    public String iconUpdateTime;
    public static Uvindex uvindex;
    public String updateTime;
    public static ArrayList<Temperature> temperature;
    public String tcmessage;
    public String mintempFrom00To09;
    public String rainfallFrom00To12;
    public String rainfallLastMonth;
    public String rainfallJanuaryToLastMonth;
    public static Humidity humidity;

    public ModelCurrentWeatherReport() {
    }

    public Rainfall getRainfall() {
        return rainfall;
    }

    public void setRainfall(Rainfall rainfall) {
        this.rainfall = rainfall;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public ArrayList<Integer> getIcon() {
        return icon;
    }

    public void setIcon(ArrayList<Integer> icon) {
        this.icon = icon;
    }

    public String getIconUpdateTime() {
        return iconUpdateTime;
    }

    public void setIconUpdateTime(String iconUpdateTime) {
        this.iconUpdateTime = iconUpdateTime;
    }

    public Uvindex getUvindex() {
        return uvindex;
    }

    public void setUvindex(Uvindex uvindex) {
        this.uvindex = uvindex;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public ArrayList<Temperature> getTemperature() {
        return temperature;
    }

    public void setTemperature(ArrayList temperature) {
        this.temperature = temperature;
    }

    public String getTcmessage() {
        return tcmessage;
    }

    public void setTcmessage(String tcmessage) {
        this.tcmessage = tcmessage;
    }

    public String getMintempFrom00To09() {
        return mintempFrom00To09;
    }

    public void setMintempFrom00To09(String mintempFrom00To09) {
        this.mintempFrom00To09 = mintempFrom00To09;
    }

    public String getRainfallFrom00To12() {
        return rainfallFrom00To12;
    }

    public void setRainfallFrom00To12(String rainfallFrom00To12) {
        this.rainfallFrom00To12 = rainfallFrom00To12;
    }

    public String getRainfallLastMonth() {
        return rainfallLastMonth;
    }

    public void setRainfallLastMonth(String rainfallLastMonth) {
        this.rainfallLastMonth = rainfallLastMonth;
    }

    public String getRainfallJanuaryToLastMonth() {
        return rainfallJanuaryToLastMonth;
    }

    public void setRainfallJanuaryToLastMonth(String rainfallJanuaryToLastMonth) {
        this.rainfallJanuaryToLastMonth = rainfallJanuaryToLastMonth;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "ModelCurrentWeatherReport{" +
                "rainfall=" + rainfall +
                ", warningMessage='" + warningMessage + '\'' +
                ", icon=" + icon +
                ", iconUpdateTime=" + iconUpdateTime +
                ", uvindex=" + uvindex +
                ", updateTime=" + updateTime +
                ", temperature=" + temperature +
                ", tcmessage='" + tcmessage + '\'' +
                ", mintempFrom00To09='" + mintempFrom00To09 + '\'' +
                ", rainfallFrom00To12='" + rainfallFrom00To12 + '\'' +
                ", rainfallLastMonth='" + rainfallLastMonth + '\'' +
                ", rainfallJanuaryToLastMonth='" + rainfallJanuaryToLastMonth + '\'' +
                ", humidity=" + humidity +
                '}';
    }
}

class Rainfall {
    public ArrayList<Datum> data;
    public String startTime;
    public String endTime;

    public Rainfall() {
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Rainfall{" +
                "data=" + data +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

class Uvindex {
    public ArrayList<Datum> data;
    public String recordDesc;

    public Uvindex() {
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public String getRecordDesc() {
        return recordDesc;
    }

    public void setRecordDesc(String recordDesc) {
        this.recordDesc = recordDesc;
    }

    @Override
    public String toString() {
        return "Uvindex{" +
                "data=" + data +
                ", recordDesc='" + recordDesc + '\'' +
                '}';
    }
}

class Temperature {
    public ArrayList<Datum> data;
    public String recordTime;

    public Temperature() {
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "data=" + data +
                ", recordTime=" + recordTime +
                '}';
    }
}

class Humidity {
    public String recordTime;
    public ArrayList<Datum> data;

    public Humidity() {
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "recordTime=" + recordTime +
                ", data=" + data +
                '}';
    }
}

class Datum {
    public String unit;
    public String place;
    public int max;
    public String main;
    public double value;
    public String desc;

    public Datum() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "unit='" + unit + '\'' +
                ", place='" + place + '\'' +
                ", max=" + max +
                ", main='" + main + '\'' +
                ", value=" + value +
                ", desc='" + desc + '\'' +
                '}';
    }
}