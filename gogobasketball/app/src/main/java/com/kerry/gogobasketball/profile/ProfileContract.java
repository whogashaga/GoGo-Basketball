package com.kerry.gogobasketball.profile;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void showUserUi(User user);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadProfileUserData(Activity activity);

        void openLogoutDialog();

        void openChangeIdDialog();

        void openChangeGender(String currentGender);

        void openChangePosition(String currentPosition);

    }
}
