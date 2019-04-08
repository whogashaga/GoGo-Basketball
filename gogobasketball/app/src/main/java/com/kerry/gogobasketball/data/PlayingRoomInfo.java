package com.kerry.gogobasketball.data;

public class PlayingRoomInfo {

    private String mId;
    private String mGender;

    public PlayingRoomInfo() {
    }

    public PlayingRoomInfo(String id, String gender) {
        mId = id;
        mGender = gender;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }
}
