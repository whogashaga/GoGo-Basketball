package com.kerry.gogobasketball.playing0referee;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class GamePlayingOfRefereePresenter implements GamePlayingOfRefereeContract.Presenter {

    private final GamePlayingOfRefereeContract.View mGamePlayingView;

    public GamePlayingOfRefereePresenter(@NonNull GamePlayingOfRefereeContract.View waiting4JoinView) {
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
    public void showGameResultUi() {

    }

    @Override
    public void start() {

    }
}
