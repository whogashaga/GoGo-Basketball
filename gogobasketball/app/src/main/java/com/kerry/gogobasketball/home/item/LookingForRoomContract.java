package com.kerry.gogobasketball.home.item;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.MainMvpController;

public interface LookingForRoomContract {

    interface View extends BaseView<Presenter> {

        void showRoomsUi();

//        boolean hasNextPaging();

//        int getPaging();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadExistedRoomsData();

        void setExistedRoomsData();

        boolean isHomeItemHasNextPaging(@MainMvpController.HomeItem String itemType);

        void onHomeItemScrollToBottom(@MainMvpController.HomeItem String itemType);

        void openWant2CreateRoom();

//        void openDetail(Product product);
    }
}
