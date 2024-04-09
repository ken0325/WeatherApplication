package com.example.weatherapplication;

import java.util.ArrayList;

public class ModelWeatherDisasterWarning {
    public static String code;
    public static String updateTime;
    public String fxLink;
    public static ArrayList<Warning> warning;
    public Refer refer;

    public ModelWeatherDisasterWarning() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public ArrayList<Warning> getWarning() {
        return warning;
    }

    public void setWarning(ArrayList<Warning> warning) {
        this.warning = warning;
    }

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }

    @Override
    public String toString() {
        return "ModelWeatherDisasterWarning{" +
                "code='" + code + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", fxLink='" + fxLink + '\'' +
                ", warning=" + warning +
                ", refer=" + refer +
                '}';
    }
}


class Refer{
    public ArrayList<String> sources;
    public ArrayList<String> license;

    public Refer() {
    }

    public ArrayList<String> getSources() {
        return sources;
    }

    public void setSources(ArrayList<String> sources) {
        this.sources = sources;
    }

    public ArrayList<String> getLicense() {
        return license;
    }

    public void setLicense(ArrayList<String> license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Refer{" +
                "sources=" + sources +
                ", license=" + license +
                '}';
    }
}


class Warning{
    public String id;
    public String sender;
    public String pubTime;
    public String title;
    public String startTime;
    public String endTime;
    public String status;
    public String level;
    public String severity;
    public String severityColor;
    public String type;
    public String typeName;
    public String urgency;
    public String certainty;
    public String text;
    public String related;

    public Warning() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverityColor() {
        return severityColor;
    }

    public void setSeverityColor(String severityColor) {
        this.severityColor = severityColor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getCertainty() {
        return certainty;
    }

    public void setCertainty(String certainty) {
        this.certainty = certainty;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    @Override
    public String toString() {
        return "Warning{" +
                "id='" + id + '\'' +
                ", sender='" + sender + '\'' +
                ", pubTime='" + pubTime + '\'' +
                ", title='" + title + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                ", level='" + level + '\'' +
                ", severity='" + severity + '\'' +
                ", severityColor='" + severityColor + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", urgency='" + urgency + '\'' +
                ", certainty='" + certainty + '\'' +
                ", text='" + text + '\'' +
                ", related='" + related + '\'' +
                '}';
    }
}

