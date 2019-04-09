package com.kerry.gogobasketball.data;

import java.util.ArrayList;

public class WaitingRoomInfo {

    private String mHostName;
    private String mRoomName;
    private String mCourtLocation;
    private boolean mJustice;
    private int mPlayers;
    private int mReferee;
    private ArrayList<WaitingRoomSeats> mWaitingDerPlayersList;

    public WaitingRoomInfo() {
        mWaitingDerPlayersList = new ArrayList<>();
        mRoomName = "";
        mCourtLocation = "";
        mJustice = true;
        mPlayers = 0;
        mReferee = 0;
        mHostName = "";
    }

    public String getHostName() {
        return mHostName;
    }

    public void setHostName(String mHostName) {
        this.mHostName = mHostName;
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

    public ArrayList<WaitingRoomSeats> getWaitingPlayersList() {
        return mWaitingDerPlayersList;
    }

    public void setWaitingPlayersList(ArrayList<WaitingRoomSeats> waitingDerPlayersList) {
        this.mWaitingDerPlayersList = waitingDerPlayersList;
    }

    public int getTotalPlayerAmount() {
        return mPlayers + mReferee;
    }
}
