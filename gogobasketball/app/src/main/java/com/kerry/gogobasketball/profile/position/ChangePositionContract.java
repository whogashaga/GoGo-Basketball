package com.kerry.gogobasketball.profile.position;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface ChangePositionContract {

    interface View extends BaseView<Presenter> {

        void showChangePositionSuccessUi();

        void showNewProfileUi();

        void finishChangePositionUi();

        void showErrorPosition();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void getPositionFromWheel(String position);

        void compareNewOldPosition(Activity activity);

        void showDataChangeSuccessDialog();

        void loadProfileUserData(Activity activity);

        void getNowPositionFromProfile(String currentPosition);

    }
}
