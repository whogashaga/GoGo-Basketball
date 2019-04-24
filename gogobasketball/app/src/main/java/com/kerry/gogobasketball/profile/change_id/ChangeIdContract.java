package com.kerry.gogobasketball.profile.change_id;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface ChangeIdContract {

    interface View extends BaseView<Presenter> {

        void showIdAlreadyExist();

        void showChangeIdSuccessUi();

        void showNewProfileUi();

        void finishChangeIdUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void onUserNewIdEditTextChange(CharSequence charSequence);

        void checkIfUserNewIdExists(Activity activity);

        void showDataChangeSuccessDialog();

        void loadProfileUserData(Activity activity);

    }
}
