package com.kerry.gogobasketball.profile;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void showUserUi(User user);

        boolean isActive();

        void showLogoutDialogUi();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadProfileUserData(Activity activity);

    }
}
