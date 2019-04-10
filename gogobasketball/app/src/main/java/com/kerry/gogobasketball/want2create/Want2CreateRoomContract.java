package com.kerry.gogobasketball.want2create;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

public interface Want2CreateRoomContract {

    interface View extends BaseView<Presenter> {

        void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats waitingRoomSeats);

        boolean needReferee();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void updateWaitingRoomInfo2FireBase();

        void getCourtLocationFromSpinner(String courtLocation);

        void getRefereeOnOffFromRadioGroup(boolean needReferee);

        void openWaitingJoin(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo);

        void finishWant2CreateRoomUi();

        void onRoomNameEditTextChange(CharSequence charSequence);

    }
}
