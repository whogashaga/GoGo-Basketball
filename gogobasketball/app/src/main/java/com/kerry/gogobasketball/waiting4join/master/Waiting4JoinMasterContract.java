package com.kerry.gogobasketball.waiting4join.master;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

import java.util.ArrayList;

public interface Waiting4JoinMasterContract {

    interface View extends BaseView<Presenter> {

        boolean needTimer();

        boolean isActive();

        void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo);

        void showWaitingSeatsMasterUi(ArrayList<WaitingRoomSeats> newSeatsList);

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void loadPlayersInfoFromFirebase();

        void loadRefereeInfoFromFirebase();

        void openGamePlayingOfReferee();

        void openGamePlayingOfPlayer();

        void finishWaiting4JoinUi();

        void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatsInfo, String roomDocId);

        void showErrorToast(String message);

        void changeMaster2NewSeat(int newSort);

        void deleteHostInfoWhenLeave();

    }

}
