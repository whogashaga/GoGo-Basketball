package com.kerry.gogobasketball.result;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;

public interface GameResultContract {

    interface View extends BaseView<Presenter> {

        void getResultFromPresenter();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

        void openWaitingJoin(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId);

        void finishResultUi();

    }
}
