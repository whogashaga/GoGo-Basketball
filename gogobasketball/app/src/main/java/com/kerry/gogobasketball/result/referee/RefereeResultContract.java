package com.kerry.gogobasketball.result.referee;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

public interface RefereeResultContract {

    interface View extends BaseView<Presenter> {

        void showResultRefereeUi(GamingRoomInfo gamingRoomInfo);

        void getHostNameFromPresenter(String hostName);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void finishResultResultUi();

        void setBackKeyDisable(boolean isBackKeyDisable);

        void getHostNameFromRefereeGoing(String hostName);

        void getRoomInfoFromFireStore(String hostName);

    }
}
