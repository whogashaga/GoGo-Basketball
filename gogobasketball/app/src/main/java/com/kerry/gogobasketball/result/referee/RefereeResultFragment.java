package com.kerry.gogobasketball.result.referee;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.kerry.gogobasketball.util.ImageManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class RefereeResultFragment extends Fragment implements RefereeResultContract.View, View.OnClickListener {

    private RefereeResultContract.Presenter mPresenter;
    View mRoot;
    private Button mBtnBackLobby;

    private TextView mTextWinner, mTextScoreA, mTextScoreB;
    private ImageView mAvatarP1;
    private TextView mTextIdP1;
    private TextView mTextP1Score, mTextP1Rebound, mTextP1Foul;
    private ImageView mAvatarP2;
    private TextView mTextIdP2;
    private TextView mTextP2Score, mTextP2Rebound, mTextP2Foul;
    private ImageView mAvatarP3;
    private TextView mTextIdP3;
    private TextView mTextP3Score, mTextP3Rebound, mTextP3Foul;
    private ImageView mAvatarP4;
    private TextView mTextIdP4;
    private TextView mTextP4Score, mTextP4Rebound, mTextP4Foul;
    private ImageView mAvatarP5;
    private TextView mTextIdP5;
    private TextView mTextP5Score, mTextP5Rebound, mTextP5Foul;
    private ImageView mAvatarP6;
    private TextView mTextIdP6;
    private TextView mTextP6Score, mTextP6Rebound, mTextP6Foul;

    private GamingRoomInfo mGamingRoomResult;

    public RefereeResultFragment() {
        mGamingRoomResult = new GamingRoomInfo();
    }

    public static RefereeResultFragment newInstance() {
        return new RefereeResultFragment();
    }

    @Override
    public void getHostNameFromPresenter(String hostName) {
        mPresenter.getRoomInfoFromFireStore(hostName);
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

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_game_result_referee, container, false);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        mRoot.getBackground().setAlpha(200);

        mAvatarP1 = mRoot.findViewById(R.id.result_referee_team_a_player1_avatar);
        mAvatarP1.setOutlineProvider(new SeatAvatarOutlineProvider());
        mTextIdP1 = mRoot.findViewById(R.id.result_referee_team_a_player1_id);
        mTextP1Score = mRoot.findViewById(R.id.text_result_referee_score_team_a_player1);
        mTextP1Rebound = mRoot.findViewById(R.id.text_result_referee_rebound_team_a_player1);
        mTextP1Foul = mRoot.findViewById(R.id.text_result_referee_foul_team_a_player1);

        mAvatarP2 = mRoot.findViewById(R.id.result_referee_team_a_player2_avatar);
        mAvatarP2.setOutlineProvider(new SeatAvatarOutlineProvider());
        mTextIdP2 = mRoot.findViewById(R.id.result_referee_team_a_player2_id);
        mTextP2Score = mRoot.findViewById(R.id.text_result_referee_score_team_a_player2);
        mTextP2Rebound = mRoot.findViewById(R.id.text_result_referee_rebound_team_a_player2);
        mTextP2Foul = mRoot.findViewById(R.id.text_result_referee_foul_team_a_player2);

        mAvatarP3 = mRoot.findViewById(R.id.result_referee_team_a_player3_avatar);
        mAvatarP3.setOutlineProvider(new SeatAvatarOutlineProvider());
        mTextIdP3 = mRoot.findViewById(R.id.result_referee_team_a_player3_id);
        mTextP3Score = mRoot.findViewById(R.id.text_result_referee_score_team_a_player3);
        mTextP3Rebound = mRoot.findViewById(R.id.text_result_referee_rebound_team_a_player3);
        mTextP3Foul = mRoot.findViewById(R.id.text_result_referee_foul_team_a_player3);

        mAvatarP4 = mRoot.findViewById(R.id.result_referee_team_b_player1_avatar);
        mAvatarP4.setOutlineProvider(new SeatAvatarOutlineProvider());
        mTextIdP4 = mRoot.findViewById(R.id.result_referee_team_b_player1_id);
        mTextP4Score = mRoot.findViewById(R.id.text_result_referee_score_team_b_player1);
        mTextP4Rebound = mRoot.findViewById(R.id.text_result_referee_rebound_team_b_player1);
        mTextP4Foul = mRoot.findViewById(R.id.text_result_referee_foul_team_b_player1);

        mAvatarP5 = mRoot.findViewById(R.id.result_referee_team_b_player2_avatar);
        mAvatarP5.setOutlineProvider(new SeatAvatarOutlineProvider());
        mTextIdP5 = mRoot.findViewById(R.id.result_referee_team_b_player2_id);
        mTextP5Score = mRoot.findViewById(R.id.text_result_referee_score_team_b_player2);
        mTextP5Rebound = mRoot.findViewById(R.id.text_result_referee_rebound_team_b_player2);
        mTextP5Foul = mRoot.findViewById(R.id.text_result_referee_foul_team_b_player2);

        mAvatarP6 = mRoot.findViewById(R.id.result_referee_team_b_player3_avatar);
        mAvatarP6.setOutlineProvider(new SeatAvatarOutlineProvider());
        mTextIdP6 = mRoot.findViewById(R.id.result_referee_team_b_player3_id);
        mTextP6Score = mRoot.findViewById(R.id.text_result_referee_score_team_b_player3);
        mTextP6Rebound = mRoot.findViewById(R.id.text_result_referee_rebound_team_b_player3);
        mTextP6Foul = mRoot.findViewById(R.id.text_result_referee_foul_team_b_player3);

        mTextWinner = mRoot.findViewById(R.id.text_referee_going_title_start);
        mTextScoreA = mRoot.findViewById(R.id.text_result_referee_score_team_a);
        mTextScoreB = mRoot.findViewById(R.id.text_result_referee_score_team_b);
        mBtnBackLobby = mRoot.findViewById(R.id.btn_result_referee_back_lobby);
        mBtnBackLobby.setOnClickListener(this);

        return mRoot;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_result_referee_back_lobby:
                mPresenter.deleteGamingRoom();
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mPresenter.openHome();
                mPresenter.showToolbarAndBottomNavigation();
                mPresenter.setActivityBackgroundPortrait();
                break;
        }
    }

    @Override
    public void showResultRefereeUi(GamingRoomInfo gamingRoomInfo) {
        mGamingRoomResult = gamingRoomInfo;
        setPlayerInfo(gamingRoomInfo.getPlayer1(), mAvatarP1, mTextIdP1, mTextP1Score, mTextP1Rebound, mTextP1Foul);
        setPlayerInfo(gamingRoomInfo.getPlayer2(), mAvatarP2, mTextIdP2, mTextP2Score, mTextP2Rebound, mTextP2Foul);
        setPlayerInfo(gamingRoomInfo.getPlayer3(), mAvatarP3, mTextIdP3, mTextP3Score, mTextP3Rebound, mTextP3Foul);
        setPlayerInfo(gamingRoomInfo.getPlayer4(), mAvatarP4, mTextIdP4, mTextP4Score, mTextP4Rebound, mTextP4Foul);
        setPlayerInfo(gamingRoomInfo.getPlayer5(), mAvatarP5, mTextIdP5, mTextP5Score, mTextP5Rebound, mTextP5Foul);
        setPlayerInfo(gamingRoomInfo.getPlayer6(), mAvatarP6, mTextIdP6, mTextP6Score, mTextP6Rebound, mTextP6Foul);

        if (gamingRoomInfo.getWinner().equals("a")){
            mTextWinner.setText("Red");
            mTextWinner.setTextColor(Color.RED);
        } else {
            mTextWinner.setText("Blue");
            mTextWinner.setTextColor(Color.BLUE);
        }
        mTextScoreA.setText(String.valueOf(gamingRoomInfo.getScoreA()));
        mTextScoreB.setText(String.valueOf(gamingRoomInfo.getScoreB()));

    }

    private void setPlayerInfo(GamingPlayer gamingPlayer, ImageView avatar, TextView textId,
                               TextView score, TextView rebound, TextView foul) {
        ImageManager.getInstance().setImageByUrl(avatar, gamingPlayer.getAvatar());
        textId.setText(gamingPlayer.getId());
        score.setText(String.valueOf(gamingPlayer.getScore()));
        rebound.setText(String.valueOf(gamingPlayer.getRebound()));
        foul.setText(String.valueOf(gamingPlayer.getFoul()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setBackKeyDisable(true);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPresenter(RefereeResultContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return false;
    }

}
