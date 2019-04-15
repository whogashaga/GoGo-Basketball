package com.kerry.gogobasketball.result.player;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.GamingRoomInfo;

public interface PlayerResultContract {

    interface View extends BaseView<Presenter> {

        void showResultPlayerUi(GamingRoomInfo gamingRoomInfo, int nowSort);

        void getHostNameFromPresenter(String hostName, int nowSort);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void finishResultResultUi();

        void setBackKeyDisable(boolean isBackKeyDisable);

        void getHostNameFromPlayerGoing(String hostName, int nowSort);

        void getRoomInfoFromFireStorePlayer(String hostName, int nowSort);


    }

}
