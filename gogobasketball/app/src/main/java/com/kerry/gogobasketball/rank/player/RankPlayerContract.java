package com.kerry.gogobasketball.rank.player;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

import java.util.ArrayList;

public interface RankPlayerContract {

    interface View extends BaseView<Presenter> {

        void showRankPlayerUi(ArrayList<User> arrayList, String recordType);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadRankPlayerByGames();

        void loadRankPlayerByWinning();

        void loadRankPlayerByScore();

        void loadRankPlayerByRebound();

        void loadRankPlayerByFoul();
    }
}
