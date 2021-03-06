package com.kerry.gogobasketball.result.player;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.GamingRoomInfo;

public interface PlayerResultContract {

    interface View extends BaseView<Presenter> {

        void showResultPlayerUi(GamingRoomInfo gamingRoomInfo, int nowSort);

        void getHostNameFromPresenter(String hostName, int nowSort);

        void showLobbyButton();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void setActivityBackgroundLandScape();

        void setActivityBackgroundPortrait();

        void setBackKeyDisable(boolean isBackKeyDisable);

        void getHostNameFromPlayerGoing(String hostName, int nowSort);

        void getRoomInfoFromFireStorePlayer(String hostName, int nowSort);

        void openCommentReferee(String refereeName);

        void setBack2LobbyVisible();

        void setHave2Comment(boolean have2Comment);

    }

}
