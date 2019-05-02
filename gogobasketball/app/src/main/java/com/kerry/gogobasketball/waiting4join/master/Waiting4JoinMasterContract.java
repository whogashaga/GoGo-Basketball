package com.kerry.gogobasketball.waiting4join.master;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

import java.util.ArrayList;

public interface Waiting4JoinMasterContract {

    interface View extends BaseView<Presenter> {

        boolean needTimer();

        boolean isActive();

        void getNewPlayerAmount(int newPlayerAmount, int nowMasterSort);

        void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo);

        void showWaitingSeatsMasterUi(ArrayList<WaitingRoomSeats> newSeatsList);

        void getGamingRoomInfoFromPresenter4GamingFragment(GamingRoomInfo gamingRoomInfo);

        void openUserDetailUi(String userId);

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void setBackKeyDisable(boolean isBackKeyDisable);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void openGamePlayingOfReferee(String hostName);

        void openGamePlayingOfPlayer(String hostName, int nowSort);

        void finishWaiting4JoinUi();

        void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatsInfo, String roomDocId);

        void showErrorToast(String message, boolean isShort);

        void changeMaster2NewSeat(int newSort);

//        void changeRoomPlayerAmountAfterChangeSeatMaster();

        void deleteHostInfoWhenLeave();

        void updateRoomStatus2Gaming(GamingRoomInfo gamingRoomInfo);

        void initializeGamingRoomInfo();

        void setActivityBackgroundLandScape();

        void setActivityBackgroundPortrait();

        void removeListenerMaster();

        void openInstructionDialog();

        void openUserDetailDialog(String userId);

        void openUserDetailMaster(int sort);
    }

}
