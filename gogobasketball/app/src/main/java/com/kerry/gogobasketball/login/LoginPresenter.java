package com.kerry.gogobasketball.login;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.UserManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(@NonNull LoginContract.View profileView) {
        mLoginView = checkNotNull(profileView, "LoginView cannot be null!");
        mLoginView.setPresenter(this);
    }

    @Override
    public void loginFbOnClick(Activity activity) {
        UserManager.getInstance().loginGoGoBasketballByFacebook(activity, new UserManager.LoadCallback() {
            @Override
            public void onSuccess(User user) {
                Log.d(Constants.TAG,"LoginFragment loginGoGoBasketballByFacebook onSuccess!" + user.getFacebookId());
                mLoginView.showLoginSuccessUi(user);
            }

            @Override
            public void onFail(String errorMessage) {
                Log.d(Constants.TAG,"LoginFragment loginGoGoBasketballByFacebook Fail!");
            }

            @Override
            public void onInvalidToken(String errorMessage) {
                Log.d(Constants.TAG,"LoginFragment Token 過期!");
            }
        });
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
    public void showLoginSuccessDialog() {

    }

    @Override
    public void onLoginSuccess(User userDocId) {

    }

    @Override
    public void start() {

    }
}
