package com.kerry.gogobasketball.result.player.comment;

import static com.google.common.base.Preconditions.checkNotNull;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.aigestudio.wheelpicker.WheelPicker;
import com.kerry.gogobasketball.R;

import java.util.ArrayList;
import java.util.List;

public class CommentRefereeDialog extends DialogFragment implements CommentRefereeContract.View,
        WheelPicker.OnItemSelectedListener, View.OnClickListener {

    private CommentRefereeContract.Presenter mPresenter;
    private WheelPicker wheelCenter;
    private View mLayout;
    private View mLayoutDialog;
    private List<Integer> mRating;
    private Button mBtnSendOut;
    private View mRoot;

    @Override
    public void setPresenter(CommentRefereeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
        mRating = new ArrayList<>();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CommentRefereeDialog);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.dialog_comment_picker, container, false);
//        view.setOnClickListener(this);
        mLayout = mRoot.findViewById(R.id.layout_want2comment);
        mLayout.setOnClickListener(this);
        mLayoutDialog = mRoot.findViewById(R.id.dialog_comment_referee);
        mLayoutDialog.setOnClickListener(this);

        wheelCenter = mRoot.findViewById(R.id.wheel_comment_referee);
        mBtnSendOut = mRoot.findViewById(R.id.btn_comment_send_out);
        mBtnSendOut.setOnClickListener(this);

        return mRoot;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.layout_want2comment:
                break;
            case R.id.btn_comment_send_out:
                mPresenter.queryRefereeUserDocId();
                mBtnSendOut.setClickable(false);
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        switch (picker.getId()) {
            case R.id.wheel_comment_referee:
                break;
            default:
                break;
        }
//        Toast.makeText(GoGoBasketball.getAppContext(), String.valueOf(data), Toast.LENGTH_SHORT).show();
        mPresenter.onWheelViewChanged((Integer) data);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_slide_left));
        for (int i = 1; i < 11; i++) {
            mRating.add(i);
        }
        wheelCenter.setOnItemSelectedListener(this);
        wheelCenter.setData(mRating);
    }

    @Override
    public void finishCommentUi() {
        dismiss();
        mPresenter.showSendCommentSuccessDialog();
        mPresenter.showBack2LobbyButtonPlayerResult();
    }
}
