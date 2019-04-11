package com.kerry.gogobasketball.data;

public class WaitingRoomSeats {

    private String mAvatar;
    private String mId;
    private String mPosition;
    private String mGender;
    private int mSort;
    private boolean mSeatAvailable;

    public WaitingRoomSeats(){
        mAvatar = "";
        mId = "";
        mPosition = "";
        mGender = "";
        mSort = -1;
        mSeatAvailable = true;
    }

    public int getSort() {
        return mSort;
    }

    public void setSort(int sort) {
        this.mSort = sort;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        this.mAvatar = avatar;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getPosition() {
        return mPosition;
    }

    public void setPosition(String position) {
        this.mPosition = position;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    public boolean isSeatAvailable() {
        return mSeatAvailable;
    }

    public void setSeatAvailable(boolean seatAvailable) {
        this.mSeatAvailable = seatAvailable;
    }
}
