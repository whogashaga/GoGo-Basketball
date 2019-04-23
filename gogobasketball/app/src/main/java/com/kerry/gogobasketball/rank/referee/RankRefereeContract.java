package com.kerry.gogobasketball.rank.referee;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

import java.util.ArrayList;

public interface RankRefereeContract {

    interface View extends BaseView<Presenter> {

        void showRankRefereeUi(ArrayList<User> arrayList, String recordType);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadRankRefereeByJustices();

        void loadRankRefereeByRating();

    }
}
