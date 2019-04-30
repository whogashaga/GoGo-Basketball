package com.kerry.gogobasketball.profile;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.UserManager;

public class ProfilePresenter implements ProfileContract.Presenter {


    private final ProfileContract.View mProfileView;
    private User mUser;

    public ProfilePresenter(@NonNull ProfileContract.View profileView) {
        mProfileView = checkNotNull(profileView, "profileView cannot be null!");
        mProfileView.setPresenter(this);
        mUser = new User();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadProfileUserData(Activity activity) {
        UserManager.getInstance().getUserProfile(new UserManager.LoadCallback() {
            @Override
            public void onSuccess(User user) {
                mUser = user;
                mProfileView.showUserUi(user);
            }

            @Override
            public void onFail(String errorMessage) {

            }

            @Override
            public void onInvalidToken(String errorMessage) {

            }
        });
    }

    @Override
    public void openLogoutDialog() {

    }

    @Override
    public void openChangeIdDialog() {

    }

    @Override
    public void openChangeGender(String currentGender) {

    }

    @Override
    public void openChangePosition(String currentGender) {

    }

    @Override
    public void start() {

    }

}
