package com.kerry.gogobasketball.rank;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class RankPresenter implements RankContract.Presenter {


    private final RankContract.View mRankView;

    public RankPresenter(@NonNull RankContract.View rankView) {
        mRankView = checkNotNull(rankView, "RankView cannot be null!");
        mRankView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
