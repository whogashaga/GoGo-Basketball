package com.kerry.gogobasketball.waiting4join.master;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.waiting4join.master.Waiting4JoinMasterContract;

public class Waiting4JoinMasterPresenter implements Waiting4JoinMasterContract.Presenter {

    private final Waiting4JoinMasterContract.View mWaiting4JoineView;

    private WaitingRoomInfo mWaitingRoomInfo;

    public Waiting4JoinMasterPresenter(@NonNull Waiting4JoinMasterContract.View waiting4JoinView) {
        mWaiting4JoineView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoineView.setPresenter(this);

        mWaitingRoomInfo = new WaitingRoomInfo();
    }

    @Override
    public void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatsInfo) {
//        mWaitingRoomInfo = waitingRoomInfo;
        mWaiting4JoineView.getRoomInfoFromPresenterMaster(waitingRoomInfo, hostSeatsInfo);
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public void loadPlayersInfoFromFirebase() {

    }

    @Override
    public void loadRefereeInfoFromFirebase() {

    }

    @Override
    public void openGamePlayingOfReferee() {

    }

    @Override
    public void finishWaiting4JoinUi() {

    }

    @Override
    public void start() {

    }
}
