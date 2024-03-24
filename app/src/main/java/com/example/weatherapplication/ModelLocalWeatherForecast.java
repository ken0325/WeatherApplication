package com.example.weatherapplication;

public class ModelLocalWeatherForecast {
    private String generalSituation;
    private String tcInfo;
    private String fireDangerWarning;
    private String forecastPeriod;
    private String forecastDesc;
    private String outlook;
    private String updateTime;

    public ModelLocalWeatherForecast() {
    }

    public String getGeneralSituation() {
        return generalSituation;
    }

    public void setGeneralSituation(String generalSituation) {
        this.generalSituation = generalSituation;
    }

    public String getTcInfo() {
        return tcInfo;
    }

    public void setTcInfo(String tcInfo) {
        this.tcInfo = tcInfo;
    }

    public String getFireDangerWarning() {
        return fireDangerWarning;
    }

    public void setFireDangerWarning(String fireDangerWarning) {
        this.fireDangerWarning = fireDangerWarning;
    }

    public String getForecastPeriod() {
        return forecastPeriod;
    }

    public void setForecastPeriod(String forecastPeriod) {
        this.forecastPeriod = forecastPeriod;
    }

    public String getForecastDesc() {
        return forecastDesc;
    }

    public void setForecastDesc(String forecastDesc) {
        this.forecastDesc = forecastDesc;
    }

    public String getOutlook() {
        return outlook;
    }

    public void setOutlook(String outlook) {
        this.outlook = outlook;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ModelLocalWeatherForecast{" +
                "generalSituation='" + generalSituation + '\'' +
                ", tcInfo='" + tcInfo + '\'' +
                ", fireDangerWarning='" + fireDangerWarning + '\'' +
                ", forecastPeriod='" + forecastPeriod + '\'' +
                ", forecastDesc='" + forecastDesc + '\'' +
                ", outlook='" + outlook + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
