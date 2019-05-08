package com.kerry.gogobasketball.profile.logout;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

import com.facebook.login.LoginManager;

public class LogoutPresenter implements LogoutContract.Presenter {

    private final LogoutContract.View mLogoutView;

    public LogoutPresenter(@NonNull LogoutContract.View commentView) {
        mLogoutView = checkNotNull(commentView, "commentView cannot be null!");
        mLogoutView.setPresenter(this);
    }

    @Override
    public void logoutFacebook() {
        LoginManager.getInstance().logOut();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showLoginFragment() {
    }

    @Override
    public void showLogoutSuccessDialog() {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public void start() {

    }
}
