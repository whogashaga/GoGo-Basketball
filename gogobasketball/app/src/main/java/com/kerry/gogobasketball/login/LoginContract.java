package com.kerry.gogobasketball.login;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showLoginUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadProfileUserData();

        void checkProfileUserData();

        void showLoginSuccessDialog();

        void onLoginSuccess(String userDocId);

    }
}
