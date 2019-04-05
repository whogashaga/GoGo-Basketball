package com.kerry.gogobasketball.data;

public class WaitingRoomInfo {

    private String mRoomName;
    private String mCourtLoaction;
    private String mRefereeOnOff;

    public WaitingRoomInfo(){}

    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String roomName) {
        this.mRoomName = roomName;
    }

    public String getCourtLoaction() {
        return mCourtLoaction;
    }

    public void setCourtLoaction(String courtLoaction) {
        this.mCourtLoaction = courtLoaction;
    }

    public String getRefereeOnOff() {
        return mRefereeOnOff;
    }

    public void setRefereeOnOff(String refereeOnOff) {
        this.mRefereeOnOff = refereeOnOff;
    }
}
