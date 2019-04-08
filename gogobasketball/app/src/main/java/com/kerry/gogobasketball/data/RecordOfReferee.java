package com.kerry.gogobasketball.data;

public class RecordOfReferee {

    private int mJustices;
    private int mRating;

    public RecordOfReferee(){
        mJustices = 0;
        mRating = 0;
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
