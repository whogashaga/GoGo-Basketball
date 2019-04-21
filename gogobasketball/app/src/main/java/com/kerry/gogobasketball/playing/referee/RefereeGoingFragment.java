package com.kerry.gogobasketball.playing.referee;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.SeatAvatarOutlineProvider;
import com.kerry.gogobasketball.data.GamingPlayer;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.ImageManager;

public class RefereeGoingFragment extends Fragment implements RefereeGoingContract.View, View.OnClickListener {

    private RefereeGoingContract.Presenter mPresenter;
    View mRoot;

    private Button mBtnP1ScorePlus, mBtnP1ScoreMinus;
    private Button mBtnP1ReboundPlus, mBtnP1ReboundMinus;
    private Button mBtnP1FoulPlus, mBtnP1FoulMinus;
    private int mIntScoreP1, mIntReboundP1, mIntFoulP1;
    private TextView mTextP1Score, mTextP1Rebound, mTextP1Foul;
    private ImageView mAvatarP1, mGenderP1, mPositionP1;
    private TextView mTextIdP1;

    private Button mBtnP2ScorePlus, mBtnP2ScoreMinus;
    private Button mBtnP2ReboundPlus, mBtnP2ReboundMinus;
    private Button mBtnP2FoulPlus, mBtnP2FoulMinus;
    private TextView mTextP2Score, mTextP2Rebound, mTextP2Foul;
    private int mIntScoreP2, mIntReboundP2, mIntFoulP2;
    private ImageView mAvatarP2, mGenderP2, mPositionP2;
    private TextView mTextIdP2;

    private Button mBtnP3ScorePlus, mBtnP3ScoreMinus;
    private Button mBtnP3ReboundPlus, mBtnP3ReboundMinus;
    private Button mBtnP3FoulPlus, mBtnP3FoulMinus;
    private TextView mTextP3Score, mTextP3Rebound, mTextP3Foul;
    private int mIntScoreP3, mIntReboundP3, mIntFoulP3;
    private ImageView mAvatarP3, mGenderP3, mPositionP3;
    private TextView mTextIdP3;

    private Button mBtnP4ScorePlus, mBtnP4ScoreMinus;
    private Button mBtnP4ReboundPlus, mBtnP4ReboundMinus;
    private Button mBtnP4FoulPlus, mBtnP4FoulMinus;
    private TextView mTextP4Score, mTextP4Rebound, mTextP4Foul;
    private int mIntScoreP4, mIntReboundP4, mIntFoulP4;
    private ImageView mAvatarP4, mGenderP4, mPositionP4;
    private TextView mTextIdP4;

    private Button mBtnP5ScorePlus, mBtnP5ScoreMinus;
    private Button mBtnP5ReboundPlus, mBtnP5ReboundMinus;
    private Button mBtnP5FoulPlus, mBtnP5FoulMinus;
    private TextView mTextP5Score, mTextP5Rebound, mTextP5Foul;
    private int mIntScoreP5, mIntReboundP5, mIntFoulP5;
    private ImageView mAvatarP5, mGenderP5, mPositionP5;
    private TextView mTextIdP5;

    private Button mBtnP6ScorePlus, mBtnP6ScoreMinus;
    private Button mBtnP6ReboundPlus, mBtnP6ReboundMinus;
    private Button mBtnP6FoulPlus, mBtnP6FoulMinus;
    private TextView mTextP6Score, mTextP6Rebound, mTextP6Foul;
    private int mIntScoreP6, mIntReboundP6, mIntFoulP6;
    private ImageView mAvatarP6, mGenderP6, mPositionP6;
    private TextView mTextIdP6;

    private TextView mTeamScoreA, mTeamScoreB;
    private int mIntScoreA;
    private int mIntScoreB;
    private Button mBtnGameOver;

    private GamingRoomInfo mGamingRoomInfo;

    public RefereeGoingFragment() {
        mGamingRoomInfo = new GamingRoomInfo();
    }

    public static RefereeGoingFragment newInstance() {
        return new RefereeGoingFragment();
    }

    @Override
    public void setPresenter(RefereeGoingContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_game_playing_referee, container, false);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        mBtnP1ScorePlus = mRoot.findViewById(R.id.btn_playing_team_a_player1_point_plus);
        mBtnP1ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_a_player1_point_minus);
        mBtnP1ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_a_player1_rebound_plus);
        mBtnP1ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_a_player1_rebound_minus);
        mBtnP1FoulPlus = mRoot.findViewById(R.id.btn_playing_team_a_player1_foul_plus);
        mBtnP1FoulMinus = mRoot.findViewById(R.id.btn_playing_team_a_player1_foul_minus);
        mTextP1Score = mRoot.findViewById(R.id.text_playing_team_a_player1_point);
        mTextP1Rebound = mRoot.findViewById(R.id.text_playing_team_a_player1_rebound);
        mTextP1Foul = mRoot.findViewById(R.id.text_playing_team_a_player1_foul);
        mAvatarP1 = mRoot.findViewById(R.id.playing_team_a_player1_avatar);
        mAvatarP1.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP1 = mRoot.findViewById(R.id.playing_team_a_player1_gender);
        mPositionP1 = mRoot.findViewById(R.id.playing_team_a_player1_position);
        mTextIdP1 = mRoot.findViewById(R.id.playing_team_a_player1_id);

        mBtnP2ScorePlus = mRoot.findViewById(R.id.btn_playing_team_a_player2_point_plus);
        mBtnP2ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_a_player2_point_minus);
        mBtnP2ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_a_player2_rebound_plus);
        mBtnP2ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_a_player2_rebound_minus);
        mBtnP2FoulPlus = mRoot.findViewById(R.id.btn_playing_team_a_player2_foul_plus);
        mBtnP2FoulMinus = mRoot.findViewById(R.id.btn_playing_team_a_player2_foul_minus);
        mTextP2Score = mRoot.findViewById(R.id.text_playing_team_a_player2_point);
        mTextP2Rebound = mRoot.findViewById(R.id.text_playing_team_a_player2_rebound);
        mTextP2Foul = mRoot.findViewById(R.id.text_playing_team_a_player2_foul);
        mAvatarP2 = mRoot.findViewById(R.id.playing_team_a_player2_avatar);
        mAvatarP2.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP2 = mRoot.findViewById(R.id.playing_team_a_player2_gender);
        mPositionP2 = mRoot.findViewById(R.id.playing_team_a_player2_position);
        mTextIdP2 = mRoot.findViewById(R.id.playing_team_a_player2_id);

        mBtnP3ScorePlus = mRoot.findViewById(R.id.btn_playing_team_a_player3_point_plus);
        mBtnP3ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_a_player3_point_minus);
        mBtnP3ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_a_player3_rebound_plus);
        mBtnP3ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_a_player3_rebound_minus);
        mBtnP3FoulPlus = mRoot.findViewById(R.id.btn_playing_team_a_player3_foul_plus);
        mBtnP3FoulMinus = mRoot.findViewById(R.id.btn_playing_team_a_player3_foul_minus);
        mTextP3Score = mRoot.findViewById(R.id.text_playing_team_a_player3_point);
        mTextP3Rebound = mRoot.findViewById(R.id.text_playing_team_a_player3_rebound);
        mTextP3Foul = mRoot.findViewById(R.id.text_playing_team_a_player3_foul);
        mAvatarP3 = mRoot.findViewById(R.id.playing_team_a_player3_avatar);
        mAvatarP3.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP3 = mRoot.findViewById(R.id.playing_team_a_player3_gender);
        mPositionP3 = mRoot.findViewById(R.id.playing_team_a_player3_position);
        mTextIdP3 = mRoot.findViewById(R.id.playing_team_a_player3_id);

        mBtnP4ScorePlus = mRoot.findViewById(R.id.btn_playing_team_b_player1_point_plus);
        mBtnP4ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_b_player1_point_minus);
        mBtnP4ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_b_player1_rebound_plus);
        mBtnP4ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_b_player1_rebound_minus);
        mBtnP4FoulPlus = mRoot.findViewById(R.id.btn_playing_team_b_player1_foul_plus);
        mBtnP4FoulMinus = mRoot.findViewById(R.id.btn_playing_team_b_player1_foul_minus);
        mTextP4Score = mRoot.findViewById(R.id.text_playing_team_b_player1_point);
        mTextP4Rebound = mRoot.findViewById(R.id.text_playing_team_b_player1_rebound);
        mTextP4Foul = mRoot.findViewById(R.id.text_playing_team_b_player1_foul);
        mAvatarP4 = mRoot.findViewById(R.id.playing_team_b_player1_avatar);
        mAvatarP4.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP4 = mRoot.findViewById(R.id.playing_team_b_player1_gender);
        mPositionP4 = mRoot.findViewById(R.id.playing_team_b_player1_position);
        mTextIdP4 = mRoot.findViewById(R.id.playing_team_b_player1_id);

        mBtnP5ScorePlus = mRoot.findViewById(R.id.btn_playing_team_b_player2_point_plus);
        mBtnP5ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_b_player2_point_minus);
        mBtnP5ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_b_player2_rebound_plus);
        mBtnP5ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_b_player2_rebound_minus);
        mBtnP5FoulPlus = mRoot.findViewById(R.id.btn_playing_team_b_player2_foul_plus);
        mBtnP5FoulMinus = mRoot.findViewById(R.id.btn_playing_team_b_player2_foul_minus);
        mTextP5Score = mRoot.findViewById(R.id.text_playing_team_b_player2_point);
        mTextP5Rebound = mRoot.findViewById(R.id.text_playing_team_b_player2_rebound);
        mTextP5Foul = mRoot.findViewById(R.id.text_playing_team_b_player2_foul);
        mAvatarP5 = mRoot.findViewById(R.id.playing_team_b_player2_avatar);
        mAvatarP5.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP5 = mRoot.findViewById(R.id.playing_team_b_player2_gender);
        mPositionP5 = mRoot.findViewById(R.id.playing_team_b_player2_position);
        mTextIdP5 = mRoot.findViewById(R.id.playing_team_b_player2_id);

        mBtnP6ScorePlus = mRoot.findViewById(R.id.btn_playing_team_b_player3_point_plus);
        mBtnP6ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_b_player3_point_minus);
        mBtnP6ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_b_player3_rebound_plus);
        mBtnP6ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_b_player3_rebound_minus);
        mBtnP6FoulPlus = mRoot.findViewById(R.id.btn_playing_team_b_player3_foul_plus);
        mBtnP6FoulMinus = mRoot.findViewById(R.id.btn_playing_team_b_player3_foul_minus);
        mTextP6Score = mRoot.findViewById(R.id.text_playing_team_b_player3_point);
        mTextP6Rebound = mRoot.findViewById(R.id.text_playing_team_b_player3_rebound);
        mTextP6Foul = mRoot.findViewById(R.id.text_playing_team_b_player3_foul);
        mAvatarP6 = mRoot.findViewById(R.id.playing_team_b_player3_avatar);
        mAvatarP6.setOutlineProvider(new SeatAvatarOutlineProvider());
        mGenderP6 = mRoot.findViewById(R.id.playing_team_b_player3_gender);
        mPositionP6 = mRoot.findViewById(R.id.playing_team_b_player3_position);
        mTextIdP6 = mRoot.findViewById(R.id.playing_team_b_player3_id);

        mTeamScoreA = mRoot.findViewById(R.id.text_gaming_current_score_team_a);
        mTeamScoreB = mRoot.findViewById(R.id.text_gaming_current_score_team_b);

        mBtnGameOver = mRoot.findViewById(R.id.btn_gaming_game_over);
        mBtnGameOver.setOnClickListener(this);

        return mRoot;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_playing_team_a_player1_point_plus:
                mPresenter.increaseScoreP1();
                break;
            case R.id.btn_playing_team_a_player1_point_minus:
                mPresenter.decreaseScoreP1();
                break;
            case R.id.btn_playing_team_a_player1_rebound_plus:
                mPresenter.increaseReboundP1();
                break;
            case R.id.btn_playing_team_a_player1_rebound_minus:
                mPresenter.decreaseReboundP1();
                break;
            case R.id.btn_playing_team_a_player1_foul_plus:
                mPresenter.increaseFoulP1();
                break;
            case R.id.btn_playing_team_a_player1_foul_minus:
                mPresenter.decreaseFoulP1();
                break;
            case R.id.btn_playing_team_a_player2_point_plus:
                mPresenter.increaseScoreP2();
                break;
            case R.id.btn_playing_team_a_player2_point_minus:
                mPresenter.decreaseScoreP2();
                break;
            case R.id.btn_playing_team_a_player2_rebound_plus:
                mPresenter.increaseReboundP2();
                break;
            case R.id.btn_playing_team_a_player2_rebound_minus:
                mPresenter.decreaseReboundP2();
                break;
            case R.id.btn_playing_team_a_player2_foul_plus:
                mPresenter.increaseFoulP2();
                break;
            case R.id.btn_playing_team_a_player2_foul_minus:
                mPresenter.decreaseFoulP2();
                break;
            case R.id.btn_playing_team_a_player3_point_plus:
                mPresenter.increaseScoreP3();
                break;
            case R.id.btn_playing_team_a_player3_point_minus:
                mPresenter.decreaseScoreP3();
                break;
            case R.id.btn_playing_team_a_player3_rebound_plus:
                mPresenter.increaseReboundP3();
                break;
            case R.id.btn_playing_team_a_player3_rebound_minus:
                mPresenter.decreaseReboundP3();
                break;
            case R.id.btn_playing_team_a_player3_foul_plus:
                mPresenter.increaseFoulP3();
                break;
            case R.id.btn_playing_team_a_player3_foul_minus:
                mPresenter.decreaseFoulP3();
                break;
            case R.id.btn_playing_team_b_player1_point_plus:
                mPresenter.increaseScoreP4();
                break;
            case R.id.btn_playing_team_b_player1_point_minus:
                mPresenter.decreaseScoreP4();
                break;
            case R.id.btn_playing_team_b_player1_rebound_plus:
                mPresenter.increaseReboundP4();
                break;
            case R.id.btn_playing_team_b_player1_rebound_minus:
                mPresenter.decreaseReboundP4();
                break;
            case R.id.btn_playing_team_b_player1_foul_plus:
                mPresenter.increaseFoulP4();
                break;
            case R.id.btn_playing_team_b_player1_foul_minus:
                mPresenter.decreaseFoulP4();
                break;
            case R.id.btn_playing_team_b_player2_point_plus:
                mPresenter.increaseScoreP5();
                break;
            case R.id.btn_playing_team_b_player2_point_minus:
                mPresenter.decreaseScoreP5();
                break;
            case R.id.btn_playing_team_b_player2_rebound_plus:
                mPresenter.increaseReboundP5();
                break;
            case R.id.btn_playing_team_b_player2_rebound_minus:
                mPresenter.decreaseReboundP5();
                break;
            case R.id.btn_playing_team_b_player2_foul_plus:
                mPresenter.increaseFoulP5();
                break;
            case R.id.btn_playing_team_b_player2_foul_minus:
                mPresenter.decreaseFoulP5();
                break;
            case R.id.btn_playing_team_b_player3_point_plus:
                mPresenter.increaseScoreP6();
                break;
            case R.id.btn_playing_team_b_player3_point_minus:
                mPresenter.decreaseScoreP6();
                break;
            case R.id.btn_playing_team_b_player3_rebound_plus:
                mPresenter.increaseReboundP6();
                break;
            case R.id.btn_playing_team_b_player3_rebound_minus:
                mPresenter.decreaseReboundP6();
                break;
            case R.id.btn_playing_team_b_player3_foul_plus:
                mPresenter.increaseFoulP6();
                break;
            case R.id.btn_playing_team_b_player3_foul_minus:
                mPresenter.decreaseFoulP6();
                break;
            case R.id.btn_gaming_game_over:
                mPresenter.checkWhichTeamWon();
                break;
            default:
                break;
        }
    }

    private GamingRoomInfo setFinalResult(GamingRoomInfo gamingRoomInfo) {

        gamingRoomInfo.getPlayer1().setScore(mIntScoreP1);
        gamingRoomInfo.getPlayer1().setRebound(mIntReboundP1);
        gamingRoomInfo.getPlayer1().setFoul(mIntFoulP1);

        gamingRoomInfo.getPlayer2().setScore(mIntScoreP2);
        gamingRoomInfo.getPlayer2().setRebound(mIntReboundP2);
        gamingRoomInfo.getPlayer2().setFoul(mIntFoulP2);

        gamingRoomInfo.getPlayer3().setScore(mIntScoreP3);
        gamingRoomInfo.getPlayer3().setRebound(mIntReboundP3);
        gamingRoomInfo.getPlayer3().setFoul(mIntFoulP3);

        gamingRoomInfo.getPlayer4().setScore(mIntScoreP4);
        gamingRoomInfo.getPlayer4().setRebound(mIntReboundP4);
        gamingRoomInfo.getPlayer4().setFoul(mIntFoulP4);

        gamingRoomInfo.getPlayer5().setScore(mIntScoreP5);
        gamingRoomInfo.getPlayer5().setRebound(mIntReboundP5);
        gamingRoomInfo.getPlayer5().setFoul(mIntFoulP5);

        gamingRoomInfo.getPlayer6().setScore(mIntScoreP6);
        gamingRoomInfo.getPlayer6().setRebound(mIntReboundP6);
        gamingRoomInfo.getPlayer6().setFoul(mIntFoulP6);

        if (mIntScoreA > mIntScoreB) {
            gamingRoomInfo.setWinner("a");
        } else {
            gamingRoomInfo.setWinner("b");
        }

        gamingRoomInfo.setStatus(Constants.STATUS_OVER);

        return gamingRoomInfo;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setBackKeyDisable(true);
        mPresenter.setGamingNowMessage(true);

        mBtnP1ScorePlus.setOnClickListener(this);
        mBtnP1ScoreMinus.setOnClickListener(this);
        mBtnP1ReboundPlus.setOnClickListener(this);
        mBtnP1ReboundMinus.setOnClickListener(this);
        mBtnP1FoulPlus.setOnClickListener(this);
        mBtnP1FoulMinus.setOnClickListener(this);

        mBtnP2ScorePlus.setOnClickListener(this);
        mBtnP2ScoreMinus.setOnClickListener(this);
        mBtnP2ReboundPlus.setOnClickListener(this);
        mBtnP2ReboundMinus.setOnClickListener(this);
        mBtnP2FoulPlus.setOnClickListener(this);
        mBtnP2FoulMinus.setOnClickListener(this);

        mBtnP3ScorePlus.setOnClickListener(this);
        mBtnP3ScoreMinus.setOnClickListener(this);
        mBtnP3ReboundPlus.setOnClickListener(this);
        mBtnP3ReboundMinus.setOnClickListener(this);
        mBtnP3FoulPlus.setOnClickListener(this);
        mBtnP3FoulMinus.setOnClickListener(this);

        mBtnP4ScorePlus.setOnClickListener(this);
        mBtnP4ScoreMinus.setOnClickListener(this);
        mBtnP4ReboundPlus.setOnClickListener(this);
        mBtnP4ReboundMinus.setOnClickListener(this);
        mBtnP4FoulPlus.setOnClickListener(this);
        mBtnP4FoulMinus.setOnClickListener(this);

        mBtnP5ScorePlus.setOnClickListener(this);
        mBtnP5ScoreMinus.setOnClickListener(this);
        mBtnP5ReboundPlus.setOnClickListener(this);
        mBtnP5ReboundMinus.setOnClickListener(this);
        mBtnP5FoulPlus.setOnClickListener(this);
        mBtnP5FoulMinus.setOnClickListener(this);

        mBtnP6ScorePlus.setOnClickListener(this);
        mBtnP6ScoreMinus.setOnClickListener(this);
        mBtnP6ReboundPlus.setOnClickListener(this);
        mBtnP6ReboundMinus.setOnClickListener(this);
        mBtnP6FoulPlus.setOnClickListener(this);
        mBtnP6FoulMinus.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getHostNameFromPresenter(String hostName) {
        mPresenter.getGamingRoomFromFireStore(hostName);
    }

    @Override
    public void openGameResultRefereeUi(String hostName) {
        mPresenter.openGameResultReferee(hostName);
        mPresenter.getRefereeUserData(getActivity());
        onDestroy();
    }

    @Override
    public void setGamingNow(boolean isGamingNow) {
        mPresenter.setGamingNowMessage(isGamingNow);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void showPlayingGameUi(GamingRoomInfo gamingRoomInfo) {
        mGamingRoomInfo = gamingRoomInfo;
        setPlayerInfo(gamingRoomInfo.getPlayer1(), mAvatarP1, mGenderP1, mPositionP1, mTextIdP1);
        setPlayerInfo(gamingRoomInfo.getPlayer2(), mAvatarP2, mGenderP2, mPositionP2, mTextIdP2);
        setPlayerInfo(gamingRoomInfo.getPlayer3(), mAvatarP3, mGenderP3, mPositionP3, mTextIdP3);
        setPlayerInfo(gamingRoomInfo.getPlayer4(), mAvatarP4, mGenderP4, mPositionP4, mTextIdP4);
        setPlayerInfo(gamingRoomInfo.getPlayer5(), mAvatarP5, mGenderP5, mPositionP5, mTextIdP5);
        setPlayerInfo(gamingRoomInfo.getPlayer6(), mAvatarP6, mGenderP6, mPositionP6, mTextIdP6);
    }

    private void setPlayerInfo(GamingPlayer gamingPlayer, ImageView avatar,
                               ImageView gender, ImageView position, TextView textId) {
        ImageManager.getInstance().setImageByUrl(avatar, gamingPlayer.getAvatar());
        setGenderImage(gender, gamingPlayer.getGender());
        setPositionImage(position, gamingPlayer.getPosition());
        textId.setText(gamingPlayer.getId());
    }

    private void setGenderImage(ImageView genderImage, String genderStr) {

        if (genderStr.equals("male")) {
            genderImage.setImageResource(R.drawable.ic_male);
        } else {
            genderImage.setImageResource(R.drawable.ic_female);
        }
    }

    private void setPositionImage(ImageView imageView, String positionStr) {
        if (positionStr.equals(Constants.POSITION_PG)) {
            imageView.setImageResource(R.drawable.ic_position_pg);
        } else if (positionStr.equals(Constants.POSITION_SG)) {
            imageView.setImageResource(R.drawable.ic_position_sg);
        } else if (positionStr.equals(Constants.POSITION_SF)) {
            imageView.setImageResource(R.drawable.ic_position_sf);
        } else if (positionStr.equals(Constants.POSITION_PF)) {
            imageView.setImageResource(R.drawable.ic_position_pf);
        } else if (positionStr.equals(Constants.POSITION_CENTER)) {
            imageView.setImageResource(R.drawable.ic_position_center);
        } else {
            Log.e("Kerry", "Set Position Error!!");
        }
    }

    @Override
    public void setScorePlusClickableTeamA(boolean clickable) {
        mBtnP1ScorePlus.setClickable(clickable);
        mBtnP2ScorePlus.setClickable(clickable);
        mBtnP3ScorePlus.setClickable(clickable);
    }

    @Override
    public void setScorePlusClickableTeamB(boolean clickable) {
        mBtnP4ScorePlus.setClickable(clickable);
        mBtnP5ScorePlus.setClickable(clickable);
        mBtnP6ScorePlus.setClickable(clickable);
    }

    @Override
    public void setTextScoreTeamA(String textScoreTeamA) {
        mIntScoreA = Integer.valueOf(textScoreTeamA);
        mTeamScoreA.setText(textScoreTeamA);
    }

    @Override
    public void setTextScoreTeamB(String textScoreTeamB) {
        mIntScoreB = Integer.valueOf(textScoreTeamB);
        mTeamScoreB.setText(textScoreTeamB);
    }

    /* ------------------------------------------------------------------------------------------ */

    /* Player1 */

    @Override
    public void setTextScoreP1(String textScoreViewP1) {
        mTextP1Score.setText(textScoreViewP1);
    }

    @Override
    public void setBtnClickableScoreMinusP1(boolean clickable) {
        mBtnP1ScoreMinus.setClickable(clickable);
    }

    @Override
    public void setTextReboundP1(String textReboundP1) {
        mTextP1Rebound.setText(textReboundP1);
    }

    @Override
    public void setBtnClickableReboundPlusP1(boolean clickable) {
        mBtnP1ReboundPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableReboundMinusP1(boolean clickable) {
        mBtnP1ReboundMinus.setClickable(clickable);
    }

    @Override
    public void setTextFoulP1(String textFoulP1) {
        mTextP1Foul.setText(textFoulP1);
    }

    @Override
    public void setBtnClickableFoulPlusP1(boolean clickable) {
        mBtnP1FoulPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableFoulMinusP1(boolean clickable) {
        mBtnP1FoulMinus.setClickable(clickable);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player2 */

    @Override
    public void setTextScoreP2(String textScoreViewP2) {
        mTextP2Score.setText(textScoreViewP2);
    }

    @Override
    public void setBtnClickableScoreMinusP2(boolean clickable) {
        mBtnP2ScoreMinus.setClickable(clickable);
    }

    @Override
    public void setTextReboundP2(String textReboundP2) {
        mTextP2Rebound.setText(textReboundP2);
    }

    @Override
    public void setBtnClickableReboundPlusP2(boolean clickable) {
        mBtnP2ReboundPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableReboundMinusP2(boolean clickable) {
        mBtnP2ReboundMinus.setClickable(clickable);
    }

    @Override
    public void setTextFoulP2(String textFoulP2) {
        mTextP2Foul.setText(textFoulP2);
    }

    @Override
    public void setBtnClickableFoulPlusP2(boolean clickable) {
        mBtnP2FoulPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableFoulMinusP2(boolean clickable) {
        mBtnP2FoulMinus.setClickable(clickable);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player3 */

    @Override
    public void setTextScoreP3(String textScoreViewP3) {
        mTextP3Score.setText(textScoreViewP3);
    }

    @Override
    public void setBtnClickableScoreMinusP3(boolean clickable) {
        mBtnP3ScoreMinus.setClickable(clickable);
    }

    @Override
    public void setTextReboundP3(String textReboundP3) {
        mTextP3Rebound.setText(textReboundP3);
    }

    @Override
    public void setBtnClickableReboundPlusP3(boolean clickable) {
        mBtnP3ReboundPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableReboundMinusP3(boolean clickable) {
        mBtnP3ReboundMinus.setClickable(clickable);
    }

    @Override
    public void setTextFoulP3(String textFoulP3) {
        mTextP3Foul.setText(textFoulP3);
    }

    @Override
    public void setBtnClickableFoulPlusP3(boolean clickable) {
        mBtnP3FoulPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableFoulMinusP3(boolean clickable) {
        mBtnP3FoulMinus.setClickable(clickable);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player4 */

    @Override
    public void setTextScoreP4(String textScoreViewP4) {
        mTextP4Score.setText(textScoreViewP4);
    }

    @Override
    public void setBtnClickableScoreMinusP4(boolean clickable) {
        mBtnP4ScoreMinus.setClickable(clickable);
    }

    @Override
    public void setTextReboundP4(String textReboundP4) {
        mTextP4Rebound.setText(textReboundP4);
    }

    @Override
    public void setBtnClickableReboundPlusP4(boolean clickable) {
        mBtnP4ReboundPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableReboundMinusP4(boolean clickable) {
        mBtnP4ReboundMinus.setClickable(clickable);
    }

    @Override
    public void setTextFoulP4(String textFoulP4) {
        mTextP4Foul.setText(textFoulP4);
    }

    @Override
    public void setBtnClickableFoulPlusP4(boolean clickable) {
        mBtnP4FoulPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableFoulMinusP4(boolean clickable) {
        mBtnP4FoulMinus.setClickable(clickable);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player5 */

    @Override
    public void setTextScoreP5(String textScoreViewP5) {
        mTextP5Score.setText(textScoreViewP5);
    }

    @Override
    public void setBtnClickableScoreMinusP5(boolean clickable) {
        mBtnP5ScoreMinus.setClickable(clickable);
    }

    @Override
    public void setTextReboundP5(String textReboundP5) {
        mTextP5Rebound.setText(textReboundP5);
    }

    @Override
    public void setBtnClickableReboundPlusP5(boolean clickable) {
        mBtnP5ReboundPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableReboundMinusP5(boolean clickable) {
        mBtnP5ReboundMinus.setClickable(clickable);
    }

    @Override
    public void setTextFoulP5(String textFoulP5) {
        mTextP5Foul.setText(textFoulP5);
    }

    @Override
    public void setBtnClickableFoulPlusP5(boolean clickable) {
        mBtnP5FoulPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableFoulMinusP5(boolean clickable) {
        mBtnP5FoulMinus.setClickable(clickable);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player6 */

    @Override
    public void setTextScoreP6(String textScoreViewP6) {
        mTextP6Score.setText(textScoreViewP6);
    }

    @Override
    public void setBtnClickableScoreMinusP6(boolean clickable) {
        mBtnP6ScoreMinus.setClickable(clickable);
    }

    @Override
    public void setTextReboundP6(String textReboundP6) {
        mTextP6Rebound.setText(textReboundP6);
    }

    @Override
    public void setBtnClickableReboundPlusP6(boolean clickable) {
        mBtnP6ReboundPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableReboundMinusP6(boolean clickable) {
        mBtnP6ReboundMinus.setClickable(clickable);
    }

    @Override
    public void setTextFoulP6(String textFoulP6) {
        mTextP6Foul.setText(textFoulP6);
    }

    @Override
    public void setBtnClickableFoulPlusP6(boolean clickable) {
        mBtnP6FoulPlus.setClickable(clickable);
    }

    @Override
    public void setBtnClickableFoulMinusP6(boolean clickable) {
        mBtnP6FoulMinus.setClickable(clickable);
    }


    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showErrorToast(String message, boolean isShort) {
        mPresenter.showErrorToast(message, isShort);
    }
}
