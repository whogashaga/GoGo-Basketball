package com.kerry.gogobasketball.playing0referee;

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

public class GamePlayingOfRefereeFragment extends Fragment implements GamePlayingOfRefereeContract.View, View.OnClickListener {

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

    private GamePlayingOfRefereeContract.Presenter mPresenter;

    public GamePlayingOfRefereeFragment() {

    }

    public static GamePlayingOfRefereeFragment newInstance() {
        return new GamePlayingOfRefereeFragment();
    }

    @Override
    public void setPresenter(GamePlayingOfRefereeContract.Presenter presenter) {
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
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnP1ScorePlus.setOnClickListener(this);
        mBtnP1ScoreMinus.setOnClickListener(this);
        mBtnP1ReboundPlus.setOnClickListener(this);
        mBtnP1ReboundMinus.setOnClickListener(this);
        mBtnP1FoulPlus.setOnClickListener(this);
        mBtnP1FoulMinus.setOnClickListener(this);
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


    /* ------------------------------------------------------------------------------------------ */
    /* Player1 */

    @Override
    public void increaseScoreP1() {
        if (mIntScoreP1 < 6) {
            mIntScoreP1 += 1;
            mTextP1Score.setText(String.valueOf(mIntScoreP1));
            mBtnP1ScoreMinus.setClickable(true);
        } else {
            mBtnP1ScorePlus.setClickable(false);
        }
    }

    @Override
    public void decreaseScoreP1() {
        if (0 < mIntScoreP1 && mIntScoreP1 < 7) {
            mIntScoreP1 -= 1;
            mTextP1Score.setText(String.valueOf(mIntScoreP1));
            mBtnP1ScorePlus.setClickable(true);
        } else {
            mBtnP1ScoreMinus.setClickable(false);
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

}
