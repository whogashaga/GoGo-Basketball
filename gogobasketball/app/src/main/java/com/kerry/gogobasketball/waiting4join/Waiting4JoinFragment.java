package com.kerry.gogobasketball.waiting4join;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.kerry.gogobasketball.R;

public class Waiting4JoinFragment extends Fragment implements Waiting4JoinContract.View,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Waiting4JoinContract.Presenter mPresenter;
    private RadioGroup mRaidoGroupTimer;
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
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_waiting4join, container, false);
        root.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        mBtnBackStack = root.findViewById(R.id.btn_waiting4join_back_arrow);
        mBtnCancel = root.findViewById(R.id.btn_waiting4join_cancel);
        mRaidoGroupTimer = root.findViewById(R.id.radios_timer_selector);

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
        mRaidoGroupTimer.setOnCheckedChangeListener(this);
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

    @Override
    public void setPresenter(Waiting4JoinContract.Presenter presenter) {

    }

}
