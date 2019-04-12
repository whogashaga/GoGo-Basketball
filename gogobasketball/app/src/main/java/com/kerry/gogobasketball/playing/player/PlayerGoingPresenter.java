package com.kerry.gogobasketball.playing.player;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class PlayerGoingPresenter implements PlayerGoingContract.Presenter {

    private final PlayerGoingContract.View mGamePlayingView;

    public PlayerGoingPresenter(@NonNull PlayerGoingContract.View waiting4JoinView) {
        mGamePlayingView = checkNotNull(waiting4JoinView, "GamePlayingView cannot be null!");
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
    public void start() {

    }
}
