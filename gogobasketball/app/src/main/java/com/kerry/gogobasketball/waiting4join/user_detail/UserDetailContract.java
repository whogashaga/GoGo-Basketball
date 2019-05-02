package com.kerry.gogobasketball.waiting4join.user_detail;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.User;

public interface UserDetailContract {

    interface View extends BaseView<Presenter> {

        void shotDetailUi(User user);

        void finishDetailUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void getUserIdFromWaiting(String hostName);

        void queryUserInfoDocId();

    }
}
