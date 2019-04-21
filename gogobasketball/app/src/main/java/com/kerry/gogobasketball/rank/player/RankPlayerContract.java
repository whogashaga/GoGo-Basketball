package com.kerry.gogobasketball.rank.player;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface RankPlayerContract {

    interface View extends BaseView<Presenter> {

        void showRankPlayerUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

    }
}
