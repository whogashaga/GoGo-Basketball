package com.kerry.gogobasketball.waiting4join.slave;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.ProfileAvatarOutlineProvider;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.ImageManager;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlaveFragment extends Fragment implements Waiting4JoinSlaveContract.View, View.OnClickListener {

    View mRoot;

    private Waiting4JoinSlaveContract.Presenter mPresenter;
    private RadioGroup mRadioGroupTimer;
    private RadioButton mRadioTimerOn;
    private RadioButton mRadioTimerOff;
    private ImageButton mBtnBackStack;
    private Button mBtnCancel;
    private TextView mTextRoomName;
    private WaitingRoomInfo mWaitingRoomInfoForFirstTime;

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

    public Waiting4JoinSlaveFragment() {
        mWaitingRoomInfoForFirstTime = new WaitingRoomInfo();
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

        mTextRoomName = mRoot.findViewById(R.id.text_slave_waiting4join_room_name);

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

        mAvatarP1 = mRoot.findViewById(R.id.slave_waiting_team_a_player1_avatar);
        mAvatarP1.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP1 = mRoot.findViewById(R.id.slave_waiting_team_a_player1_gender);
        mPositionP1 = mRoot.findViewById(R.id.slave_waiting_team_a_player1_position);
        mTextIdP1 = mRoot.findViewById(R.id.slave_waiting_team_a_player1_id);
        mBtnSeatP1 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player1_change_seat);
        mBtnInfoP1 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player1_info);
        mBtnAddFriendP1 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player1_add);

        mAvatarP2 = mRoot.findViewById(R.id.slave_waiting_team_a_player2_avatar);
        mAvatarP2.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP2 = mRoot.findViewById(R.id.slave_waiting_team_a_player2_gender);
        mPositionP2 = mRoot.findViewById(R.id.slave_waiting_team_a_player2_position);
        mTextIdP2 = mRoot.findViewById(R.id.slave_waiting_team_a_player2_id);
        mBtnSeatP2 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player2_change_seat);
        mBtnInfoP2 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player2_info);
        mBtnAddFriendP2 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player2_add);

        mAvatarP3 = mRoot.findViewById(R.id.slave_waiting_team_a_player3_avatar);
        mAvatarP3.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP3 = mRoot.findViewById(R.id.slave_waiting_team_a_player3_gender);
        mPositionP3 = mRoot.findViewById(R.id.slave_waiting_team_a_player3_position);
        mTextIdP3 = mRoot.findViewById(R.id.slave_waiting_team_a_player3_id);
        mBtnSeatP3 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player3_change_seat);
        mBtnInfoP3 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player3_info);
        mBtnAddFriendP3 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player3_add);

        mAvatarP4 = mRoot.findViewById(R.id.slave_waiting_team_b_player1_avatar);
        mAvatarP4.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP4 = mRoot.findViewById(R.id.slave_waiting_team_b_player1_gender);
        mPositionP4 = mRoot.findViewById(R.id.slave_waiting_team_b_player1_position);
        mTextIdP4 = mRoot.findViewById(R.id.slave_waiting_team_b_player1_id);
        mBtnSeatP4 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player1_change_seat);
        mBtnInfoP4 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player1_info);
        mBtnAddFriendP4 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player1_add);

        mAvatarP5 = mRoot.findViewById(R.id.slave_waiting_team_b_player2_avatar);
        mAvatarP5.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP5 = mRoot.findViewById(R.id.slave_waiting_team_b_player2_gender);
        mPositionP5 = mRoot.findViewById(R.id.slave_waiting_team_b_player2_position);
        mTextIdP5 = mRoot.findViewById(R.id.slave_waiting_team_b_player2_id);
        mBtnSeatP5 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player2_change_seat);
        mBtnInfoP5 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player2_info);
        mBtnAddFriendP5 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player2_add);

        mAvatarP6 = mRoot.findViewById(R.id.slave_waiting_team_b_player3_avatar);
        mAvatarP6.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP6 = mRoot.findViewById(R.id.slave_waiting_team_b_player3_gender);
        mPositionP6 = mRoot.findViewById(R.id.slave_waiting_team_b_player3_position);
        mTextIdP6 = mRoot.findViewById(R.id.slave_waiting_team_b_player3_id);
        mBtnSeatP6 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player3_change_seat);
        mBtnInfoP6 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player3_info);
        mBtnAddFriendP6 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player3_add);

        mAvatarP7 = mRoot.findViewById(R.id.slave_waiting_referee_avatar);
        mAvatarP7.setOutlineProvider(new ProfileAvatarOutlineProvider());
        mGenderP7 = mRoot.findViewById(R.id.slave_waiting_referee_gender);
        mPositionP7 = mRoot.findViewById(R.id.slave_waiting_referee_position);
        mTextIdP7 = mRoot.findViewById(R.id.slave_waiting_referee_id);
        mBtnSeatP7 = mRoot.findViewById(R.id.btn_slave_waiting_referee_change_seat);
        mBtnInfoP7 = mRoot.findViewById(R.id.btn_slave_waiting_referee_info);
        mBtnAddFriendP7 = mRoot.findViewById(R.id.btn_slave_waiting_referee_add);

        return mRoot;
    }

    @Override
    public void showRoomName(WaitingRoomInfo waitingRoomInfo) {
        mTextRoomName.setText(waitingRoomInfo.getRoomName());
    }

    @Override
    public void showWaiting4JoinSlaveUi(ArrayList<WaitingRoomSeats> newSeatsList) {
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                setUserSeatInfo(newSeatsList.get(i), 1, mAvatarP1, mGenderP1, mPositionP1, mTextIdP1);
            } else if (i == 1) {
                setUserSeatInfo(newSeatsList.get(i), 2, mAvatarP2, mGenderP2, mPositionP2, mTextIdP2);
            } else if (i == 2) {
                setUserSeatInfo(newSeatsList.get(i), 3, mAvatarP3, mGenderP3, mPositionP3, mTextIdP3);
            } else if (i == 3) {
                setUserSeatInfo(newSeatsList.get(i), 4, mAvatarP4, mGenderP4, mPositionP4, mTextIdP4);
            } else if (i == 4) {
                setUserSeatInfo(newSeatsList.get(i), 5, mAvatarP5, mGenderP5, mPositionP5, mTextIdP5);
            } else if (i == 5) {
                setUserSeatInfo(newSeatsList.get(i), 6, mAvatarP6, mGenderP6, mPositionP6, mTextIdP6);
            } else if (i == 6) {
                setUserSeatInfo(newSeatsList.get(i), 7, mAvatarP7, mGenderP7, mPositionP7, mTextIdP7);
            } else {
                Log.d("Kerry","showWaiting4JoinSlaveUi Error!!");
            }
        }
    }

    private void setUserSeatInfo(WaitingRoomSeats seatPlayerInfo, int sort,
                                 ImageView avatar, ImageView gender,
                                 ImageView position, TextView id) {
        // set avatar
        if (seatPlayerInfo.getAvatar().equals("")) {
            avatar.setImageResource(R.drawable.ic_user_avatar);
        } else {
            ImageManager.getInstance().setImageByUrl(avatar, seatPlayerInfo.getAvatar());
        }

        // set Gender
        if (seatPlayerInfo.getGender().equals("")) {
            gender.setVisibility(View.GONE);
        } else {
            gender.setVisibility(View.VISIBLE);
            if (seatPlayerInfo.getGender().equals("male")) {
                gender.setImageResource(R.drawable.ic_male);
            } else {
                gender.setImageResource(R.drawable.ic_female);
            }
        }

        // set Position image
        if (seatPlayerInfo.getPosition().equals("")) {
            position.setVisibility(View.GONE);
        } else {
            position.setVisibility(View.VISIBLE);
            setPositionImage(seatPlayerInfo, position);
        }

        // set id
        if (seatPlayerInfo.getId().equals("")) {
            id.setText("Player" + String.valueOf(sort));
        } else {
            id.setText(seatPlayerInfo.getId());
        }

    }

    public void setPositionImage(WaitingRoomSeats waitPlayerInfo, ImageView imageView) {

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
        } else if (waitPlayerInfo.getPosition().equals("r")) {
            imageView.setImageResource(R.drawable.ic_position_referee);
        } else {
            Log.e("Kerry", "Set Position Error!!");
        }
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

        mGenderP1.setVisibility(View.GONE);
        mPositionP1.setVisibility(View.GONE);
        mGenderP2.setVisibility(View.GONE);
        mPositionP2.setVisibility(View.GONE);
        mGenderP3.setVisibility(View.GONE);
        mPositionP3.setVisibility(View.GONE);
        mGenderP4.setVisibility(View.GONE);
        mPositionP4.setVisibility(View.GONE);
        mGenderP5.setVisibility(View.GONE);
        mPositionP5.setVisibility(View.GONE);
        mGenderP6.setVisibility(View.GONE);
        mPositionP6.setVisibility(View.GONE);
        mGenderP7.setVisibility(View.GONE);
        mPositionP7.setVisibility(View.GONE);
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
        mPresenter.deleteSeatInfoWhenLeaveRoom();
    }

    @Override
    public boolean isActive() {
        return false;
    }


}
