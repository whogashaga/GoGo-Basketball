package com.kerry.gogobasketball.playing.referee;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

public class RefereeGoingPresenter implements RefereeGoingContract.Presenter {

    private final RefereeGoingContract.View mGamePlayingView;

    public RefereeGoingPresenter(@NonNull RefereeGoingContract.View refereeGoingView) {
        mGamePlayingView = checkNotNull(refereeGoingView, "GamePlayingView cannot be null!");
        mGamePlayingView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public void loadPlayersInfoFromFirebase() {

    }

    @Override
    public void loadRefereeInfoFromFirebase() {

    }

    @Override
    public void forced2FinishPlayingUi() {

    }

    @Override
    public void showGameResult() {

    }

    @Override
    public void forced2FinishGaming() {

    }

    @Override
    public void fireStoreScorePlusP1() {

    }

    @Override
    public void fireStoreScoreMinusP1() {

    }

    @Override
    public void fireStoreReboundPlusP1() {

    }

    @Override
    public void fireStoreReboundMinusP1() {

    }

    @Override
    public void fireStoreFoulPlusP1() {

    }

    @Override
    public void fireStoreFoulMinusP1() {

    }


    @Override
    public void start() {

    }
}
