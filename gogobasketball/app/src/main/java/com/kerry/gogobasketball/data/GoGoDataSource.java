package com.kerry.gogobasketball.data;

import android.support.annotation.NonNull;

public interface GoGoDataSource {

    interface GetRoomInfoCallback {

        void onCompleted();

        void onError(String errorMessage);
    }

    void getRoomInformation(@NonNull GetRoomInfoCallback callback);
}
