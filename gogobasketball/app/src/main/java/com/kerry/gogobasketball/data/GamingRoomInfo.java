package com.kerry.gogobasketball.data;

public class GamingRoomInfo {

    private String mHostName;
    private String mRoomName;
    private String mStatus;
    private String mWinner;
    private GamingPlayer mPlayer1;
    private GamingPlayer mPlayer2;
    private GamingPlayer mPlayer3;
    private GamingPlayer mPlayer4;
    private GamingPlayer mPlayer5;
    private GamingPlayer mPlayer6;
    private GamingReferee mReferee;

    public GamingRoomInfo() {
        mHostName = "";
        mRoomName = "";
        mStatus = "";
        mWinner = "";
        mPlayer1 = new GamingPlayer();
        mPlayer2 = new GamingPlayer();
        mPlayer3 = new GamingPlayer();
        mPlayer4 = new GamingPlayer();
        mPlayer5 = new GamingPlayer();
        mPlayer6 = new GamingPlayer();
        mReferee = new GamingReferee();
    }

    public String getWinner() {
        return mWinner;
    }

    public void setWinner(String winner) {
        this.mWinner = winner;
    }

    public int getScoreA() {
        return mPlayer1.getScore() + mPlayer2.getScore() + mPlayer3.getScore();
    }

    public int getScoreB() {
        return mPlayer4.getScore() + mPlayer5.getScore() + mPlayer6.getScore();
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }

    public String getHostName() {
        return mHostName;
    }

    public void setHostName(String hostName) {
        this.mHostName = hostName;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String roomName) {
        this.mRoomName = roomName;
    }

    public GamingPlayer getPlayer1() {
        return mPlayer1;
    }

    public void setPlayer1(GamingPlayer player1) {
        this.mPlayer1 = player1;
    }

    public GamingPlayer getPlayer2() {
        return mPlayer2;
    }

    public void setPlayer2(GamingPlayer mPlayer2) {
        this.mPlayer2 = mPlayer2;
    }

    public GamingPlayer getPlayer3() {
        return mPlayer3;
    }

    public void setPlayer3(GamingPlayer mPlayer3) {
        this.mPlayer3 = mPlayer3;
    }

    public GamingPlayer getPlayer4() {
        return mPlayer4;
    }

    public void setPlayer4(GamingPlayer mPlayer4) {
        this.mPlayer4 = mPlayer4;
    }

    public GamingPlayer getPlayer5() {
        return mPlayer5;
    }

    public void setPlayer5(GamingPlayer mPlayer5) {
        this.mPlayer5 = mPlayer5;
    }

    public GamingPlayer getPlayer6() {
        return mPlayer6;
    }

    public void setPlayer6(GamingPlayer mPlayer6) {
        this.mPlayer6 = mPlayer6;
    }

    public GamingReferee getReferee() {
        return mReferee;
    }

    public void setReferee(GamingReferee referee) {
        this.mReferee = referee;
    }
}
