package com.kerry.gogobasketball.waiting4join.slave;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

import java.util.ArrayList;

public interface Waiting4JoinSlaveContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showRoomName(WaitingRoomInfo waitingRoomInfo);

        void showWaitingSeatsSlaveUi(ArrayList<WaitingRoomSeats> newSeatsList);

        void closeSlaveUiBecauseMasterOutFirst();

        void openPlayerGamingUi();

        void openRefereeGamingUi();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void showErrorToast(String message, boolean isShort);

        void openGamePlayingOfReferee();

        void openGamePlayingOfPlayer();

        void finishWaiting4JoinUi();

        void getHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo);

        void deleteSeatsInfoWhenLeaveRoom();

        void changeSlave2NewSeat(int newSort);

        void checkTotalPlayerAmountSlave();

        void deleteRoomDocSlave();
    }

}
