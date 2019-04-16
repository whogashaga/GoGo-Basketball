package com.kerry.gogobasketball.create_user;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface CreateUserContract {

    interface View extends BaseView<Presenter> {

        void showCreateUserUi();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void showCreateSuccessDialog();

    }
}
