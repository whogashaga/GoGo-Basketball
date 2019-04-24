package com.kerry.gogobasketball.profile.change_position;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface ChangePositionContract {

    interface View extends BaseView<Presenter> {

        void showChangePositionSuccessUi();

        void showNewProfileUi();

        void finishChangePositionUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void getPositionFromWheel(String position);

        void updatePositionData(Activity activity);

        void showDataChangeSuccessDialog();

        void loadProfileUserData(Activity activity);

    }
}
