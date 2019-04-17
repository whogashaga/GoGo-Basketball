package com.kerry.gogobasketball.home;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;
import com.kerry.gogobasketball.home.item.Looking4RoomFragment;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        Looking4RoomFragment findRoomsView();

        CourtsMapFragment findMapView();

        void hideToolbarAndBottomNavigation();

        void showToolbarAndBottomNavigation();

    }
}
