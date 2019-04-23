package com.kerry.gogobasketball.data;

public class RecordOfReferee {

    private int mJustices;
    private int mRating;
    private int mPopulation;
    private float mCommenter;

    public RecordOfReferee() {
        mJustices = 0;
        mRating = 0;
        mCommenter = 0;
        mPopulation = 0;
    }

    public int getCommenter() {
        return mPopulation;
    }

    public void setCommenter(int mPopulation) {
        this.mPopulation = mPopulation;
    }

    public float getAvRating() {
        return mCommenter;
    }

    public void setAvRating(float mAvRating) {
        this.mCommenter = mAvRating;
    }

    public int getJustices() {
        return mJustices;
    }

    public void setJustices(int justices) {
        this.mJustices = justices;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        this.mRating = rating;
    }
}
