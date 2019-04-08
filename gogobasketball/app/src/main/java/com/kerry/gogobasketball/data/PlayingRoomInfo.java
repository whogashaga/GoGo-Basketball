package com.kerry.gogobasketball.data;

import java.util.ArrayList;

public class PlayingRoomInfo {

    private String mLocation;
    private String mRoomName;
    private ArrayList<PlayingDerGamers> mPlayingDerGamersList;

    public PlayingRoomInfo() {
        mLocation = "";
        mRoomName = "";
        mPlayingDerGamersList = new ArrayList<>();
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String roomName) {
        this.mRoomName = roomName;
    }

    public ArrayList<PlayingDerGamers> getPlayingDerGamersList() {
        return mPlayingDerGamersList;
    }

    public void setPlayingDerGamersList(ArrayList<PlayingDerGamers> playingDerGamersList) {
        this.mPlayingDerGamersList = playingDerGamersList;
    }

}
