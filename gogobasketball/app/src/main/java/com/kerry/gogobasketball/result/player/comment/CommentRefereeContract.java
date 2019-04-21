package com.kerry.gogobasketball.result.player.comment;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface CommentRefereeContract {

    interface View extends BaseView<Presenter> {

        void setDialogTitle(String hostName);

        void finishCommentUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showSendCommentSuccessDialog();

        void getRefereeNameFromResult(String hostName);

        void onWheelViewChanged(int rating);

        void queryRefereeUserDocId();

        void showBack2LobbyButtonPlayerResult();

    }
}
