package com.kerry.gogobasketball.result.player.comment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.util.Util;

import static com.google.common.base.Preconditions.checkNotNull;

public class CommentRefereeDialog extends DialogFragment implements CommentRefereeContract.View, WheelPicker.OnItemSelectedListener {

    private CommentRefereeContract.Presenter mPresenter;
    private WheelPicker wheelCenter;
    View mLayout;

    @Override
    public void setPresenter(CommentRefereeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CommentRefereeDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_comment_picker, container, false);
//        view.setOnClickListener(this);
        mLayout = view.findViewById(R.id.layout_want2comment);

        wheelCenter = view.findViewById(R.id.wheel_comment_referee);
        wheelCenter.setOnItemSelectedListener(this);
//        Util.setTouchDelegate(view.findViewById(R.id.button_want2comment_close));

        return view;
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        switch (picker.getId()) {
            case R.id.wheel_comment_referee:
                text = "Left:";
                break;
        }
        Toast.makeText(GoGoBasketball.getAppContext(), text + String.valueOf(data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_slide_left));
    }

    @Override
    public void setDialogTitle(String hostName) {

    }

    @Override
    public void setSendOutEnable() {

    }

    @Override
    public void finishCommentUi() {

    }

    @Override
    public void showLoginUi() {

    }

}
