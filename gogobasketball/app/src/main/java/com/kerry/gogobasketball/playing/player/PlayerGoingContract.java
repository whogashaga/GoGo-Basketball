package com.kerry.gogobasketball.playing.player;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface PlayerGoingContract {
    interface View extends BaseView<Presenter> {

        void openGameResultPlayerUi(String hostName, int nowSort);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void openGameResultPlayer(String hostName, int nowSort);

        void removeListenerPlayer();

        void setBackKeyDisable(boolean isBackKeyDisable);

        void setGamingNowMessage(boolean isGamingNow);

        void getHostNameFromWaitingJoinSlave(String hostName, int nowSort);
    }
}
