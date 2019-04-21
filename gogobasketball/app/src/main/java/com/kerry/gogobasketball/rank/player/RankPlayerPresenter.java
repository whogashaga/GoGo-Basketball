package com.kerry.gogobasketball.rank.player;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class RankPlayerPresenter implements RankPlayerContract.Presenter {

    private final RankPlayerContract.View mRankPlayerView;

    public RankPlayerPresenter(@NonNull RankPlayerContract.View profileView) {
        mRankPlayerView = checkNotNull(profileView, "RankPlayerView cannot be null!");
        mRankPlayerView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
