package com.kerry.gogobasketball.waiting4join.master;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

import java.util.ArrayList;

public interface Waiting4JoinMasterContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void openGamingUi(GamingRoomInfo gamingRoomInfo);

        void openUserDetailUi(String userId);

        void getNewPlayerAmount(int newPlayerAmount, int nowMasterSort);

        void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo);

        void showWaitingSeatsMasterUi(ArrayList<WaitingRoomSeats> newSeatsList);

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void setBackKeyDisable(boolean isBackKeyDisable);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void setActivityBackgroundLandScape();

        void setActivityBackgroundPortrait();

        void openGamePlayingOfReferee(String hostName);

        void openGamePlayingOfPlayer(String hostName, int nowSort);

        void finishWaiting4JoinUi();

        void setRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatsInfo, String roomDocId);

        void showErrorToast(String message, boolean isShort);

        void changeMaster2NewSeat(int newSort);

        void deleteHostSeatWhenLeave();

        void initializeGamingRoomInfo();

        void removeListenerMaster();

        void openInstructionDialog();

        void openUserDetailDialog(String userId);

        void openUserDetailMaster(int sort);

        void kickOutPlayer(int sort);
    }

}
