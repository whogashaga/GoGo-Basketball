package com.kerry.gogobasketball.home.map;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.home.item.LookingForRoomContract;

public interface CourtsMapContract {

    interface View extends BaseView<Presenter> {

        void showMapsUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

//    void loadExistedRoomsData();

//    void setExistedRoomsData();

//        void openDetail(Product product);

    }
}