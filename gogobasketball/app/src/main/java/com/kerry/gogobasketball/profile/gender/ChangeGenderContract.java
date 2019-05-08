package com.kerry.gogobasketball.profile.gender;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface ChangeGenderContract {

    interface View extends BaseView<Presenter> {

        void showChangeGenderSuccessUi();

        void showNewProfileUi();

        void finishChangeGenderUi();

        void showErrorGender();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void getGenderFromRadios(String gender);

        void compareOldNewGender(Activity activity);

        void showDataChangeSuccessDialog();

        void loadProfileUserData(Activity activity);

        void getNowGenderFromProfile(String currentGender);

    }
}
