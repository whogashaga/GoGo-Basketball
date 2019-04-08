package com.kerry.gogobasketball.home;

import static com.google.common.base.Preconditions.checkNotNull;

import com.kerry.gogobasketball.home.item.Looking4RoomFragment;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View homeView) {
        mHomeView = checkNotNull(homeView, "mCatalogView cannot be null!");
        mHomeView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public Looking4RoomFragment findRoomsView() {
        return null;
    }

    @Override
    public CourtsMapFragment findMapView() {
        return null;
    }

    @Override
    public void start() {

    }
}
