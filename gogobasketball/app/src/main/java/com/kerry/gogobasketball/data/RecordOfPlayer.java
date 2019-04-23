package com.kerry.gogobasketball.data;

public class RecordOfPlayer {

    private int mGames;
    private int mWinning;
    private int mScore;
    private int mRebound;
    private int mFoul;
    private float mAvWinRate;
    private float mAvScore;
    private float mAvRebound;
    private float mAvFoul;


    public RecordOfPlayer() {
        mGames = 0;
        mScore = 0;
        mRebound = 0;
        mFoul = 0;
        mAvWinRate = 0;
        mAvScore = 0;
        mAvRebound = 0;
        mAvFoul = 0;
    }

    public float getAvWinRate() {
        return mAvWinRate;
    }

    public void setAvWinRate(float mAvWinRate) {
        this.mAvWinRate = mAvWinRate;
    }

    public float getAvScore() {
        return mAvScore;
    }

    public void setAvScore(float mAvScore) {
        this.mAvScore = mAvScore;
    }

    public float getAvRebound() {
        return mAvRebound;
    }

    public void setAvRebound(float mAvRebound) {
        this.mAvRebound = mAvRebound;
    }

    public float getAvFoul() {
        return mAvFoul;
    }

    public void setAvFoul(float mAvFoul) {
        this.mAvFoul = mAvFoul;
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
