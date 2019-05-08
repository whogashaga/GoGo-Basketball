package com.kerry.gogobasketball.create_user;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

public interface CreateUserContract {

    interface View extends BaseView<Presenter> {

        void showCreateUserSuccessUi();

        void showIdAlreadyExist();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void showErrorToast(String message, boolean isShort);

        void getUserIniInfoFromLogin(String userFbId);

        void getPositionFromSpinner(String position);

        void onUserIdEditTextChange(CharSequence charSequence);

        void getGenderFromRadioGroup(String gender);

        void createUserClickConfirm();

        void showCreateUserSuccessDialog();

        void onCreateUserSuccess();

        void checkIfUserIdExists();

        void finishCreateUser();
    }
}
