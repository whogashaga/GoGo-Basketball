package com.kerry.gogobasketball.result.player.comment;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class CommentRefereePresenter implements CommentRefereeContract.Presenter {


    private final CommentRefereeContract.View mCommentRefereeView;
    private String mHostName;

    public CommentRefereePresenter(@NonNull CommentRefereeContract.View commentView) {
        mCommentRefereeView = checkNotNull(commentView, "commentView cannot be null!");
        mCommentRefereeView.setPresenter(this);
        mHostName = "";
    }


    @Override
    public void getHostNameFromResult(String hostName) {
        mHostName = hostName;
    }


    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void getComment() {

    }

    @Override
    public void sendOutComment() {

    }

    @Override
    public void showSendCommentSuccessDialog() {

    }

    @Override
    public void updateAfterSendOutComment() {

    }

    @Override
    public void start() {

    }
}
