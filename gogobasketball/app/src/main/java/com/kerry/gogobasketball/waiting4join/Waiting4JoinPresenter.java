package com.kerry.gogobasketball.waiting4join;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

public class Waiting4JoinPresenter implements Waiting4JoinContract.Presenter {

    private final Waiting4JoinContract.View mWaiting4JoineView;

    public Waiting4JoinPresenter(@NonNull Waiting4JoinContract.View waiting4JoinView) {
        mWaiting4JoineView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoineView.setPresenter(this);
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
    public void openGamePlayingOfReferee() {

    }

    @Override
    public void finishWaiting4JoinUi() {

    }

    @Override
    public void start() {

    }
}
