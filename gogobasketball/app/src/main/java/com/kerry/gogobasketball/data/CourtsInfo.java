package com.kerry.gogobasketball.data;

public class CourtsInfo {

    private String mLocation;
    private double mLat;
    private double mLatMin;
    private double mLong;
    private double mLongMin;
    private double mLatMax;
    private double mLongMax;
    private int mPopulation;

    public CourtsInfo() {
        mLocation = "";
        mLat = 0;
        mLatMin = 0;
        mLatMax = 0;
        mLong = 0;
        mLongMin = 0;
        mLongMax = 0;
        mPopulation = 0;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        this.mLat = lat;
    }

    public double getLong() {
        return mLong;
    }

    public void setLong(double lng) {
        this.mLong = lng;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public double getLatMin() {
        return mLatMin;
    }

    public void setLatMin(double latMin) {
        this.mLatMin = latMin;
    }

    public double getLongMin() {
        return mLongMin;
    }

    public void setLongMin(double longMin) {
        this.mLongMin = longMin;
    }

    public double getLatMax() {
        return mLatMax;
    }

    public void setLatMax(double latMax) {
        this.mLatMax = latMax;
    }

    public double getLongMax() {
        return mLongMax;
    }

    public void setLongMax(double longMax) {
        this.mLongMax = longMax;
    }

    public int getPopulation() {
        return mPopulation;
    }

    public void setPopulation(int population) {
        this.mPopulation = population;
    }
}
