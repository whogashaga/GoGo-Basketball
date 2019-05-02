package com.kerry.gogobasketball.data;

import com.kerry.gogobasketball.util.Constants;

public class CourtsPeople {

    private String mId;
    private String mStatus;
    private String mFacebookId;

    public CourtsPeople() {
        mId = "";
        mFacebookId = "";
        mStatus = Constants.STATUS_WAITING;
    }

    public String getFacebookId() {
        return mFacebookId;
    }

    public void setFacebookId(String mFacebookId) {
        this.mFacebookId = mFacebookId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}
