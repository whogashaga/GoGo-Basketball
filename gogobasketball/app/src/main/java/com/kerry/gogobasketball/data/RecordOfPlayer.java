package com.kerry.gogobasketball.data;

public class RecordOfPlayer {


    private int mGames;
    private int mWinning;
    private int mScore;
    private int mRebound;
    private int mFoul;

    public RecordOfPlayer(){
        mGames = 0;
        mScore = 0;
        mRebound = 0;
        mFoul = 0;
    }

    public int getGames() {
        return mGames;
    }

    public void setGames(int games) {
        this.mGames = games;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        this.mScore = score;
    }

    public int getRebound() {
        return mRebound;
    }

    public void setRebound(int rebound) {
        this.mRebound = rebound;
    }

    public int getFoul() {
        return mFoul;
    }

    public void setFoul(int foul) {
        this.mFoul = foul;
    }

    public int getWinning() {
        return mWinning;
    }

    public void setWinning(int winning) {
        this.mWinning = winning;
    }
}
