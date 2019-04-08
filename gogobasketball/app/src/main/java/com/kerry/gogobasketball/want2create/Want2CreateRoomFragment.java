package com.kerry.gogobasketball.want2create;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;

public class Want2CreateRoomFragment extends Fragment implements Want2CreateRoomContract.View,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private Want2CreateRoomContract.Presenter mPresenter;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioRefereeYes;
    private RadioButton mRadioRefereeNo;
    private TextView mTextRefereeWarning;
    private Button mBtnCreateConfirm;
    private Button mBtnCreateCancel;
    private ImageButton mBtnBackStack;

    public Want2CreateRoomFragment() {
        // Requires empty public constructor
    }

    public static Want2CreateRoomFragment newInstance() {
        return new Want2CreateRoomFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setPresenter(Want2CreateRoomContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_want2create_room, container, false);

        mTextRefereeWarning = root.findViewById(R.id.text_want2create_warning);
        mRadioGroup = root.findViewById(R.id.radios_referee_selector);
        mRadioGroup.setOnCheckedChangeListener(this);

        mBtnCreateConfirm = root.findViewById(R.id.btn_want2create_build_confirm);
        mBtnCreateConfirm.setOnClickListener(this);

        mBtnCreateCancel = root.findViewById(R.id.btn_want2create_build_cancel);
        mBtnCreateCancel.setOnClickListener(this);

        mBtnBackStack = root.findViewById(R.id.btn_want2create_backstack);
        mBtnBackStack.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_want2create_build_confirm:
                mPresenter.openWaitingJoin();


                break;
            case R.id.btn_want2create_build_cancel:
                mPresenter.finishWant2CreateRoomUi();
                break;
            case R.id.btn_want2create_backstack:
                mPresenter.finishWant2CreateRoomUi();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radios_referee_yes:
//                mTextRefereeWarning.setText(GoGoBasketball.getAppContext().getString(R.string.referee_yes));
                mTextRefereeWarning.setText("裁判模式結果將列入天梯排名");
                mTextRefereeWarning.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.black_3f3a3a));
                break;
            case R.id.radios_referee_no:
//                mTextRefereeWarning.setText(GoGoBasketball.getAppContext().getString(R.string.referee_no));
                mTextRefereeWarning.setText("暫不支援非裁判模式");
                mTextRefereeWarning.setTextColor(GoGoBasketball.getAppContext().getColor(R.color.red_FF001F));
                mBtnCreateConfirm.setClickable(false);
                break;
            default:
                break;
        }
    }

    public void tapToAnimate(View view){
        final Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.bounce);
        mBtnCreateConfirm.startAnimation(animation);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.hideToolbarAndBottomNavigation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.showToolbarAndBottomNavigation();
    }

    @Override
    public void showWant2CreateRoomUi() {

    }

    @Override
    public boolean needReferee() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }


}
