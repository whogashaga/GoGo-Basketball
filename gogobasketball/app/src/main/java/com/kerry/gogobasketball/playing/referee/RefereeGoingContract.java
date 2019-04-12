package com.kerry.gogobasketball.playing.referee;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface RefereeGoingContract {

    interface View extends BaseView<Presenter> {

        void showPlayingGameUi();

        boolean isActive();


        void increaseScoreP1();

        void decreaseScoreP1();

        void increaseReboundP1();

        void decreaseReboundP1();

        void increaseFoulP1();

        void decreaseFoulP1();


        void increaseScoreP2();

        void decreaseScoreP2();

        void increaseReboundP2();

        void decreaseReboundP2();

        void increaseFoulP2();

        void decreaseFoulP2();


        void increaseScoreP3();

        void decreaseScoreP3();

        void increaseReboundP3();

        void decreaseReboundP3();

        void increaseFoulP3();

        void decreaseFoulP3();


        void increaseScoreP4();

        void decreaseScoreP4();

        void increaseReboundP4();

        void decreaseReboundP4();

        void increaseFoulP4();

        void decreaseFoulP4();


        void increaseScoreP5();

        void decreaseScoreP5();

        void increaseReboundP5();

        void decreaseReboundP5();

        void increaseFoulP5();

        void decreaseFoulP5();


        void increaseScoreP6();

        void decreaseScoreP6();

        void increaseReboundP6();

        void decreaseReboundP6();

        void increaseFoulP6();

        void decreaseFoulP6();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void forced2FinishPlayingUi();

        void showGameResult();

        void forced2FinishGaming();

        void fireStoreScorePlusP1();

        void fireStoreScoreMinusP1();

        void fireStoreReboundPlusP1();

        void fireStoreReboundMinusP1();

        void fireStoreFoulPlusP1();

        void fireStoreFoulMinusP1();

    }
}
