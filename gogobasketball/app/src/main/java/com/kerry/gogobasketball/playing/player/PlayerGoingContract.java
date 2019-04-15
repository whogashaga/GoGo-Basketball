package com.kerry.gogobasketball.playing.player;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface PlayerGoingContract {
    interface View extends BaseView<Presenter> {

        void showPlayingGameUi();

        void openGameResultPlayerUi(String hostName, int nowSort);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void forced2FinishPlayingUi();

        void openGameResultPlayer(String hostName, int nowSort);

        void forced2FinishGaming();

        void setBackKeyDisable(boolean isBackKeyDisable);

        void getHostNameFromWaitingJoinSlave(String hostName, int nowSort);
    }
}
