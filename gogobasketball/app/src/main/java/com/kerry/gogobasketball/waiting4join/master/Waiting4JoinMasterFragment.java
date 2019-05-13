package com.kerry.gogobasketball.waiting4join.master;

import static com.google.common.base.Preconditions.checkNotNull;

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
import com.kerry.gogobasketball.component.SeatAvatarOutlineProvider;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.ImageManager;

import java.util.ArrayList;

public class Waiting4JoinMasterFragment extends Fragment implements Waiting4JoinMasterContract.View,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    View mRoot;

    private Waiting4JoinMasterContract.Presenter mPresenter;
    private RadioGroup mRadioGroupTimer;
    private Spinner mSpinnerMinuteSelector;
    private TextView mTextMinute;
    private TextView mRoomName;

    private ImageButton mBtnBackStack, mBtnInstruction, mBtnChat;
    private Button mBtnCancel;
    private Button mBtnStartGame;

    private ImageView mAvatarP1, mGenderP1, mPositionP1;
    private TextView mTextIdP1;
    private Button mBtnSeatP1, mBtnInfoP1, mBtnDeleteP1;

    private ImageView mAvatarP2, mGenderP2, mPositionP2;
    private TextView mTextIdP2;
    private Button mBtnSeatP2, mBtnInfoP2, mBtnDeleteP2;

    private ImageView mAvatarP3, mGenderP3, mPositionP3;
    private TextView mTextIdP3;
    private Button mBtnSeatP3, mBtnInfoP3, mBtnDeleteP3;

    private ImageView mAvatarP4, mGenderP4, mPositionP4;
    private TextView mTextIdP4;
    private Button mBtnSeatP4, mBtnInfoP4, mBtnDeleteP4;

    private ImageView mAvatarP5, mGenderP5, mPositionP5;
    private TextView mTextIdP5;
    private Button mBtnSeatP5, mBtnInfoP5, mBtnDeleteP5;

    private ImageView mAvatarP6, mGenderP6, mPositionP6;
    private TextView mTextIdP6;
    private Button mBtnSeatP6, mBtnInfoP6, mBtnDeleteP6;

    private ImageView mAvatarP7, mGenderP7, mPositionP7;
    private TextView mTextIdP7;
    private Button mBtnSeatP7, mBtnInfoP7, mBtnDeleteP7;

    private View mSeatP1, mSeatP2, mSeatP3;
    private View mSeatP4, mSeatP5, mSeatP6, mSeatP7;

    private WaitingRoomInfo mWaitingRoomInfo;
    private int mCurrentGamerAmount;
    private int mNowMasterSort;
    private int mNewSortForDeleteBtn;
    private ArrayList<WaitingRoomSeats> mSeatsList;

    public Waiting4JoinMasterFragment() {
        mWaitingRoomInfo = new WaitingRoomInfo();
        mCurrentGamerAmount = -1;
        mNowMasterSort = -1;
        mNewSortForDeleteBtn = 1;
        mSeatsList = new ArrayList<>();
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
        Log.d(Constants.TAG, " Waiting4JoinMasterFragment onResume: ");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo) {
        // for setting host info
        mWaitingRoomInfo = waitingRoomInfo;
    }

    @Override
    public void openGamingUi(GamingRoomInfo gamingRoomInfo) {
        if (mNowMasterSort == 7) {
            mPresenter.openGamePlayingOfReferee(gamingRoomInfo.getHostName());
            mPresenter.deleteHostSeatWhenLeave();
        } else {
            mPresenter.openGamePlayingOfPlayer(gamingRoomInfo.getHostName(), mNowMasterSort);
            mPresenter.deleteHostSeatWhenLeave();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "Waiting4Join fragment onDestroy !!");
        if (mPresenter != null) {
            mPresenter.deleteHostSeatWhenLeave();
            mPresenter.setBackKeyDisable(false);
            mPresenter.removeListenerMaster();
        }
    }

    @Override
    public void openUserDetailUi(String userId) {
        mPresenter.openUserDetailDialog(userId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_waiting4join_master, container, false);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        mRoot.getBackground().setAlpha(200);

        mRoomName = mRoot.findViewById(R.id.text_waiting4join_room_name);
        mBtnInstruction = mRoot.findViewById(R.id.btn_waiting4join_instruction);
        mBtnInstruction.setOnClickListener(this);

        mBtnChat = mRoot.findViewById(R.id.btn_waiting4join_message);
        mBtnChat.setOnClickListener(this);

        mBtnBackStack = mRoot.findViewById(R.id.btn_waiting4join_back_arrow);
        mBtnBackStack.setOnClickListener(this);
        mBtnBackStack.setClickable(false);
        mBtnCancel = mRoot.findViewById(R.id.btn_waiting4join_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnCancel.setClickable(false);
        mRadioGroupTimer = mRoot.findViewById(R.id.radios_timer_selector);
        mRadioGroupTimer.setOnCheckedChangeListener(this);
        mBtnStartGame = mRoot.findViewById(R.id.btn_waiting4join_start);
        mBtnStartGame.setOnClickListener(this);

        mSpinnerMinuteSelector = mRoot.findViewById(R.id.spinner_timer_selector);
        mTextMinute = mRoot.findViewById(R.id.text_timer_minutes);
        mSpinnerMinuteSelector.setVisibility(View.INVISIBLE);

        mAvatarP1 = mRoot.findViewById(R.id.waiting_team_a_player1_avatar);
        mAvatarP1.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP1 = mRoot.findViewById(R.id.waiting_team_a_player1_gender);
        mPositionP1 = mRoot.findViewById(R.id.waiting_team_a_player1_position);
        mTextIdP1 = mRoot.findViewById(R.id.waiting_team_a_player1_id);
        mBtnSeatP1 = mRoot.findViewById(R.id.btn_waiting_team_a_player1_change_seat);
        mBtnSeatP1.setOnClickListener(this);
        mSeatP1 = mRoot.findViewById(R.id.waiting_team_a_player1_seat);
        mSeatP1.getBackground().setAlpha(230);
        mBtnInfoP1 = mRoot.findViewById(R.id.btn_waiting_team_a_player1_info);
        mBtnInfoP1.setOnClickListener(this);
        mBtnDeleteP1 = mRoot.findViewById(R.id.btn_waiting_team_a_player1_delete);
        mBtnDeleteP1.setOnClickListener(this);

        mAvatarP2 = mRoot.findViewById(R.id.waiting_team_a_player2_avatar);
        mAvatarP2.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP2 = mRoot.findViewById(R.id.waiting_team_a_player2_gender);
        mPositionP2 = mRoot.findViewById(R.id.waiting_team_a_player2_position);
        mTextIdP2 = mRoot.findViewById(R.id.waiting_team_a_player2_id);
        mBtnSeatP2 = mRoot.findViewById(R.id.btn_waiting_team_a_player2_change_seat);
        mBtnSeatP2.setOnClickListener(this);
        mSeatP2 = mRoot.findViewById(R.id.waiting_team_a_player2_seat);
        mSeatP2.getBackground().setAlpha(230);
        mBtnInfoP2 = mRoot.findViewById(R.id.btn_waiting_team_a_player2_info);
        mBtnInfoP2.setOnClickListener(this);
        mBtnDeleteP2 = mRoot.findViewById(R.id.btn_waiting_team_a_player2_delete);
        mBtnDeleteP2.setOnClickListener(this);

        mAvatarP3 = mRoot.findViewById(R.id.waiting_team_a_player3_avatar);
        mAvatarP3.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP3 = mRoot.findViewById(R.id.waiting_team_a_player3_gender);
        mPositionP3 = mRoot.findViewById(R.id.waiting_team_a_player3_position);
        mTextIdP3 = mRoot.findViewById(R.id.waiting_team_a_player3_id);
        mBtnSeatP3 = mRoot.findViewById(R.id.btn_waiting_team_a_player3_change_seat);
        mBtnSeatP3.setOnClickListener(this);
        mSeatP3 = mRoot.findViewById(R.id.waiting_team_a_player3_seat);
        mSeatP3.getBackground().setAlpha(230);
        mBtnInfoP3 = mRoot.findViewById(R.id.btn_waiting_team_a_player3_info);
        mBtnInfoP3.setOnClickListener(this);
        mBtnDeleteP3 = mRoot.findViewById(R.id.btn_waiting_team_a_player3_delete);
        mBtnDeleteP3.setOnClickListener(this);


        mAvatarP4 = mRoot.findViewById(R.id.waiting_team_b_player1_avatar);
        mAvatarP4.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP4 = mRoot.findViewById(R.id.waiting_team_b_player1_gender);
        mPositionP4 = mRoot.findViewById(R.id.waiting_team_b_player1_position);
        mTextIdP4 = mRoot.findViewById(R.id.waiting_team_b_player1_id);
        mBtnSeatP4 = mRoot.findViewById(R.id.btn_waiting_team_b_player1_change_seat);
        mBtnSeatP4.setOnClickListener(this);
        mSeatP4 = mRoot.findViewById(R.id.waiting_team_b_player1_seat);
        mSeatP4.getBackground().setAlpha(230);
        mBtnInfoP4 = mRoot.findViewById(R.id.btn_waiting_team_b_player1_info);
        mBtnInfoP4.setOnClickListener(this);
        mBtnDeleteP4 = mRoot.findViewById(R.id.btn_waiting_team_b_player1_delete);
        mBtnDeleteP4.setOnClickListener(this);


        mAvatarP5 = mRoot.findViewById(R.id.waiting_team_b_player2_avatar);
        mAvatarP5.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP5 = mRoot.findViewById(R.id.waiting_team_b_player2_gender);
        mPositionP5 = mRoot.findViewById(R.id.waiting_team_b_player2_position);
        mTextIdP5 = mRoot.findViewById(R.id.waiting_team_b_player2_id);
        mBtnSeatP5 = mRoot.findViewById(R.id.btn_waiting_team_b_player2_change_seat);
        mBtnSeatP5.setOnClickListener(this);
        mSeatP5 = mRoot.findViewById(R.id.waiting_team_b_player2_seat);
        mSeatP5.getBackground().setAlpha(230);
        mBtnInfoP5 = mRoot.findViewById(R.id.btn_waiting_team_b_player2_info);
        mBtnInfoP5.setOnClickListener(this);
        mBtnDeleteP5 = mRoot.findViewById(R.id.btn_waiting_team_b_player2_delete);
        mBtnDeleteP5.setOnClickListener(this);

        mAvatarP6 = mRoot.findViewById(R.id.waiting_team_b_player3_avatar);
        mAvatarP6.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP6 = mRoot.findViewById(R.id.waiting_team_b_player3_gender);
        mPositionP6 = mRoot.findViewById(R.id.waiting_team_b_player3_position);
        mTextIdP6 = mRoot.findViewById(R.id.waiting_team_b_player3_id);
        mSeatP6 = mRoot.findViewById(R.id.waiting_team_b_player3_seat);
        mSeatP6.getBackground().setAlpha(230);
        mBtnSeatP6 = mRoot.findViewById(R.id.btn_waiting_team_b_player3_change_seat);
        mBtnSeatP6.setOnClickListener(this);
        mBtnInfoP6 = mRoot.findViewById(R.id.btn_waiting_team_b_player3_info);
        mBtnInfoP6.setOnClickListener(this);
        mBtnDeleteP6 = mRoot.findViewById(R.id.btn_waiting_team_b_player3_delete);
        mBtnDeleteP6.setOnClickListener(this);

        mAvatarP7 = mRoot.findViewById(R.id.waiting_referee_avatar);
        mAvatarP7.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP7 = mRoot.findViewById(R.id.waiting_referee_gender);
        mPositionP7 = mRoot.findViewById(R.id.waiting_referee_position);
        mTextIdP7 = mRoot.findViewById(R.id.waiting_referee_id);
        mBtnSeatP7 = mRoot.findViewById(R.id.btn_waiting_referee_change_seat);
        mBtnSeatP7.setOnClickListener(this);
        mSeatP7 = mRoot.findViewById(R.id.waiting_referee_seat);
        mSeatP7.getBackground().setAlpha(230);
        mBtnInfoP7 = mRoot.findViewById(R.id.btn_waiting_referee_info);
        mBtnInfoP7.setOnClickListener(this);
        mBtnDeleteP7 = mRoot.findViewById(R.id.btn_waiting_referee_delete);
        mBtnDeleteP7.setOnClickListener(this);

        return mRoot;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_waiting4join_back_arrow:
                mPresenter.finishWaiting4JoinUi();
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mBtnBackStack.setClickable(false);
                break;
            case R.id.btn_waiting4join_cancel:
                mPresenter.finishWaiting4JoinUi();
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mBtnCancel.setClickable(false);
                break;
            case R.id.btn_waiting4join_start:
                if (mCurrentGamerAmount == 7) {
                    mPresenter.initializeGamingRoomInfo();
                    mBtnStartGame.setClickable(false);
                } else {
                    mPresenter.showErrorToast("人數不足\n無法開始!", true);
                }
                break;
            case R.id.btn_waiting4join_instruction:
                mPresenter.openInstructionDialog();
                break;
            case R.id.btn_waiting4join_message:
                mPresenter.showErrorToast("Coming Soon !", true);
                break;
            case R.id.btn_waiting_team_a_player1_change_seat:
                Log.d(Constants.TAG, "onClick seat1");
                mPresenter.changeMaster2NewSeat(1);
                mNewSortForDeleteBtn = 1;
                mBtnSeatP1.setClickable(false);
                break;
            case R.id.btn_waiting_team_a_player2_change_seat:
                Log.d(Constants.TAG, "onClick seat2");
                mPresenter.changeMaster2NewSeat(2);
                mNewSortForDeleteBtn = 2;
                mBtnSeatP2.setClickable(false);
                break;
            case R.id.btn_waiting_team_a_player3_change_seat:
                Log.d(Constants.TAG, "onClick seat3");
                mPresenter.changeMaster2NewSeat(3);
                mNewSortForDeleteBtn = 3;
                mBtnSeatP3.setClickable(false);
                break;
            case R.id.btn_waiting_team_b_player1_change_seat:
                Log.d(Constants.TAG, "onClick seat4");
                mPresenter.changeMaster2NewSeat(4);
                mNewSortForDeleteBtn = 4;
                mBtnSeatP4.setClickable(false);
                break;
            case R.id.btn_waiting_team_b_player2_change_seat:
                Log.d(Constants.TAG, "onClick seat5");
                mPresenter.changeMaster2NewSeat(5);
                mNewSortForDeleteBtn = 5;
                mBtnSeatP5.setClickable(false);
                break;
            case R.id.btn_waiting_team_b_player3_change_seat:
                Log.d(Constants.TAG, "onClick seat6");
                mPresenter.changeMaster2NewSeat(6);
                mNewSortForDeleteBtn = 6;
                mBtnSeatP6.setClickable(false);
                break;
            case R.id.btn_waiting_referee_change_seat:
                Log.d(Constants.TAG, "onClick seat7");
                mPresenter.changeMaster2NewSeat(7);
                mNewSortForDeleteBtn = 7;
                mBtnSeatP7.setClickable(false);
                break;
            case R.id.btn_waiting_team_a_player1_info:
                Log.d(Constants.TAG, "Info seat1");
                mPresenter.openUserDetailMaster(1);
                break;
            case R.id.btn_waiting_team_a_player2_info:
                Log.d(Constants.TAG, "Info seat2");
                mPresenter.openUserDetailMaster(2);
                break;
            case R.id.btn_waiting_team_a_player3_info:
                Log.d(Constants.TAG, "Info seat3");
                mPresenter.openUserDetailMaster(3);
                break;
            case R.id.btn_waiting_team_b_player1_info:
                Log.d(Constants.TAG, "Info seat4");
                mPresenter.openUserDetailMaster(4);
                break;
            case R.id.btn_waiting_team_b_player2_info:
                Log.d(Constants.TAG, "Info seat5");
                mPresenter.openUserDetailMaster(5);
                break;
            case R.id.btn_waiting_team_b_player3_info:
                Log.d(Constants.TAG, "Info seat6");
                mPresenter.openUserDetailMaster(6);
                break;
            case R.id.btn_waiting_referee_info:
                Log.d(Constants.TAG, "Info seat7");
                mPresenter.openUserDetailMaster(7);
                break;
            case R.id.btn_waiting_team_a_player1_delete:
                Log.d(Constants.TAG, "Delete seat1");
                mBtnDeleteP1.setClickable(false);
                mPresenter.kickOutPlayer(1);
                break;
            case R.id.btn_waiting_team_a_player2_delete:
                Log.d(Constants.TAG, "Delete seat2");
                mBtnDeleteP2.setClickable(false);
                mPresenter.kickOutPlayer(2);
                break;
            case R.id.btn_waiting_team_a_player3_delete:
                Log.d(Constants.TAG, "Delete seat3");
                mBtnDeleteP3.setClickable(false);
                mPresenter.kickOutPlayer(3);
                break;
            case R.id.btn_waiting_team_b_player1_delete:
                Log.d(Constants.TAG, "Delete seat4");
                mBtnDeleteP4.setClickable(false);
                mPresenter.kickOutPlayer(4);
                break;
            case R.id.btn_waiting_team_b_player2_delete:
                Log.d(Constants.TAG, "Delete seat5");
                mBtnDeleteP5.setClickable(false);
                mPresenter.kickOutPlayer(5);
                break;
            case R.id.btn_waiting_team_b_player3_delete:
                Log.d(Constants.TAG, "Delete seat6");
                mBtnDeleteP6.setClickable(false);
                mPresenter.kickOutPlayer(6);
                break;
            case R.id.btn_waiting_referee_delete:
                Log.d(Constants.TAG, "Delete seat7");
                mBtnDeleteP7.setClickable(false);
                mPresenter.kickOutPlayer(7);
                break;
            default:
                break;
        }
    }

    @Override
    public void getNewPlayerAmount(int newPlayerAmount, int nowMasterSort) {
        mCurrentGamerAmount = newPlayerAmount;
        mNowMasterSort = nowMasterSort;
    }

    @Override
    public void showWaitingSeatsMasterUi(ArrayList<WaitingRoomSeats> newSeatsList) {
        mSeatsList = newSeatsList;
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                setUserSeatInfo(newSeatsList.get(i), 1, mAvatarP1, mGenderP1, mPositionP1, mTextIdP1, mBtnSeatP1, mBtnDeleteP1);
            } else if (i == 1) {
                setUserSeatInfo(newSeatsList.get(i), 2, mAvatarP2, mGenderP2, mPositionP2, mTextIdP2, mBtnSeatP2, mBtnDeleteP2);
            } else if (i == 2) {
                setUserSeatInfo(newSeatsList.get(i), 3, mAvatarP3, mGenderP3, mPositionP3, mTextIdP3, mBtnSeatP3, mBtnDeleteP3);
            } else if (i == 3) {
                setUserSeatInfo(newSeatsList.get(i), 4, mAvatarP4, mGenderP4, mPositionP4, mTextIdP4, mBtnSeatP4, mBtnDeleteP4);
            } else if (i == 4) {
                setUserSeatInfo(newSeatsList.get(i), 5, mAvatarP5, mGenderP5, mPositionP5, mTextIdP5, mBtnSeatP5, mBtnDeleteP5);
            } else if (i == 5) {
                setUserSeatInfo(newSeatsList.get(i), 6, mAvatarP6, mGenderP6, mPositionP6, mTextIdP6, mBtnSeatP6, mBtnDeleteP6);
            } else if (i == 6) {
                setUserSeatInfo(newSeatsList.get(i), 7, mAvatarP7, mGenderP7, mPositionP7, mTextIdP7, mBtnSeatP7, mBtnDeleteP7);
            } else {
                Log.d(Constants.TAG, "showWaitingSeatsSlaveUi Error!!");
            }
        }
        mBtnBackStack.setClickable(true);
        mBtnCancel.setClickable(true);
    }

    private void setUserSeatInfo(WaitingRoomSeats seatPlayerInfo, int sort,
                                 ImageView avatar, ImageView gender,
                                 ImageView position, TextView id,
                                 Button btnChangeSeat, Button btnKickOut) {
        // set avatar
        if (seatPlayerInfo.getAvatar().equals("")) {
            avatar.setImageResource(R.drawable.ic_user_avatar);
        } else {
            ImageManager.getInstance().setImageByUrl(avatar, seatPlayerInfo.getAvatar());
        }

        // set Gender
        if (seatPlayerInfo.getGender().equals("")) {
            gender.setVisibility(View.INVISIBLE);
        } else {
            gender.setVisibility(View.VISIBLE);
            ImageManager.getInstance().setGenderImage(gender, seatPlayerInfo.getGender());
        }

        // set Position image
        if (seatPlayerInfo.getPosition().equals("")) {
            position.setVisibility(View.INVISIBLE);
        } else if (sort == 7) {
            position.setVisibility(View.VISIBLE);
            mPositionP7.setImageResource(R.drawable.ic_position_referee);
        } else {
            position.setVisibility(View.VISIBLE);
//            setPositionImage(seatPlayerInfo, position);
            ImageManager.getInstance().setPositionImage(position,seatPlayerInfo.getPosition());
        }

        // set id
        if (seatPlayerInfo.getId().equals("")) {
            if (sort == 7) {
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
            btnChangeSeat.setClickable(true);
        } else {
            btnChangeSeat.setVisibility(View.GONE);
        }

        // set Btn kick out
        if (mNewSortForDeleteBtn == sort) {
            btnKickOut.setVisibility(View.GONE);
        } else {
            btnKickOut.setVisibility(View.VISIBLE);
            btnKickOut.setClickable(true);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radios_timer_yes:
                mTextMinute.setVisibility(View.VISIBLE);
                mBtnStartGame.setBackgroundResource(R.drawable.ic_start_unclick);
                mBtnStartGame.setClickable(false);
                break;
            case R.id.radios_timer_no:
                mTextMinute.setVisibility(View.INVISIBLE);
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
        mPresenter.setBackKeyDisable(true);
        mPresenter.setActivityBackgroundLandScape();
        mRoomName.setText(mWaitingRoomInfo.getRoomName());

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
    public boolean isActive() {
        return false;
    }

}
