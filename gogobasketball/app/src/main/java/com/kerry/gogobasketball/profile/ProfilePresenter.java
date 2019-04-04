package com.kerry.gogobasketball.profile;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

public class ProfilePresenter implements ProfileContract.Presenter {


    private final ProfileContract.View mProfileView;

    public ProfilePresenter(@NonNull ProfileContract.View profileView) {
        mProfileView = checkNotNull(profileView, "profileView cannot be null!");
        mProfileView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadProfileUserData() {

    }

    @Override
    public void checkProfileUserData() {

    }

    @Override
    public void start() {

    }
}
