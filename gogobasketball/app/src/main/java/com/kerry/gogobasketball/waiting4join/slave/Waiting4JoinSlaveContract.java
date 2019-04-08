package com.kerry.gogobasketball.waiting4join.slave;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface Waiting4JoinSlaveContract {

    interface View extends BaseView<Presenter> {

        void showPlayingGameSlaveUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void openGamePlayingOfSlave();

        void finishWaiting4JoinUi();
    }
}
