package com.kerry.gogobasketball.data;

public class GamingPlayer extends WaitingRoomSeats {

    private int mScore;
    private int mFoul;
    private int mRebound;
    private boolean mIsWinning;

    public GamingPlayer(){
        mScore = 0;
        mRebound = 0;
        mFoul = 0;
        mIsWinning = false;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        this.mScore = score;
    }

    public int getFoul() {
        return mFoul;
    }

    public void setFoul(int foul) {
        this.mFoul = foul;
    }

    public int getRebound() {
        return mRebound;
    }

    public void setRebound(int rebound) {
        this.mRebound = rebound;
    }

    public boolean isWinning() {
        return mIsWinning;
    }

    public void setWinning(boolean winning) {
        mIsWinning = winning;
    }

}
