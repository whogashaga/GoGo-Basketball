package com.kerry.gogobasketball.rank.referee;

import android.support.annotation.NonNull;

import com.kerry.gogobasketball.rank.RankContract;
import com.kerry.gogobasketball.rank.player.RankPlayerFragment;

import static com.google.common.base.Preconditions.checkNotNull;

public class RankRefereePresenter implements RankRefereeContract.Presenter {

    private final RankRefereeContract.View mRankRefereeView;

    public RankRefereePresenter(@NonNull RankRefereeContract.View profileView) {
        mRankRefereeView = checkNotNull(profileView, "RankRefereeView cannot be null!");
        mRankRefereeView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
