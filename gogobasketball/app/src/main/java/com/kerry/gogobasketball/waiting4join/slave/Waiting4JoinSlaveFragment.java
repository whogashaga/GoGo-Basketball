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
import com.kerry.gogobasketball.component.SeatAvatarOutlineProvider;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.ImageManager;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlaveFragment extends Fragment implements Waiting4JoinSlaveContract.View, View.OnClickListener {

    View mRoot;
//    private boolean mIsRoomExisted;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getProfileUserDataSlave(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.hideToolbarAndBottomNavigation();
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
        mRoot.getBackground().setAlpha(200);

        mTextRoomName = mRoot.findViewById(R.id.text_slave_waiting4join_room_name);
        mBtnBackStack = mRoot.findViewById(R.id.btn_slave_waiting4join_back_arrow);
        mBtnBackStack.setClickable(false);
        mBtnBackStack.setOnClickListener(this);
        mBtnCancel = mRoot.findViewById(R.id.btn_slave_waiting4join_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnCancel.setClickable(false);

        mRadioTimerOn = mRoot.findViewById(R.id.radio_slave_timer_yes);
        mRadioTimerOn.setClickable(false);
        mRadioTimerOff = mRoot.findViewById(R.id.radio_slave_timer_no);
        mRadioTimerOff.setClickable(false);
        mRadioGroupTimer = mRoot.findViewById(R.id.radio_slave_timer_selector);
//        mRadioGroupTimer.setOnCheckedChangeListener(this);

        mAvatarP1 = mRoot.findViewById(R.id.slave_waiting_team_a_player1_avatar);
        mAvatarP1.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP1 = mRoot.findViewById(R.id.slave_waiting_team_a_player1_gender);
        mPositionP1 = mRoot.findViewById(R.id.slave_waiting_team_a_player1_position);
        mTextIdP1 = mRoot.findViewById(R.id.slave_waiting_team_a_player1_id);
        mBtnSeatP1 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player1_change_seat);
        mBtnSeatP1.setOnClickListener(this);
        mBtnInfoP1 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player1_info);
        mBtnAddFriendP1 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player1_add);

        mAvatarP2 = mRoot.findViewById(R.id.slave_waiting_team_a_player2_avatar);
        mAvatarP2.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP2 = mRoot.findViewById(R.id.slave_waiting_team_a_player2_gender);
        mPositionP2 = mRoot.findViewById(R.id.slave_waiting_team_a_player2_position);
        mTextIdP2 = mRoot.findViewById(R.id.slave_waiting_team_a_player2_id);
        mBtnSeatP2 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player2_change_seat);
        mBtnSeatP2.setOnClickListener(this);
        mBtnInfoP2 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player2_info);
        mBtnAddFriendP2 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player2_add);

        mAvatarP3 = mRoot.findViewById(R.id.slave_waiting_team_a_player3_avatar);
        mAvatarP3.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP3 = mRoot.findViewById(R.id.slave_waiting_team_a_player3_gender);
        mPositionP3 = mRoot.findViewById(R.id.slave_waiting_team_a_player3_position);
        mTextIdP3 = mRoot.findViewById(R.id.slave_waiting_team_a_player3_id);
        mBtnSeatP3 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player3_change_seat);
        mBtnSeatP3.setOnClickListener(this);
        mBtnInfoP3 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player3_info);
        mBtnAddFriendP3 = mRoot.findViewById(R.id.btn_slave_waiting_team_a_player3_add);

        mAvatarP4 = mRoot.findViewById(R.id.slave_waiting_team_b_player1_avatar);
        mAvatarP4.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP4 = mRoot.findViewById(R.id.slave_waiting_team_b_player1_gender);
        mPositionP4 = mRoot.findViewById(R.id.slave_waiting_team_b_player1_position);
        mTextIdP4 = mRoot.findViewById(R.id.slave_waiting_team_b_player1_id);
        mBtnSeatP4 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player1_change_seat);
        mBtnSeatP4.setOnClickListener(this);
        mBtnInfoP4 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player1_info);
        mBtnAddFriendP4 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player1_add);

        mAvatarP5 = mRoot.findViewById(R.id.slave_waiting_team_b_player2_avatar);
        mAvatarP5.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP5 = mRoot.findViewById(R.id.slave_waiting_team_b_player2_gender);
        mPositionP5 = mRoot.findViewById(R.id.slave_waiting_team_b_player2_position);
        mTextIdP5 = mRoot.findViewById(R.id.slave_waiting_team_b_player2_id);
        mBtnSeatP5 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player2_change_seat);
        mBtnSeatP5.setOnClickListener(this);
        mBtnInfoP5 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player2_info);
        mBtnAddFriendP5 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player2_add);

        mAvatarP6 = mRoot.findViewById(R.id.slave_waiting_team_b_player3_avatar);
        mAvatarP6.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP6 = mRoot.findViewById(R.id.slave_waiting_team_b_player3_gender);
        mPositionP6 = mRoot.findViewById(R.id.slave_waiting_team_b_player3_position);
        mTextIdP6 = mRoot.findViewById(R.id.slave_waiting_team_b_player3_id);
        mBtnSeatP6 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player3_change_seat);
        mBtnSeatP6.setOnClickListener(this);
        mBtnInfoP6 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player3_info);
        mBtnAddFriendP6 = mRoot.findViewById(R.id.btn_slave_waiting_team_b_player3_add);

        mAvatarP7 = mRoot.findViewById(R.id.slave_waiting_referee_avatar);
        mAvatarP7.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP7 = mRoot.findViewById(R.id.slave_waiting_referee_gender);
        mPositionP7 = mRoot.findViewById(R.id.slave_waiting_referee_position);
        mTextIdP7 = mRoot.findViewById(R.id.slave_waiting_referee_id);
        mBtnSeatP7 = mRoot.findViewById(R.id.btn_slave_waiting_referee_change_seat);
        mBtnSeatP7.setOnClickListener(this);
        mBtnInfoP7 = mRoot.findViewById(R.id.btn_slave_waiting_referee_info);
        mBtnAddFriendP7 = mRoot.findViewById(R.id.btn_slave_waiting_referee_add);

        return mRoot;
    }

    @Override
    public void showRoomName(WaitingRoomInfo waitingRoomInfo) {
        mTextRoomName.setText(waitingRoomInfo.getRoomName());
    }

    @Override
    public void showWaitingSeatsSlaveUi(ArrayList<WaitingRoomSeats> newSeatsList) {
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                setUserSeatInfo(newSeatsList.get(i), 1, mAvatarP1, mGenderP1, mPositionP1, mTextIdP1, mBtnSeatP1);
            } else if (i == 1) {
                setUserSeatInfo(newSeatsList.get(i), 2, mAvatarP2, mGenderP2, mPositionP2, mTextIdP2, mBtnSeatP2);
            } else if (i == 2) {
                setUserSeatInfo(newSeatsList.get(i), 3, mAvatarP3, mGenderP3, mPositionP3, mTextIdP3, mBtnSeatP3);
            } else if (i == 3) {
                setUserSeatInfo(newSeatsList.get(i), 4, mAvatarP4, mGenderP4, mPositionP4, mTextIdP4, mBtnSeatP4);
            } else if (i == 4) {
                setUserSeatInfo(newSeatsList.get(i), 5, mAvatarP5, mGenderP5, mPositionP5, mTextIdP5, mBtnSeatP5);
            } else if (i == 5) {
                setUserSeatInfo(newSeatsList.get(i), 6, mAvatarP6, mGenderP6, mPositionP6, mTextIdP6, mBtnSeatP6);
            } else if (i == 6) {
                setUserSeatInfo(newSeatsList.get(i), 7, mAvatarP7, mGenderP7, mPositionP7, mTextIdP7, mBtnSeatP7);
            } else {
                Log.d(Constants.TAG, "showWaitingSeatsSlaveUi Error!!");
            }
        }
    }

    private void setUserSeatInfo(WaitingRoomSeats seatPlayerInfo, int sort,
                                 ImageView avatar, ImageView gender,
                                 ImageView position, TextView id, Button btnChangeSeat) {
        // set avatar
        if (seatPlayerInfo.getAvatar().equals("")) {
            avatar.setImageResource(R.drawable.ic_nav_profile);
        } else {
            ImageManager.getInstance().setImageByUrl(avatar, seatPlayerInfo.getAvatar());
        }

        // set Gender
        if (seatPlayerInfo.getGender().equals("")) {
            gender.setVisibility(View.INVISIBLE);
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
            position.setVisibility(View.INVISIBLE);
        } else {
            if (sort == 7) {
                position.setVisibility(View.VISIBLE);
                mPositionP7.setImageResource(R.drawable.ic_position_referee);
            } else {
                position.setVisibility(View.VISIBLE);
                setPositionImage(seatPlayerInfo, position);
            }
        }

        // set id
        if (seatPlayerInfo.getId().equals("")) {
            if(sort == 7){
                id.setText("Referee");
            } else {
                id.setText("Player" + String.valueOf(sort));
            }
        } else {
            id.setText(seatPlayerInfo.getId());
        }

        // set btn change seat
        if (seatPlayerInfo.isSeatAvailable()) {
            btnChangeSeat.setVisibility(View.VISIBLE);
        } else {
            btnChangeSeat.setVisibility(View.GONE);
        }

    }

    public void setPositionImage(WaitingRoomSeats waitPlayerInfo, ImageView imageView) {

        if (waitPlayerInfo.getPosition().equals(Constants.POSITION_PG)) {
            imageView.setImageResource(R.drawable.ic_position_pg);
        } else if (waitPlayerInfo.getPosition().equals(Constants.POSITION_SG)) {
            imageView.setImageResource(R.drawable.ic_position_sg);
        } else if (waitPlayerInfo.getPosition().equals(Constants.POSITION_SF)) {
            imageView.setImageResource(R.drawable.ic_position_sf);
        } else if (waitPlayerInfo.getPosition().equals(Constants.POSITION_PF)) {
            imageView.setImageResource(R.drawable.ic_position_pf);
        } else if (waitPlayerInfo.getPosition().equals(Constants.POSITION_CENTER)) {
            imageView.setImageResource(R.drawable.ic_position_center);
        } else if (waitPlayerInfo.getPosition().equals("r")) {
            imageView.setImageResource(R.drawable.ic_position_referee);
        } else {
            Log.e(Constants.TAG, "Set Position Error!!");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_slave_waiting4join_back_arrow:
                mPresenter.removeListenerSlave();
                mPresenter.finishWaiting4JoinSlaveUi();
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mPresenter.showToolbarAndBottomNavigation();
                mBtnBackStack.setClickable(false);
                break;
            case R.id.btn_slave_waiting4join_cancel:
                mPresenter.removeListenerSlave();
                mPresenter.finishWaiting4JoinSlaveUi();
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mPresenter.showToolbarAndBottomNavigation();
                mBtnCancel.setClickable(false);
                break;
            case R.id.btn_slave_waiting_team_a_player1_change_seat:
                Log.d(Constants.TAG, "Joiner onClick seat1");
                mPresenter.changeSlave2NewSeat(1);
                break;
            case R.id.btn_slave_waiting_team_a_player2_change_seat:
                Log.d(Constants.TAG, "Joiner onClick seat2");
                mPresenter.changeSlave2NewSeat(2);
                break;
            case R.id.btn_slave_waiting_team_a_player3_change_seat:
                Log.d(Constants.TAG, "Joiner onClick seat3");
                mPresenter.changeSlave2NewSeat(3);
                break;
            case R.id.btn_slave_waiting_team_b_player1_change_seat:
                Log.d(Constants.TAG, "Joiner onClick seat4");
                mPresenter.changeSlave2NewSeat(4);
                break;
            case R.id.btn_slave_waiting_team_b_player2_change_seat:
                Log.d(Constants.TAG, "Joiner onClick seat5");
                mPresenter.changeSlave2NewSeat(5);
                break;
            case R.id.btn_slave_waiting_team_b_player3_change_seat:
                Log.d(Constants.TAG, "Joiner onClick seat6");
                mPresenter.changeSlave2NewSeat(6);
                break;
            case R.id.btn_slave_waiting_referee_change_seat:
                Log.d(Constants.TAG, "Joiner onClick seat7");
                mPresenter.changeSlave2NewSeat(7);
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setBackKeyDisable(true);
        mPresenter.hideToolbarAndBottomNavigation();
        mPresenter.setActivityBackgroundLandScape();

        mGenderP1.setVisibility(View.INVISIBLE);
        mPositionP1.setVisibility(View.INVISIBLE);
        mGenderP2.setVisibility(View.INVISIBLE);
        mPositionP2.setVisibility(View.INVISIBLE);
        mGenderP3.setVisibility(View.INVISIBLE);
        mPositionP3.setVisibility(View.INVISIBLE);
        mGenderP4.setVisibility(View.INVISIBLE);
        mPositionP4.setVisibility(View.INVISIBLE);
        mGenderP5.setVisibility(View.INVISIBLE);
        mPositionP5.setVisibility(View.INVISIBLE);
        mGenderP6.setVisibility(View.INVISIBLE);
        mPositionP6.setVisibility(View.INVISIBLE);
        mGenderP7.setVisibility(View.INVISIBLE);
        mPositionP7.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.setBackKeyDisable(false);
        mPresenter.deleteSeatsInfoWhenLeaveRoom();
    }

    @Override
    public void closeSlaveUiBecauseMasterOutFirst() {
        mPresenter.showErrorToast("房主落跑...", false);
        mPresenter.finishWaiting4JoinSlaveUi();
        mPresenter.showToolbarAndBottomNavigation();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        mPresenter.deleteSeatsInfoWhenLeaveRoom();
    }

    @Override
    public void openPlayerGamingUi(String hostName, int nowSort) {
        mPresenter.deleteSeatsInfoWhenLeaveRoom();
        mPresenter.openGamePlayingOfPlayer(hostName, nowSort);
    }

    @Override
    public void openRefereeGamingUi(String hostName) {
        mPresenter.deleteSeatsInfoWhenLeaveRoom();
        mPresenter.openGamePlayingOfReferee(hostName);
    }

    @Override
    public void setBackBtnClickable() {
        mBtnCancel.setClickable(true);
        mBtnBackStack.setClickable(true);
    }


    @Override
    public boolean isActive() {
        return false;
    }


}
