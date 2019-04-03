package com.kerry.gogobasketball.rank;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface RankContract {

    interface View extends BaseView<Presenter> {

        void showRankUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

    }
}
