package com.kerry.gogobasketball.profile.logout;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface LogoutContract {

    interface View extends BaseView<Presenter> {

        void openLoginFragment();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void logoutFacebook();

        void showLoginFragment();

        void showLogoutSuccessDialog();

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

    }
}
