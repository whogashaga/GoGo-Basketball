package com.kerry.gogobasketball.want2create;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

public interface Want2CreateRoomContract {

    interface View extends BaseView<Presenter> {

        void getRoomInfoFromPresenter(WaitingRoomInfo waitingRoomInfo);

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

        void openWaitingJoin(WaitingRoomInfo waitingRoomInfo);

        void finishWant2CreateRoomUi();

        void onRoomNameEditTextChange(CharSequence charSequence);

    }
}
