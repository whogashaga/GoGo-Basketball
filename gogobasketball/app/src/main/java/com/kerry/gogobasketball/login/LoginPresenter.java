package com.kerry.gogobasketball.login;

import android.support.annotation.NonNull;

import com.kerry.gogobasketball.data.User;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(@NonNull LoginContract.View profileView) {
        mLoginView = checkNotNull(profileView, "LoginView cannot be null!");
        mLoginView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public void loadProfileUserData() {

    }

    @Override
    public void checkProfileUserData() {

    }

    @Override
    public void showLoginSuccessDialog() {

    }

    @Override
    public void onLoginSuccess(String userDocId) {

    }

    @Override
    public void start() {

    }
}
