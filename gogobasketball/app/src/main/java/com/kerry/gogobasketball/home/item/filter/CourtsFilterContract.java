package com.kerry.gogobasketball.home.item.filter;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

import java.util.ArrayList;

public interface CourtsFilterContract {

    interface View extends BaseView<Presenter> {

        void setWheelPicker(ArrayList<String> courtsList);

        void showFindNoCourts();

        void showCourtsFilterSuccessUi(ArrayList<WaitingRoomInfo> list);

        void finishCourtsFilterUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void updateRecyclerView(Activity activity);

        void queryCourts();

        void updateLooking4RoomView(ArrayList<WaitingRoomInfo> list);

        void getLocationFromWheel(String position);

        void getCourtsListFromFirebase();
    }
}
