package com.kerry.gogobasketball.data;

public class WaitingRoomInfo {

    private String mRoomName;
    private String mCourtLocation;
    private String mRefereeOnOff;

    public WaitingRoomInfo(){}

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

}
