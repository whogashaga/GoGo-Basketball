package com.kerry.gogobasketball.result.player.comment;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface CommentRefereeContract {

    interface View extends BaseView<Presenter> {

        void setDialogTitle(String hostName);

        void setSendOutEnable();

        void finishCommentUi();

        void showLoginUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void getComment();

        void sendOutComment();

        void showSendCommentSuccessDialog();

        void updateAfterSendOutComment();

        void getRefereeNameFromResult(String hostName);

        void onWheelViewChanged(int rating);

        void queryRefereeUserDocId();

    }
}
