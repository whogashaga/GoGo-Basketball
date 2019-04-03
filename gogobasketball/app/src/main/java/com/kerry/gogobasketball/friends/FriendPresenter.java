package com.kerry.gogobasketball.friends;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class FriendPresenter implements FriendContract.Presenter {

    private final FriendContract.View mFriendView;

    public FriendPresenter(@NonNull FriendContract.View friendView) {
        mFriendView = checkNotNull(friendView, "FriendView cannot be null!");
        mFriendView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
