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


    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void forced2FinishPlayingUi();

        void showGameResultUi();

        void fireStoreScorePlusP1();

        void fireStoreScoreMinusP1();

        void fireStoreReboundPlusP1();

        void fireStoreReboundMinusP1();

        void fireStoreFoulPlusP1();

        void fireStoreFoulMinusP1();

    }
}
