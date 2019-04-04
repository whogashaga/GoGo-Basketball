package com.kerry.gogobasketball.want2create;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class Want2CreateRoomPresenter implements Want2CreateRoomContract.Presenter {

    private final Want2CreateRoomContract.View mWant2CreateRoomView;

    public Want2CreateRoomPresenter(@NonNull Want2CreateRoomContract.View want2CreateRoomView) {
        mWant2CreateRoomView = checkNotNull(want2CreateRoomView, "FriendView cannot be null!");
        mWant2CreateRoomView.setPresenter(this);
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
    public void updateRoomName2Firebase() {

    }

    @Override
    public void updateLocation2Firebase() {

    }

    @Override
    public void openWaitingJoin() {

    }

    @Override
    public void finishWant2CreateRoomUi() {

    }

    @Override
    public void start() {

    }
}
