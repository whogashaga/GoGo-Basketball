package com.kerry.gogobasketball.friends;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.profile.ProfileContract;

public interface FriendContract {

    interface View extends BaseView<Presenter> {

        void showFriendUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void postDemoPlayer(int position);

        void updateDemoTotalNumber();
    }
}
