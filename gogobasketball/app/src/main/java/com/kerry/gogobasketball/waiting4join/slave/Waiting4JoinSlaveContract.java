package com.kerry.gogobasketball.waiting4join.slave;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

public interface Waiting4JoinSlaveContract {

    interface View extends BaseView<Presenter> {

        void showPlayingGameSlaveUi();

        boolean isActive();

        void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo);
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void openGamePlayingOfSlave();

        void finishWaiting4JoinUi();

//        void updateRoomInfo2FireBase();

        void getHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo);

        void deleteSeatInfoWhenLeaveRoom();
    }

}
