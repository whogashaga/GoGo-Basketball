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

    public void setLat(double mLat) {
        this.mLat = mLat;
    }

    public double getLong() {
        return mLong;
    }

    public void setLong(double mLong) {
        this.mLong = mLong;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public double getLatMin() {
        return mLatMin;
    }

    public void setLatMin(double mLatMin) {
        this.mLatMin = mLatMin;
    }

    public double getLongMin() {
        return mLongMin;
    }

    public void setLongMin(double mLongMin) {
        this.mLongMin = mLongMin;
    }

    public double getLatMax() {
        return mLatMax;
    }

    public void setLatMax(double mLatMax) {
        this.mLatMax = mLatMax;
    }

    public double getLongMax() {
        return mLongMax;
    }

    public void setLongMax(double mLongMax) {
        this.mLongMax = mLongMax;
    }

    public int getPopulation() {
        return mPopulation;
    }

    public void setPopulation(int mPopulation) {
        this.mPopulation = mPopulation;
    }
}
