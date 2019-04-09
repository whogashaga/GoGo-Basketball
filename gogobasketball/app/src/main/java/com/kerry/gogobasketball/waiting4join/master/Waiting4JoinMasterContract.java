package com.kerry.gogobasketball.waiting4join.master;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

public interface Waiting4JoinMasterContract {

    interface View extends BaseView<Presenter> {

        void showPlayingGameUi();

        boolean needTimer();

        boolean isActive();

        void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo);

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void openGamePlayingOfReferee();

        void finishWaiting4JoinUi();

        void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo);
    }
}
