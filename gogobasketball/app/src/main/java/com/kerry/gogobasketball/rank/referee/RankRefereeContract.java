package com.kerry.gogobasketball.rank.referee;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface RankRefereeContract {

    interface View extends BaseView<Presenter> {

        void showRankRefereeUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

    }
}
