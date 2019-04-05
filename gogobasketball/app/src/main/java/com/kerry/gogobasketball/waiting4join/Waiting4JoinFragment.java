package com.kerry.gogobasketball.waiting4join;

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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinFragment extends Fragment implements Waiting4JoinContract.View,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Waiting4JoinContract.Presenter mPresenter;
    private RadioGroup mRadioGroupTimer;
    private Spinner mSpinnerMinuteSelector;
    private TextView mTextMinute;

    private ImageButton mBtnBackStack;
    private Button mBtnCancel;

    public Waiting4JoinFragment() {
    }

    public static Waiting4JoinFragment newInstance() {
        return new Waiting4JoinFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setPresenter(Waiting4JoinContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_waiting4join_master, container, false);
        root.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        mBtnBackStack = root.findViewById(R.id.btn_waiting4join_back_arrow);
        mBtnCancel = root.findViewById(R.id.btn_waiting4join_cancel);
        mRadioGroupTimer = root.findViewById(R.id.radios_timer_selector);

        mSpinnerMinuteSelector = root.findViewById(R.id.spinner_timer_selector);
        mTextMinute = root.findViewById(R.id.text_timer_minutes);
        mSpinnerMinuteSelector.setVisibility(View.GONE);
        mTextMinute.setVisibility(View.GONE);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_waiting4join_back_arrow:
                mPresenter.finishWaiting4JoinUi();
                break;
            case R.id.btn_waiting4join_cancel:
                mPresenter.finishWaiting4JoinUi();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radios_timer_yes:
                mSpinnerMinuteSelector.setVisibility(View.VISIBLE);
                mTextMinute.setVisibility(View.VISIBLE);
                break;
            case R.id.radios_timer_no:
                mSpinnerMinuteSelector.setVisibility(View.GONE);
                mTextMinute.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnBackStack.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mRadioGroupTimer.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void showPlayingGameUi() {

    }

    @Override
    public boolean needTimer() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }



}
