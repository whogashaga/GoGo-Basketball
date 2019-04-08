package com.kerry.gogobasketball.data;

import android.support.annotation.NonNull;

public interface GoGoDataSource {

    interface GetWaitingRoomInfoCallback {

        void onCompleted(WaitingRoomInfo roomInfo);

        void onError(String errorMessage);
    }


    void getWaitingRoomInfo(@NonNull GetWaitingRoomInfoCallback callback);


}
