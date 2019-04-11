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

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinMasterFragment extends Fragment implements Waiting4JoinMasterContract.View,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    View mRoot;

    private Waiting4JoinMasterContract.Presenter mPresenter;
    private RadioGroup mRadioGroupTimer;
    private Spinner mSpinnerMinuteSelector;
    private TextView mTextMinute;
    private TextView mRoomName;

    private ImageButton mBtnBackStack;
    private Button mBtnCancel;
    private Button mBtnStartGame;

    private ImageView mAvatarP1, mGenderP1, mPositionP1;
    private TextView mTextIdP1;
    private Button mBtnSeatP1, mBtnInfoP1, mBtnAddFriendP1;

    private ImageView mAvatarP2, mGenderP2, mPositionP2;
    private TextView mTextIdP2;
    private Button mBtnSeatP2, mBtnInfoP2, mBtnAddFriendP2;

    private ImageView mAvatarP3, mGenderP3, mPositionP3;
    private TextView mTextIdP3;
    private Button mBtnSeatP3, mBtnInfoP3, mBtnAddFriendP3;

    private ImageView mAvatarP4, mGenderP4, mPositionP4;
    private TextView mTextIdP4;
    private Button mBtnSeatP4, mBtnInfoP4, mBtnAddFriendP4;

    private ImageView mAvatarP5, mGenderP5, mPositionP5;
    private TextView mTextIdP5;
    private Button mBtnSeatP5, mBtnInfoP5, mBtnAddFriendP5;

    private ImageView mAvatarP6, mGenderP6, mPositionP6;
    private TextView mTextIdP6;
    private Button mBtnSeatP6, mBtnInfoP6, mBtnAddFriendP6;

    private ImageView mAvatarP7, mGenderP7, mPositionP7;
    private TextView mTextIdP7;
    private Button mBtnSeatP7, mBtnInfoP7, mBtnAddFriendP7;

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
    public void getRoomInfoWhenCreate(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats waitingRoomSeats) {
        // for setting host info
        mWaitingRoomInfo = waitingRoomInfo;
        mHostSeatInfo = waitingRoomSeats;

        mRoomName.setText(waitingRoomInfo.getRoomName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_waiting4join_master, container, false);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        mRoomName = mRoot.findViewById(R.id.text_slave_waiting4join_room_name);
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

        mAvatarP2 = mRoot.findViewById(R.id.waiting_team_a_player2_avatar);
        mAvatarP2.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP2 = mRoot.findViewById(R.id.waiting_team_a_player2_gender);
        mPositionP2 = mRoot.findViewById(R.id.waiting_team_a_player2_position);
        mTextIdP2 = mRoot.findViewById(R.id.waiting_team_a_player2_id);
        mBtnSeatP2 = mRoot.findViewById(R.id.btn_waiting_team_a_player2_change_seat);
        mBtnInfoP2 = mRoot.findViewById(R.id.btn_waiting_team_a_player2_info);
        mBtnAddFriendP2 = mRoot.findViewById(R.id.btn_waiting_team_a_player2_add);

        mAvatarP3 = mRoot.findViewById(R.id.waiting_team_a_player3_avatar);
        mAvatarP3.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP3 = mRoot.findViewById(R.id.waiting_team_a_player3_gender);
        mPositionP3 = mRoot.findViewById(R.id.waiting_team_a_player3_position);
        mTextIdP3 = mRoot.findViewById(R.id.waiting_team_a_player3_id);
        mBtnSeatP3 = mRoot.findViewById(R.id.btn_waiting_team_a_player3_change_seat);
        mBtnInfoP3 = mRoot.findViewById(R.id.btn_waiting_team_a_player3_info);
        mBtnAddFriendP3 = mRoot.findViewById(R.id.btn_waiting_team_a_player3_add);

        mAvatarP4 = mRoot.findViewById(R.id.waiting_team_b_player1_avatar);
        mAvatarP4.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP4 = mRoot.findViewById(R.id.waiting_team_b_player1_gender);
        mPositionP4 = mRoot.findViewById(R.id.waiting_team_b_player1_position);
        mTextIdP4 = mRoot.findViewById(R.id.waiting_team_b_player1_id);
        mBtnSeatP4 = mRoot.findViewById(R.id.btn_waiting_team_b_player1_change_seat);
        mBtnInfoP4 = mRoot.findViewById(R.id.btn_waiting_team_b_player1_info);
        mBtnAddFriendP4 = mRoot.findViewById(R.id.btn_waiting_team_b_player1_add);

        mAvatarP5 = mRoot.findViewById(R.id.waiting_team_b_player2_avatar);
        mAvatarP5.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP5 = mRoot.findViewById(R.id.waiting_team_b_player2_gender);
        mPositionP5 = mRoot.findViewById(R.id.waiting_team_b_player2_position);
        mTextIdP5 = mRoot.findViewById(R.id.waiting_team_b_player2_id);
        mBtnSeatP5 = mRoot.findViewById(R.id.btn_waiting_team_b_player2_change_seat);
        mBtnInfoP5 = mRoot.findViewById(R.id.btn_waiting_team_b_player2_info);
        mBtnAddFriendP5 = mRoot.findViewById(R.id.btn_waiting_team_b_player2_add);

        mAvatarP6 = mRoot.findViewById(R.id.waiting_team_b_player3_avatar);
        mAvatarP6.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP6 = mRoot.findViewById(R.id.waiting_team_b_player3_gender);
        mPositionP6 = mRoot.findViewById(R.id.waiting_team_b_player3_position);
        mTextIdP6 = mRoot.findViewById(R.id.waiting_team_b_player3_id);
        mBtnSeatP6 = mRoot.findViewById(R.id.btn_waiting_team_b_player3_change_seat);
        mBtnInfoP6 = mRoot.findViewById(R.id.btn_waiting_team_b_player3_info);
        mBtnAddFriendP6 = mRoot.findViewById(R.id.btn_waiting_team_b_player3_add);

        mAvatarP7 = mRoot.findViewById(R.id.waiting_referee_avatar);
        mAvatarP7.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP7 = mRoot.findViewById(R.id.waiting_referee_gender);
        mPositionP7 = mRoot.findViewById(R.id.waiting_referee_position);
        mTextIdP7 = mRoot.findViewById(R.id.waiting_referee_id);
        mBtnSeatP7 = mRoot.findViewById(R.id.btn_waiting_referee_change_seat);
        mBtnInfoP7 = mRoot.findViewById(R.id.btn_waiting_referee_info);
        mBtnAddFriendP7 = mRoot.findViewById(R.id.btn_waiting_referee_add);

        return mRoot;
    }

    @Override
    public void showWaitingSeatsUi(ArrayList<WaitingRoomSeats> newSeatsList) {
        for (int i = 0; i < newSeatsList.size(); i++) {
            if (newSeatsList.get(i).getSort() == 1) {
                setUserSeatInfo(newSeatsList.get(i), 1, mAvatarP1, mGenderP1, mPositionP1, mTextIdP1);
            } else if (newSeatsList.get(i).getSort() == 2) {
                setUserSeatInfo(newSeatsList.get(i), 2, mAvatarP2, mGenderP2, mPositionP2, mTextIdP2);
            } else if (newSeatsList.get(i).getSort() == 3) {
                setUserSeatInfo(newSeatsList.get(i), 3, mAvatarP3, mGenderP3, mPositionP3, mTextIdP3);
            } else if (newSeatsList.get(i).getSort() == 4) {
                setUserSeatInfo(newSeatsList.get(i), 4, mAvatarP4, mGenderP4, mPositionP4, mTextIdP4);
            } else if (newSeatsList.get(i).getSort() == 5) {
                setUserSeatInfo(newSeatsList.get(i), 5, mAvatarP5, mGenderP5, mPositionP5, mTextIdP5);
            } else if (newSeatsList.get(i).getSort() == 6) {
                setUserSeatInfo(newSeatsList.get(i), 6, mAvatarP6, mGenderP6, mPositionP6, mTextIdP6);
            } else if (newSeatsList.get(i).getSort() == 7) {
                setUserSeatInfo(newSeatsList.get(i), 7, mAvatarP7, mGenderP7, mPositionP7, mTextIdP7);
            }
        }
    }

    private void setUserSeatInfo(WaitingRoomSeats seatPlayerInfo, int sort,
                                 ImageView avatar, ImageView gender,
                                 ImageView position, TextView id) {
        // set avatar
        ImageManager.getInstance().setImageByUrl(avatar, seatPlayerInfo.getAvatar());

        // set Gender
        if (seatPlayerInfo.getGender().equals("male")) {
            gender.setImageResource(R.drawable.ic_male);
        } else {
            gender.setImageResource(R.drawable.ic_female);
        }

        // set Position image
        setPositionImage(seatPlayerInfo, position);

        // set id
        id.setText(seatPlayerInfo.getId());

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
            Log.e("Kerry", "Set Position Image Error!!");
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
    public boolean needTimer() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }


}
