package com.kerry.gogobasketball.home;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.kerry.gogobasketball.home.item.LookingForRoomFragment;

import static com.google.common.base.Preconditions.checkNotNull;

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View homeView){
        mHomeView = checkNotNull(homeView,"mCatalogView cannot be null!");
        mHomeView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public LookingForRoomFragment findRoomsView() {
        return null;
    }

    @Override
    public Fragment findMapView() {
        return null;
    }

    @Override
    public void start() {

    }
}
