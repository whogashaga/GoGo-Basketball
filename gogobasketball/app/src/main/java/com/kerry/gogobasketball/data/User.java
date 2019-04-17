package com.kerry.gogobasketball.data;

public class User {

    private String mAvatar;
    private String mId;
    private String mPosition;
    private String mGender;
    private String mFacebookId;
    private String mName;
    private RecordOfPlayer mPlayerRecord;
    private RecordOfReferee mRefereeRecord;

    public User() {
        mAvatar = "";
        mId = "";
        mPosition = "";
        mGender = "male";
        mFacebookId = "";
        mName = "";
        mPlayerRecord = new RecordOfPlayer();
        mRefereeRecord = new RecordOfReferee();
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
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

    public String getFacebookId() {
        return mFacebookId;
    }

    public void setFacebookId(String facebookId) {
        this.mFacebookId = facebookId;
    }

    public RecordOfPlayer getPlayerRecord() {
        return mPlayerRecord;
    }

    public void setPlayerRecord(RecordOfPlayer playerRecord) {
        this.mPlayerRecord = playerRecord;
    }

    public RecordOfReferee getRefereeRecord() {
        return mRefereeRecord;
    }

    public void setRefereeRecord(RecordOfReferee refereeRecord) {
        this.mRefereeRecord = refereeRecord;
    }
}
