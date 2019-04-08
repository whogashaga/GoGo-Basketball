package com.kerry.gogobasketball.data;

import java.util.ArrayList;

public class WaitingRoomInfo {

    private String mRoomName;
    private String mCourtLocation;
    private String mRefereeOnOff;
    private int mPlayers;
    private int mReferee;
    private ArrayList<WaitingRoomSeats> mWaitingDerPlayersList;

    public WaitingRoomInfo() {
        mWaitingDerPlayersList = new ArrayList<>();
        mRoomName = "";
        mCourtLocation = "";
        mRefereeOnOff = "";
        mPlayers = 0;
        mReferee = 0;
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

    public String getRefereeOnOff() {
        return mRefereeOnOff;
    }

    public void setRefereeOnOff(String refereeOnOff) {
        this.mRefereeOnOff = refereeOnOff;
    }

    public int getPlayers() {
        return mPlayers;
    }

    public void setPlayers(int players) {
        this.mPlayers = players;
    }

    public int getReferee() {
        return mReferee;
    }

    public void setReferee(int referee) {
        this.mReferee = referee;
    }

    public ArrayList<WaitingRoomSeats> getWaitingPlayersList() {
        return mWaitingDerPlayersList;
    }

    public void setWaitingPlayersList(ArrayList<WaitingRoomSeats> waitingDerPlayersList) {
        this.mWaitingDerPlayersList = waitingDerPlayersList;
    }
}
