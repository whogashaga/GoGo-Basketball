package com.kerry.gogobasketball.profile.logout;

import android.support.annotation.NonNull;
import android.util.Log;

import static com.google.common.base.Preconditions.checkNotNull;

public class LogoutPresenter implements LogoutContract.Presenter {

    private final LogoutContract.View mLogoutView;

    public LogoutPresenter(@NonNull LogoutContract.View commentView) {
        mLogoutView = checkNotNull(commentView, "commentView cannot be null!");
        mLogoutView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void logoutFacebookBySdk() {

    }

    @Override
    public void showLoginFragment() {
    }

    @Override
    public void showLogoutSuccessDialog() {

    }

    @Override
    public void start() {

    }
}
