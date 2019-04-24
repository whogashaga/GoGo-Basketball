package com.kerry.gogobasketball.profile.change_id;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface ChangeIdContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void onUserNewIdEditTextChange(CharSequence charSequence);

        void createUserClickConfirm();

        void showChangeIdSuccessDialog();

    }
}
