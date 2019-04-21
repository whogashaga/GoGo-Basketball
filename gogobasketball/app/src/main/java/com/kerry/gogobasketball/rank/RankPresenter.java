package com.kerry.gogobasketball.rank;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

import com.kerry.gogobasketball.rank.player.RankPlayerFragment;
import com.kerry.gogobasketball.rank.referee.RankRefereeFragment;

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
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public RankPlayerFragment findPlayerRankView() {
        return null;
    }

    @Override
    public RankRefereeFragment findRefereeRankView() {
        return null;
    }

    @Override
    public void start() {

    }
}
