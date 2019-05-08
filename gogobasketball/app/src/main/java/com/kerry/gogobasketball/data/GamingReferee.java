package com.kerry.gogobasketball.data;

public class GamingReferee extends WaitingRoomSeats {

    private int mJustices;
    private int mRating;

    public GamingReferee() {
        mJustices = 0;
        mRating = 0;
    }

    public int getJustcies() {
        return mJustices;
    }

    public void setJustcies(int justcies) {
        this.mJustices = justcies;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        this.mRating = rating;
    }
}
