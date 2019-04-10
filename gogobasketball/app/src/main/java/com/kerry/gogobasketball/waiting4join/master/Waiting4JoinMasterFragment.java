package com.kerry.gogobasketball.waiting4join.master;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.ProfileAvatarOutlineProvider;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.ImageManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinMasterFragment extends Fragment implements Waiting4JoinMasterContract.View,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    View mRoot;

    private Waiting4JoinMasterContract.Presenter mPresenter;
    private RadioGroup mRadioGroupTimer;
    private Spinner mSpinnerMinuteSelector;
    private TextView mTextMinute;

    private ImageButton mBtnBackStack;
    private Button mBtnCancel;
    private Button mBtnStartGame;

    private ImageView mAvatarP1, mGenderP1, mPositionP1;
    private TextView mTextIdP1;
    private Button mBtnSeatP1, mBtnInfoP1, mBtnAddFriendP1;

    private WaitingRoomInfo mWaitingRoomInfo;
    private WaitingRoomSeats mHostSeatInfo;

    public Waiting4JoinMasterFragment() {
        mWaitingRoomInfo = new WaitingRoomInfo();
    }

    public static Waiting4JoinMasterFragment newInstance() {
        return new Waiting4JoinMasterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setPresenter(Waiting4JoinMasterContract.Presenter presenter) {
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

    @Override
    public void getRoomInfoFromPresenterMaster(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats waitingRoomSeats) {
        // for setting host info
        mWaitingRoomInfo = waitingRoomInfo;
        mHostSeatInfo = waitingRoomSeats;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_waiting4join_master, container, false);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        mBtnBackStack = mRoot.findViewById(R.id.btn_waiting4join_back_arrow);
        mBtnBackStack.setOnClickListener(this);
        mBtnCancel = mRoot.findViewById(R.id.btn_waiting4join_cancel);
        mBtnCancel.setOnClickListener(this);
        mRadioGroupTimer = mRoot.findViewById(R.id.radios_timer_selector);
        mRadioGroupTimer.setOnCheckedChangeListener(this);
        mBtnStartGame = mRoot.findViewById(R.id.btn_waiting4join_start);
        mBtnStartGame.setOnClickListener(this);

        mSpinnerMinuteSelector = mRoot.findViewById(R.id.spinner_timer_selector);
        mTextMinute = mRoot.findViewById(R.id.text_timer_minutes);
        mSpinnerMinuteSelector.setVisibility(View.GONE);
        mTextMinute.setVisibility(View.GONE);

        mAvatarP1 = mRoot.findViewById(R.id.waiting_team_a_player1_avatar);
        mAvatarP1.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP1 = mRoot.findViewById(R.id.waiting_team_a_player1_gender);
        mPositionP1 = mRoot.findViewById(R.id.waiting_team_a_player1_position);
        mTextIdP1 = mRoot.findViewById(R.id.waiting_team_a_player1_id);

        return mRoot;
    }

    public void setUserHost2TheSeat(WaitingRoomSeats seatPlayerInfo, int sort) {

        // set avatar
        ImageManager.getInstance().setImageByUrl(mAvatarP1, seatPlayerInfo.getAvatar());

        // set Gender
        if (mHostSeatInfo.getGender().equals("male")) {
            mGenderP1.setImageResource(R.drawable.ic_male);
        } else {
            mGenderP1.setImageResource(R.drawable.ic_female);
        }

        // set Position image
        setPositionImage(seatPlayerInfo, mPositionP1);

        // set id
        mTextIdP1.setText(seatPlayerInfo.getId());

    }

    public void setPositionImage(WaitingRoomSeats waitPlayerInfo,ImageView imageView) {

        if (waitPlayerInfo.getPosition().equals("pg")) {
            imageView.setImageResource(R.drawable.ic_position_pg);
        } else if (waitPlayerInfo.getPosition().equals("sg")) {
            imageView.setImageResource(R.drawable.ic_position_sg);
        } else if (waitPlayerInfo.getPosition().equals("sf")) {
            imageView.setImageResource(R.drawable.ic_position_sf);
        } else if (waitPlayerInfo.getPosition().equals("pf")) {
            imageView.setImageResource(R.drawable.ic_position_pf);
        } else if (waitPlayerInfo.getPosition().equals("c")) {
            imageView.setImageResource(R.drawable.ic_position_center);
        } else {
            Log.e("Kerry", "Set Position Error!!");
        }
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
            case R.id.btn_waiting4join_start:
                mPresenter.openGamePlayingOfReferee();
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
                mBtnStartGame.setBackgroundResource(R.drawable.ic_start_unclick);
                mBtnStartGame.setClickable(false);
                break;
            case R.id.radios_timer_no:
                mSpinnerMinuteSelector.setVisibility(View.GONE);
                mTextMinute.setVisibility(View.GONE);
                mBtnStartGame.setBackgroundResource(R.drawable.button_effect_start);
                mBtnStartGame.setClickable(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUserHost2TheSeat(mHostSeatInfo,0);

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.deleteRoomWhenLeaveRoom();
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
