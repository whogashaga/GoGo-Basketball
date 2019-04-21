package com.kerry.gogobasketball.rank;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.rank.player.RankPlayerFragment;
import com.kerry.gogobasketball.rank.referee.RankRefereeFragment;

public interface RankContract {

    interface View extends BaseView<Presenter> {

        void showRankUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        RankPlayerFragment findPlayerRankView();

        RankRefereeFragment findRefereeRankView();
    }
}
