package com.kerry.gogobasketball.login;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showLoginUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadProfileUserData();

        void checkProfileUserData();

        void showLoginSuccessDialog();

        void onLoginSuccess();

    }
}
