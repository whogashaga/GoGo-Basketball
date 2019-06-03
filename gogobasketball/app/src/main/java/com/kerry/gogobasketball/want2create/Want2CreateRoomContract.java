package com.kerry.gogobasketball.want2create;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

import java.util.ArrayList;

public interface Want2CreateRoomContract {

    interface View extends BaseView<Presenter> {

        void openWaitingJoinMasterUi(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats waitingRoomSeats, String roomDocId);

        void setCreateRoomBtnClickable();

        void setSpinnerCourts(ArrayList<String> courtsList);

        boolean needReferee();

        boolean isActive();

        void closeProgressDialogUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void setWant2CreateNow(boolean isCreatingNow);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void setRoomInfo();

        void updateHostSeat2Cloud(WaitingRoomSeats hostPlayer, String roomDocId);

        void onRoomNameEditTextChange(CharSequence charSequence);

        void getSpinnerCourtLocation(String courtLocation);

        void getRadioRefereeMode(boolean needReferee);

        void openWaitingJoinMaster(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId);

        void finishWant2CreateRoomUi();

        void setActivityBackgroundLandScape();

        void setActivityBackgroundPortrait();

        void loadHostUserData();

        void setCreateBtnClickable();

        void getCourtsListFromCloud();

        void openProgressDialog();

        void closeProgressDialog();
    }
}
