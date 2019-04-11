package com.kerry.gogobasketball.data;

import java.util.ArrayList;

public class GamingRoomInfo {

    private String mLocation;
    private String mRoomName;

    public GamingRoomInfo() {
        mLocation = "";
        mRoomName = "";
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

}
