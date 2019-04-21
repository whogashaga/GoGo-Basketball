package com.kerry.gogobasketball.home.item;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

import java.util.ArrayList;

public interface Looking4RoomContract {

    interface View extends BaseView<Presenter> {

        void showRoomsUi();

        void showWaitingRoomListUi(ArrayList<WaitingRoomInfo> roomInfoList);

//        boolean hasNextPaging();

//        int getPaging();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadExistedRoomsData4RecyclerView();

        void setExistedRoomsData();

        boolean isHomeItemHasNextPaging(@MainMvpController.HomeItem String itemType);

        void onHomeItemScrollToBottom(@MainMvpController.HomeItem String itemType);

        void openWant2CreateRoom();

        void openWaiting4JoinSlave(WaitingRoomInfo waitingRoomInfo);

        void showErrorToast(String message, boolean isShort);

        void setRoomListSnapshotListerSlave();

    }
}
