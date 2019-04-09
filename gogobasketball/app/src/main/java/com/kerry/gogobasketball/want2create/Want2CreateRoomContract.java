package com.kerry.gogobasketball.want2create;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

public interface Want2CreateRoomContract {

    interface View extends BaseView<Presenter> {

        void showWant2CreateRoomUi();

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

        void openWaitingJoin();

        void finishWant2CreateRoomUi();

        void onRoomNameEditTextChange(CharSequence charSequence);

    }
}
