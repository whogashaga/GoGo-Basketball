package com.kerry.gogobasketball.data;

public class CourtsInfo {

    private String mLocation;
    private double mLatMin;
    private double mLongMin;
    private double mLatMax;
    private double mLongMax;
    private int mPopulation;

    public CourtsInfo() {
        mLocation = "";
        mLatMin = 0;
        mLongMin = 0;
        mPopulation = 0;
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
