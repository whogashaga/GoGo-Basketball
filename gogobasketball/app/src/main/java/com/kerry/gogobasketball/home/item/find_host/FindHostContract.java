package com.kerry.gogobasketball.home.item.find_host;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface FindHostContract {

    interface View extends BaseView<Presenter> {

        void showFindNoHost();

        void showFindHostSuccessUi();

        void finishFindHostUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void onUserNewIdEditTextChange(CharSequence charSequence);

        void checkIfUserNewIdExists(Activity activity);

        void showFindSuccessDialog();

        void showFindFailDialog();

        void updateRecyclerView(Activity activity);

    }
}
