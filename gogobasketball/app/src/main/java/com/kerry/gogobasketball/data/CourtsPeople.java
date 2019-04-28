package com.kerry.gogobasketball.data;

import com.kerry.gogobasketball.util.Constants;

public class CourtsPeople {

    private String mId;
    private String mStatus;

    public CourtsPeople() {
        mId = "";
        mStatus = Constants.STATUS_WAITING;
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
