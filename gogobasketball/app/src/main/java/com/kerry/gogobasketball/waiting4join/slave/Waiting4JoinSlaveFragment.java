package com.kerry.gogobasketball.waiting4join.slave;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlaveFragment extends Fragment implements Waiting4JoinSlaveContract.View, View.OnClickListener {

    View mRoot;

    private Waiting4JoinSlaveContract.Presenter mPresenter;
    private RadioGroup mRadioGroupTimer;
    private RadioButton mRadioTimerOn;
    private RadioButton mRadioTimerOff;
    private ImageButton mBtnBackStack;
    private Button mBtnCancel;

    public Waiting4JoinSlaveFragment() {
    }

    public static Waiting4JoinSlaveFragment newInstance() {
        return new Waiting4JoinSlaveFragment();
    }

    @Override
    public void setPresenter(Waiting4JoinSlaveContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_waiting4join_slave, container, false);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        mBtnBackStack = mRoot.findViewById(R.id.btn_slave_waiting4join_back_arrow);
        mBtnBackStack.setOnClickListener(this);
        mBtnCancel = mRoot.findViewById(R.id.btn_slave_waiting4join_cancel);
        mBtnCancel.setOnClickListener(this);

        mRadioTimerOn = mRoot.findViewById(R.id.radio_slave_timer_yes);
        mRadioTimerOn.setClickable(false);
        mRadioTimerOff = mRoot.findViewById(R.id.radio_slave_timer_no);
        mRadioTimerOff.setClickable(false);
        mRadioGroupTimer = mRoot.findViewById(R.id.radio_slave_timer_selector);
//        mRadioGroupTimer.setOnCheckedChangeListener(this);


        return mRoot;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_slave_waiting4join_back_arrow:
                mPresenter.finishWaiting4JoinUi();
                break;
            case R.id.btn_slave_waiting4join_cancel:
                mPresenter.finishWaiting4JoinUi();
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.hideToolbarAndBottomNavigation();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.showToolbarAndBottomNavigation();
    }

    @Override
    public void showPlayingGameSlaveUi() {

    }

    @Override
    public boolean isActive() {
        return false;
    }


}