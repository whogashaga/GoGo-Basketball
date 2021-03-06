package com.kerry.gogobasketball.data;

public class WaitingRoomInfo {

    private String mHostName;
    private String mRoomName;
    private String mCourtLocation;
    private boolean mJustice;
    private int mPlayers;
    private int mReferee;
    private String mStatus;

    public WaitingRoomInfo() {
        mRoomName = "";
        mCourtLocation = "";
        mJustice = true;
        mPlayers = 0;
        mReferee = 0;
        mHostName = "";
        mStatus = "waiting";
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

    public String getCourtLocation() {
        return mCourtLocation;
    }

    public void setCourtLocation(String courtLocation) {
        this.mCourtLocation = courtLocation;
    }

    public boolean getJustice() {
        return mJustice;
    }

    public void setJustice(boolean needReferee) {
        this.mJustice = needReferee;
    }

    public int getPlayerAmount() {
        return mPlayers;
    }

    public void setPlayerAmount(int players) {
        this.mPlayers = players;
    }

    public int getRefereeAmount() {
        return mReferee;
    }

    public void setRefereeAmount(int referee) {
        this.mReferee = referee;
    }

    public int getTotalPlayerAmount() {
        return mPlayers + mReferee;
    }
}
