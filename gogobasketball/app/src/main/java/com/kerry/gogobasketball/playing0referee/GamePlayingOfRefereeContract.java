package com.kerry.gogobasketball.playing0referee;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface GamePlayingOfRefereeContract {

    interface View extends BaseView<Presenter> {

        void showPlayingGameUi();

        boolean isActive();


        void increaseScoreP1();

        void decreaseScoreP1();

        void increaseReboundP1();

        void decreaseReboundP1();

        void increaseFoulP1();

        void decreaseFoulP1();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void forced2FinishPlayingUi();

        void showGameResultUi();

        void clickScorePlusP1();

        void clickScoreMinusP1();

        void clickReboundPlusP1();

        void clickReboundMinusP1();

        void clickFoulPlusP1();

        void clickFoulMinusP1();

    }
}
