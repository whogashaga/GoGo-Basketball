package com.kerry.gogobasketball.playing0referee;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface GamePlayingOfRefereeContract {

    interface View extends BaseView<Presenter> {

        void showPlayingGameUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void forced2FinishPlayingUi();

        void showGameResultUi();
    }
}
