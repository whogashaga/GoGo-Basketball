package com.kerry.gogobasketball.data;

public class UserInfo {

    private String mAvatar;
    private String mId;
    private String mPosition;
    private String mGender;
    private String mEmail;
    private RecordOfPlayer mPlayerRecord;
    private RecordOfReferee mRefereeRecord;

    public UserInfo(){
        mAvatar = "";
        mId = "";
        mPosition = "";
        mGender = "";
        mEmail = "";
        mPlayerRecord = new RecordOfPlayer();
        mRefereeRecord = new RecordOfReferee();
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

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
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
