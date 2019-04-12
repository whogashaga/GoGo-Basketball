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
import android.widget.TextView;

import com.kerry.gogobasketball.R;

public class RefereeGoingFragment extends Fragment implements RefereeGoingContract.View, View.OnClickListener {

    private RefereeGoingContract.Presenter mPresenter;
    View mRoot;

    private Button mBtnP1ScorePlus, mBtnP1ScoreMinus;
    private Button mBtnP1ReboundPlus, mBtnP1ReboundMinus;
    private Button mBtnP1FoulPlus, mBtnP1FoulMinus;
    private TextView mTextP1Score, mTextP1Rebound, mTextP1Foul;
    private int mIntScoreP1, mIntReboundP1, mIntFoulP1;

    private Button mBtnP2ScorePlus, mBtnP2ScoreMinus;
    private Button mBtnP2ReboundPlus, mBtnP2ReboundMinus;
    private Button mBtnP2FoulPlus, mBtnP2FoulMinus;
    private TextView mTextP2Score, mTextP2Rebound, mTextP2Foul;
    private int mIntScoreP2, mIntReboundP2, mIntFoulP2;

    private Button mBtnP3ScorePlus, mBtnP3ScoreMinus;
    private Button mBtnP3ReboundPlus, mBtnP3ReboundMinus;
    private Button mBtnP3FoulPlus, mBtnP3FoulMinus;
    private TextView mTextP3Score, mTextP3Rebound, mTextP3Foul;
    private int mIntScoreP3, mIntReboundP3, mIntFoulP3;

    private Button mBtnP4ScorePlus, mBtnP4ScoreMinus;
    private Button mBtnP4ReboundPlus, mBtnP4ReboundMinus;
    private Button mBtnP4FoulPlus, mBtnP4FoulMinus;
    private TextView mTextP4Score, mTextP4Rebound, mTextP4Foul;
    private int mIntScoreP4, mIntReboundP4, mIntFoulP4;

    private Button mBtnP5ScorePlus, mBtnP5ScoreMinus;
    private Button mBtnP5ReboundPlus, mBtnP5ReboundMinus;
    private Button mBtnP5FoulPlus, mBtnP5FoulMinus;
    private TextView mTextP5Score, mTextP5Rebound, mTextP5Foul;
    private int mIntScoreP5, mIntReboundP5, mIntFoulP5;

    private Button mBtnP6ScorePlus, mBtnP6ScoreMinus;
    private Button mBtnP6ReboundPlus, mBtnP6ReboundMinus;
    private Button mBtnP6FoulPlus, mBtnP6FoulMinus;
    private TextView mTextP6Score, mTextP6Rebound, mTextP6Foul;
    private int mIntScoreP6, mIntReboundP6, mIntFoulP6;

    private TextView mTeamScoreA, mTeamScoreB;
    private int mIntScoreA;
    private int mIntScoreB;

    public RefereeGoingFragment() {
        mIntScoreP1 = 0;
        mIntScoreP2 = 0;
        mIntScoreP3 = 0;
        mIntScoreP4 = 0;
        mIntScoreP5 = 0;
        mIntScoreP6 = 0;
        mIntReboundP1 = 0;
        mIntReboundP2 = 0;
        mIntReboundP3 = 0;
        mIntReboundP4 = 0;
        mIntReboundP5 = 0;
        mIntReboundP6 = 0;
        mIntFoulP1 = 0;
        mIntFoulP2 = 0;
        mIntFoulP3 = 0;
        mIntFoulP4 = 0;
        mIntFoulP5 = 0;
        mIntFoulP6 = 0;
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

        mBtnP2ScorePlus = mRoot.findViewById(R.id.btn_playing_team_a_player2_point_plus);
        mBtnP2ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_a_player2_point_minus);
        mBtnP2ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_a_player2_rebound_plus);
        mBtnP2ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_a_player2_rebound_minus);
        mBtnP2FoulPlus = mRoot.findViewById(R.id.btn_playing_team_a_player2_foul_plus);
        mBtnP2FoulMinus = mRoot.findViewById(R.id.btn_playing_team_a_player2_foul_minus);
        mTextP2Score = mRoot.findViewById(R.id.text_playing_team_a_player2_point);
        mTextP2Rebound = mRoot.findViewById(R.id.text_playing_team_a_player2_rebound);
        mTextP2Foul = mRoot.findViewById(R.id.text_playing_team_a_player2_foul);

        mBtnP3ScorePlus = mRoot.findViewById(R.id.btn_playing_team_a_player3_point_plus);
        mBtnP3ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_a_player3_point_minus);
        mBtnP3ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_a_player3_rebound_plus);
        mBtnP3ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_a_player3_rebound_minus);
        mBtnP3FoulPlus = mRoot.findViewById(R.id.btn_playing_team_a_player3_foul_plus);
        mBtnP3FoulMinus = mRoot.findViewById(R.id.btn_playing_team_a_player3_foul_minus);
        mTextP3Score = mRoot.findViewById(R.id.text_playing_team_a_player3_point);
        mTextP3Rebound = mRoot.findViewById(R.id.text_playing_team_a_player3_rebound);
        mTextP3Foul = mRoot.findViewById(R.id.text_playing_team_a_player3_foul);

        mBtnP4ScorePlus = mRoot.findViewById(R.id.btn_playing_team_b_player1_point_plus);
        mBtnP4ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_b_player1_point_minus);
        mBtnP4ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_b_player1_rebound_plus);
        mBtnP4ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_b_player1_rebound_minus);
        mBtnP4FoulPlus = mRoot.findViewById(R.id.btn_playing_team_b_player1_foul_plus);
        mBtnP4FoulMinus = mRoot.findViewById(R.id.btn_playing_team_b_player1_foul_minus);
        mTextP4Score = mRoot.findViewById(R.id.text_playing_team_b_player1_point);
        mTextP4Rebound = mRoot.findViewById(R.id.text_playing_team_b_player1_rebound);
        mTextP4Foul = mRoot.findViewById(R.id.text_playing_team_b_player1_foul);

        mBtnP5ScorePlus = mRoot.findViewById(R.id.btn_playing_team_b_player2_point_plus);
        mBtnP5ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_b_player2_point_minus);
        mBtnP5ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_b_player2_rebound_plus);
        mBtnP5ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_b_player2_rebound_minus);
        mBtnP5FoulPlus = mRoot.findViewById(R.id.btn_playing_team_b_player2_foul_plus);
        mBtnP5FoulMinus = mRoot.findViewById(R.id.btn_playing_team_b_player2_foul_minus);
        mTextP5Score = mRoot.findViewById(R.id.text_playing_team_b_player2_point);
        mTextP5Rebound = mRoot.findViewById(R.id.text_playing_team_b_player2_rebound);
        mTextP5Foul = mRoot.findViewById(R.id.text_playing_team_b_player2_foul);

        mBtnP6ScorePlus = mRoot.findViewById(R.id.btn_playing_team_b_player3_point_plus);
        mBtnP6ScoreMinus = mRoot.findViewById(R.id.btn_playing_team_b_player3_point_minus);
        mBtnP6ReboundPlus = mRoot.findViewById(R.id.btn_playing_team_b_player3_rebound_plus);
        mBtnP6ReboundMinus = mRoot.findViewById(R.id.btn_playing_team_b_player3_rebound_minus);
        mBtnP6FoulPlus = mRoot.findViewById(R.id.btn_playing_team_b_player3_foul_plus);
        mBtnP6FoulMinus = mRoot.findViewById(R.id.btn_playing_team_b_player3_foul_minus);
        mTextP6Score = mRoot.findViewById(R.id.text_playing_team_b_player3_point);
        mTextP6Rebound = mRoot.findViewById(R.id.text_playing_team_b_player3_rebound);
        mTextP6Foul = mRoot.findViewById(R.id.text_playing_team_b_player3_foul);

        mTeamScoreA = mRoot.findViewById(R.id.text_gaming_current_score_team_a);
        mTeamScoreB = mRoot.findViewById(R.id.text_gaming_current_score_team_b);

        return mRoot;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_playing_team_a_player1_point_plus:
                increaseScoreP1();
                break;
            case R.id.btn_playing_team_a_player1_point_minus:
                decreaseScoreP1();
                break;
            case R.id.btn_playing_team_a_player1_rebound_plus:
                increaseReboundP1();
                break;
            case R.id.btn_playing_team_a_player1_rebound_minus:
                decreaseReboundP1();
                break;
            case R.id.btn_playing_team_a_player1_foul_plus:
                increaseFoulP1();
                break;
            case R.id.btn_playing_team_a_player1_foul_minus:
                decreaseFoulP1();
                break;
            case R.id.btn_playing_team_a_player2_point_plus:
                increaseScoreP2();
                break;
            case R.id.btn_playing_team_a_player2_point_minus:
                decreaseScoreP2();
                break;
            case R.id.btn_playing_team_a_player2_rebound_plus:
                increaseReboundP2();
                break;
            case R.id.btn_playing_team_a_player2_rebound_minus:
                decreaseReboundP2();
                break;
            case R.id.btn_playing_team_a_player2_foul_plus:
                increaseFoulP2();
                break;
            case R.id.btn_playing_team_a_player2_foul_minus:
                decreaseFoulP2();
                break;
            case R.id.btn_playing_team_a_player3_point_plus:
                increaseScoreP3();
                break;
            case R.id.btn_playing_team_a_player3_point_minus:
                decreaseScoreP3();
                break;
            case R.id.btn_playing_team_a_player3_rebound_plus:
                increaseReboundP3();
                break;
            case R.id.btn_playing_team_a_player3_rebound_minus:
                decreaseReboundP3();
                break;
            case R.id.btn_playing_team_a_player3_foul_plus:
                increaseFoulP3();
                break;
            case R.id.btn_playing_team_a_player3_foul_minus:
                decreaseFoulP3();
                break;

            case R.id.btn_playing_team_b_player1_point_plus:
                increaseScoreP4();
                break;
            case R.id.btn_playing_team_b_player1_point_minus:
                decreaseScoreP4();
                break;
            case R.id.btn_playing_team_b_player1_rebound_plus:
                increaseReboundP4();
                break;
            case R.id.btn_playing_team_b_player1_rebound_minus:
                decreaseReboundP4();
                break;
            case R.id.btn_playing_team_b_player1_foul_plus:
                increaseFoulP4();
                break;
            case R.id.btn_playing_team_b_player1_foul_minus:
                decreaseFoulP4();
                break;
            case R.id.btn_playing_team_b_player2_point_plus:
                increaseScoreP5();
                break;
            case R.id.btn_playing_team_b_player2_point_minus:
                decreaseScoreP5();
                break;
            case R.id.btn_playing_team_b_player2_rebound_plus:
                increaseReboundP5();
                break;
            case R.id.btn_playing_team_b_player2_rebound_minus:
                decreaseReboundP5();
                break;
            case R.id.btn_playing_team_b_player2_foul_plus:
                increaseFoulP5();
                break;
            case R.id.btn_playing_team_b_player2_foul_minus:
                decreaseFoulP5();
                break;
            case R.id.btn_playing_team_b_player3_point_plus:
                increaseScoreP6();
                break;
            case R.id.btn_playing_team_b_player3_point_minus:
                decreaseScoreP6();
                break;
            case R.id.btn_playing_team_b_player3_rebound_plus:
                increaseReboundP6();
                break;
            case R.id.btn_playing_team_b_player3_rebound_minus:
                decreaseReboundP6();
                break;
            case R.id.btn_playing_team_b_player3_foul_plus:
                increaseFoulP6();
                break;
            case R.id.btn_playing_team_b_player3_foul_minus:
                decreaseFoulP6();
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setBackKeyDisable(true);

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
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void showPlayingGameUi() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    public int getIntScoreA() {
        mIntScoreA = mIntScoreP1 + mIntScoreP2 + mIntScoreP3;
        return mIntScoreA;
    }

    public int getIntScoreB() {
        mIntScoreB = mIntScoreP4 + mIntScoreP5 + mIntScoreP6;
        return mIntScoreB;
    }

    public void setScorePlusClickableTeamA(boolean clickable) {
        mBtnP1ScorePlus.setClickable(clickable);
        mBtnP2ScorePlus.setClickable(clickable);
        mBtnP3ScorePlus.setClickable(clickable);
    }

    public void setScorePlusClickableTeamB(boolean clickable) {
        mBtnP4ScorePlus.setClickable(clickable);
        mBtnP5ScorePlus.setClickable(clickable);
        mBtnP6ScorePlus.setClickable(clickable);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player1 */

    @Override
    public void increaseScoreP1() {
        if (getIntScoreA() == 6) {
            setScorePlusClickableTeamA(false);
        } else if (mIntScoreP1 < 6 && getIntScoreA() < 6) {
            mIntScoreP1 += 1;
            mTextP1Score.setText(String.valueOf(mIntScoreP1));
            mTeamScoreA.setText(String.valueOf(getIntScoreA()));
            mBtnP1ScoreMinus.setClickable(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP1() {
        if (mIntScoreP1 == 0) {
            mBtnP1ScoreMinus.setClickable(false);
        } else if (0 < getIntScoreA() && getIntScoreA() < 7) {
            mIntScoreP1 -= 1;
            mTextP1Score.setText(String.valueOf(mIntScoreP1));
            mTeamScoreA.setText(String.valueOf(getIntScoreA()));
            setScorePlusClickableTeamA(true);
        } else {
            Log.d("Kerry", "P1 Score error !");
        }
    }

    @Override
    public void increaseReboundP1() {
        if (mIntReboundP1 < 20) {
            mIntReboundP1 += 1;
            mTextP1Rebound.setText(String.valueOf(mIntReboundP1));
            mBtnP1ReboundMinus.setClickable(true);
        } else {
            mBtnP1ReboundPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseReboundP1() {
        if (0 < mIntReboundP1 && mIntReboundP1 < 21) {
            mIntReboundP1 -= 1;
            mTextP1Rebound.setText(String.valueOf(mIntReboundP1));
            mBtnP1ReboundPlus.setClickable(true);
        } else {
            mBtnP1ReboundMinus.setClickable(false);
        }
    }

    @Override
    public void increaseFoulP1() {
        if (mIntFoulP1 < 10) {
            mIntFoulP1 += 1;
            mTextP1Foul.setText(String.valueOf(mIntFoulP1));
            mBtnP1FoulMinus.setClickable(true);
        } else {
            mBtnP1FoulPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseFoulP1() {
        if (0 < mIntFoulP1 && mIntFoulP1 < 11) {
            mIntFoulP1 -= 1;
            mTextP1Foul.setText(String.valueOf(mIntFoulP1));
            mBtnP1FoulPlus.setClickable(true);
        } else {
            mBtnP1FoulMinus.setClickable(false);
        }
    }


    /* ------------------------------------------------------------------------------------------ */
    /* Player2 */

    @Override
    public void increaseScoreP2() {
        if (getIntScoreA() == 6) {
            setScorePlusClickableTeamA(false);
        } else if (mIntScoreP2 < 6 && getIntScoreA() < 6) {
            mIntScoreP2 += 1;
            mTextP2Score.setText(String.valueOf(mIntScoreP2));
            mTeamScoreA.setText(String.valueOf(getIntScoreA()));
            mBtnP2ScoreMinus.setClickable(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP2() {
        if (mIntScoreP2 == 0) {
            mBtnP2ScoreMinus.setClickable(false);
        } else if (0 < getIntScoreA() && getIntScoreA() < 7) {
            mIntScoreP2 -= 1;
            mTextP2Score.setText(String.valueOf(mIntScoreP2));
            mTeamScoreA.setText(String.valueOf(getIntScoreA()));
            setScorePlusClickableTeamA(true);
        } else {
            Log.d("Kerry", "P2 Score error !");
        }
    }

    @Override
    public void increaseReboundP2() {
        if (mIntReboundP2 < 20) {
            mIntReboundP2 += 1;
            mTextP2Rebound.setText(String.valueOf(mIntReboundP2));
            mBtnP2ReboundMinus.setClickable(true);
        } else {
            mBtnP2ReboundPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseReboundP2() {
        if (0 < mIntReboundP2 && mIntReboundP2 < 21) {
            mIntReboundP2 -= 1;
            mTextP2Rebound.setText(String.valueOf(mIntReboundP2));
            mBtnP2ReboundPlus.setClickable(true);
        } else {
            mBtnP2ReboundMinus.setClickable(false);
        }
    }

    @Override
    public void increaseFoulP2() {
        if (mIntFoulP2 < 10) {
            mIntFoulP2 += 1;
            mTextP2Foul.setText(String.valueOf(mIntFoulP2));
            mBtnP2FoulMinus.setClickable(true);
        } else {
            mBtnP2FoulPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseFoulP2() {
        if (0 < mIntFoulP2 && mIntFoulP2 < 11) {
            mIntFoulP2 -= 1;
            mTextP2Foul.setText(String.valueOf(mIntFoulP2));
            mBtnP2FoulPlus.setClickable(true);
        } else {
            mBtnP2FoulMinus.setClickable(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player3 */

    @Override
    public void increaseScoreP3() {
        if (getIntScoreA() == 6) {
            setScorePlusClickableTeamA(false);
        } else if (mIntScoreP3 < 6 && getIntScoreA() < 6) {
            mIntScoreP3 += 1;
            mTextP3Score.setText(String.valueOf(mIntScoreP3));
            mTeamScoreA.setText(String.valueOf(getIntScoreA()));
            mBtnP3ScoreMinus.setClickable(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP3() {
        if (mIntScoreP3 == 0) {
            mBtnP3ScoreMinus.setClickable(false);
        } else if (0 < getIntScoreA() && getIntScoreA() < 7) {
            mIntScoreP3 -= 1;
            mTextP3Score.setText(String.valueOf(mIntScoreP3));
            mTeamScoreA.setText(String.valueOf(getIntScoreA()));
            setScorePlusClickableTeamA(true);
        } else {
            Log.d("Kerry", "P3 Score error !");
        }
    }

    @Override
    public void increaseReboundP3() {
        if (mIntReboundP3 < 20) {
            mIntReboundP3 += 1;
            mTextP3Rebound.setText(String.valueOf(mIntReboundP3));
            mBtnP3ReboundMinus.setClickable(true);
        } else {
            mBtnP3ReboundPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseReboundP3() {
        if (0 < mIntReboundP3 && mIntReboundP3 < 21) {
            mIntReboundP3 -= 1;
            mTextP3Rebound.setText(String.valueOf(mIntReboundP3));
            mBtnP3ReboundPlus.setClickable(true);
        } else {
            mBtnP3ReboundMinus.setClickable(false);
        }
    }

    @Override
    public void increaseFoulP3() {
        if (mIntFoulP3 < 10) {
            mIntFoulP3 += 1;
            mTextP3Foul.setText(String.valueOf(mIntFoulP3));
            mBtnP3FoulMinus.setClickable(true);
        } else {
            mBtnP3FoulPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseFoulP3() {
        if (0 < mIntFoulP3 && mIntFoulP3 < 11) {
            mIntFoulP3 -= 1;
            mTextP3Foul.setText(String.valueOf(mIntFoulP3));
            mBtnP3FoulPlus.setClickable(true);
        } else {
            mBtnP3FoulMinus.setClickable(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player4 */

    @Override
    public void increaseScoreP4() {
        if (getIntScoreB() == 6) {
            setScorePlusClickableTeamB(false);
        } else if (mIntScoreP4 < 6 && getIntScoreB() < 6) {
            mIntScoreP4 += 1;
            mTextP4Score.setText(String.valueOf(mIntScoreP4));
            mTeamScoreB.setText(String.valueOf(getIntScoreB()));
            mBtnP4ScoreMinus.setClickable(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP4() {
        if (mIntScoreP4 == 0) {
            mBtnP4ScoreMinus.setClickable(false);
        } else if (0 < getIntScoreB() && getIntScoreB() < 7) {
            mIntScoreP4 -= 1;
            mTextP4Score.setText(String.valueOf(mIntScoreP4));
            mTeamScoreB.setText(String.valueOf(getIntScoreB()));
            setScorePlusClickableTeamB(true);
        } else {
            Log.d("Kerry", "P4 Score error !");
        }
    }

    @Override
    public void increaseReboundP4() {
        if (mIntReboundP4 < 20) {
            mIntReboundP4 += 1;
            mTextP4Rebound.setText(String.valueOf(mIntReboundP4));
            mBtnP4ReboundMinus.setClickable(true);
        } else {
            mBtnP4ReboundPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseReboundP4() {
        if (0 < mIntReboundP4 && mIntReboundP4 < 21) {
            mIntReboundP4 -= 1;
            mTextP4Rebound.setText(String.valueOf(mIntReboundP4));
            mBtnP4ReboundPlus.setClickable(true);
        } else {
            mBtnP4ReboundMinus.setClickable(false);
        }
    }

    @Override
    public void increaseFoulP4() {
        if (mIntFoulP4 < 10) {
            mIntFoulP4 += 1;
            mTextP4Foul.setText(String.valueOf(mIntFoulP4));
            mBtnP4FoulMinus.setClickable(true);
        } else {
            mBtnP4FoulPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseFoulP4() {
        if (0 < mIntFoulP4 && mIntFoulP4 < 11) {
            mIntFoulP4 -= 1;
            mTextP4Foul.setText(String.valueOf(mIntFoulP4));
            mBtnP4FoulPlus.setClickable(true);
        } else {
            mBtnP4FoulMinus.setClickable(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player5 */

    @Override
    public void increaseScoreP5() {
        if (getIntScoreB() == 6) {
            setScorePlusClickableTeamB(false);
        } else if (mIntScoreP5 < 6 && getIntScoreB() < 6) {
            mIntScoreP5 += 1;
            mTextP5Score.setText(String.valueOf(mIntScoreP5));
            mTeamScoreB.setText(String.valueOf(getIntScoreB()));
            mBtnP5ScoreMinus.setClickable(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP5() {
        if (mIntScoreP5 == 0) {
            mBtnP5ScoreMinus.setClickable(false);
        } else if (0 < getIntScoreB() && getIntScoreB() < 7) {
            mIntScoreP5 -= 1;
            mTextP5Score.setText(String.valueOf(mIntScoreP5));
            mTeamScoreB.setText(String.valueOf(getIntScoreB()));
            setScorePlusClickableTeamB(true);
        } else {
            Log.d("Kerry", "P5 Score error !");
        }
    }

    @Override
    public void increaseReboundP5() {
        if (mIntReboundP5 < 20) {
            mIntReboundP5 += 1;
            mTextP5Rebound.setText(String.valueOf(mIntReboundP5));
            mBtnP5ReboundMinus.setClickable(true);
        } else {
            mBtnP5ReboundPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseReboundP5() {
        if (0 < mIntReboundP5 && mIntReboundP5 < 21) {
            mIntReboundP5 -= 1;
            mTextP5Rebound.setText(String.valueOf(mIntReboundP5));
            mBtnP5ReboundPlus.setClickable(true);
        } else {
            mBtnP5ReboundMinus.setClickable(false);
        }
    }

    @Override
    public void increaseFoulP5() {
        if (mIntFoulP5 < 10) {
            mIntFoulP5 += 1;
            mTextP5Foul.setText(String.valueOf(mIntFoulP5));
            mBtnP5FoulMinus.setClickable(true);
        } else {
            mBtnP5FoulPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseFoulP5() {
        if (0 < mIntFoulP5 && mIntFoulP5 < 11) {
            mIntFoulP5 -= 1;
            mTextP5Foul.setText(String.valueOf(mIntFoulP5));
            mBtnP5FoulPlus.setClickable(true);
        } else {
            mBtnP5FoulMinus.setClickable(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player6 */

    @Override
    public void increaseScoreP6() {
        if (getIntScoreB() == 6) {
            setScorePlusClickableTeamB(false);
        } else if (mIntScoreP6 < 6 && getIntScoreB() < 6) {
            mIntScoreP6 += 1;
            mTextP6Score.setText(String.valueOf(mIntScoreP6));
            mTeamScoreB.setText(String.valueOf(getIntScoreB()));
            mBtnP6ScoreMinus.setClickable(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP6() {
        if (mIntScoreP6 == 0) {
            mBtnP6ScoreMinus.setClickable(false);
        } else if (0 < getIntScoreB() && getIntScoreB() < 7) {
            mIntScoreP6 -= 1;
            mTextP6Score.setText(String.valueOf(mIntScoreP6));
            mTeamScoreB.setText(String.valueOf(getIntScoreB()));
            setScorePlusClickableTeamB(true);
        } else {
            Log.d("Kerry", "P6 Score error !");
        }
    }

    @Override
    public void increaseReboundP6() {
        if (mIntReboundP6 < 20) {
            mIntReboundP6 += 1;
            mTextP6Rebound.setText(String.valueOf(mIntReboundP6));
            mBtnP6ReboundMinus.setClickable(true);
        } else {
            mBtnP6ReboundPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseReboundP6() {
        if (0 < mIntReboundP6 && mIntReboundP6 < 21) {
            mIntReboundP6 -= 1;
            mTextP6Rebound.setText(String.valueOf(mIntReboundP6));
            mBtnP6ReboundPlus.setClickable(true);
        } else {
            mBtnP6ReboundMinus.setClickable(false);
        }
    }

    @Override
    public void increaseFoulP6() {
        if (mIntFoulP6 < 10) {
            mIntFoulP6 += 1;
            mTextP6Foul.setText(String.valueOf(mIntFoulP6));
            mBtnP6FoulMinus.setClickable(true);
        } else {
            mBtnP6FoulPlus.setClickable(false);
        }
    }

    @Override
    public void decreaseFoulP6() {
        if (0 < mIntFoulP6 && mIntFoulP6 < 11) {
            mIntFoulP6 -= 1;
            mTextP6Foul.setText(String.valueOf(mIntFoulP6));
            mBtnP6FoulPlus.setClickable(true);
        } else {
            mBtnP6FoulMinus.setClickable(false);
        }
    }
}
