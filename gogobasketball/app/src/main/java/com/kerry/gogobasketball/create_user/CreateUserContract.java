package com.kerry.gogobasketball.create_user;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

public interface CreateUserContract {

    interface View extends BaseView<Presenter> {

        void openHomeUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void showCreateSuccessDialog();

        void showErrorToast(String message, boolean isShort);

        void getUserIniInfoFromLogin(String userDocId);

        void getPositionFromSpinner(String position);

        void checkIfUserIdExisted();

        void onUerIdEditTextChange(CharSequence charSequence);

        void getGenderFromRadioGroup(String gender);

        void openHome();
    }
}
