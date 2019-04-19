package com.kerry.gogobasketball.waiting4join.slave;

import android.app.Activity;

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

        void openPlayerGamingUi(String hostName, int nowSort);

        void openRefereeGamingUi(String hostName);
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void setBackKeyDisable(boolean isBackKeyDisable);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void showErrorToast(String message, boolean isShort);

        void openGamePlayingOfReferee(String hostName);

        void openGamePlayingOfPlayer(String hostName, int nowSort);

        void finishWaiting4JoinUi();

        void getHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo);

        void deleteSeatsInfoWhenLeaveRoom();

        void changeSlave2NewSeat(int newSort);

        void checkTotalPlayerAmountSlave();

        void deleteRoomDocSlave();

        void setActivityBackgroundLandScape();

        void setActivityBackgroundPortrait();

        void getProfileUserData(Activity activity);

        void removeListenerSlave();
    }

}
