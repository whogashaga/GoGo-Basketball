package com.kerry.gogobasketball.playing.player;

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

import com.kerry.gogobasketball.R;

public class PlayerGoingFragment extends Fragment implements PlayerGoingContract.View {

    private PlayerGoingContract.Presenter mPresenter;
    View mRoot;

    public PlayerGoingFragment() {
    }

    public static PlayerGoingFragment newInstance() {
        return new PlayerGoingFragment();
    }

    @Override
    public void setPresenter(PlayerGoingContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("Kerry", "PlayerGoing onResume !");
        mPresenter.hideToolbarAndBottomNavigation();
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_game_playing_player, container, false);

        return mRoot;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setBackKeyDisable(true);
        mPresenter.setGamingNowMessage(true);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Kerry", "PlayerGoing onDestroy !");
        if (mPresenter != null) {
            mPresenter.setGamingNowMessage(false);
        }
    }

    @Override
    public void openGameResultPlayerUi(String hostName, int nowSort) {
        mPresenter.openGameResultPlayer(hostName, nowSort);
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
