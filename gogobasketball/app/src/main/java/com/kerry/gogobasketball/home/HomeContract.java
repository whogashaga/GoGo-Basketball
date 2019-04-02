package com.kerry.gogobasketball.home;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.home.item.LookingForRoomFragment;

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        LookingForRoomFragment findRoomsView();

        Activity findMapView();

    }
}
