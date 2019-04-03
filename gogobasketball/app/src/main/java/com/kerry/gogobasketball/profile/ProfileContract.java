package com.kerry.gogobasketball.profile;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void showUserUi();

        boolean isActive();

        void showLogoutDialogUi();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadProfileUserData();

        void checkProfileUserData();

    }
}
