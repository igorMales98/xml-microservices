package com.xml.dto;

public class CoordinatesDto {
    private float lat;
    private float lng;
    String androidToken;
    String seconds;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getAndroidToken() {
        return androidToken;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }
}
