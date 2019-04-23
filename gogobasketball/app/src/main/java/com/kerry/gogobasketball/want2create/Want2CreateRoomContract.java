package com.kerry.gogobasketball.want2create;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

public interface Want2CreateRoomContract {

    interface View extends BaseView<Presenter> {

        void getRoomInfoFromPresenter4NextFragment(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats waitingRoomSeats, String roomDocId);

        void setCreateRoomBtnClickable();

        boolean needReferee();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showErrorToast(String message, boolean isShort);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void updateRoomInfo2FireStore();

        void updateUserInfo2FireBase(WaitingRoomSeats hostPlayer, String roomDocId);

        void getCourtLocationFromSpinner(String courtLocation);

        void getRefereeOnOffFromRadioGroup(boolean needReferee);

        void openWaitingJoin(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId);

        void finishWant2CreateRoomUi();

        void onRoomNameEditTextChange(CharSequence charSequence);

        void setActivityBackgroundLandScape();

        void setActivityBackgroundPortrait();

        void loadProfileUserDataWant2Create(Activity activity);

        void setCreateBtnClickable();
    }
}
