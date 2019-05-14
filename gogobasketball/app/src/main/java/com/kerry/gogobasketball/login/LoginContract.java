package com.kerry.gogobasketball.login;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showLoginSuccessUi(User user);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loginFbOnClick(Activity activity);

        void showLoginSuccessDialog();

        void onLoginSuccess(User user);

        void switchToHotsByBottomNavigation();

        void getLocationPermissionWhenLogin(Activity activity);
    }
}
