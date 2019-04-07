package com.kerry.gogobasketball.waiting4join;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface Waiting4JoinContract {

    interface View extends BaseView<Presenter> {

        void showPlayingGameUi();

        boolean needTimer();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void openGamePlayingOfReferee();

        void finishWaiting4JoinUi();
    }
}