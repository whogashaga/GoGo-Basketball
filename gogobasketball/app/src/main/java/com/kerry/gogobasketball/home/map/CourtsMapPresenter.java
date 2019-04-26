package com.kerry.gogobasketball.home.map;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

public class CourtsMapPresenter implements CourtsMapContract.Presenter {


    private CourtsMapContract.View mCourtsMapView;


    public CourtsMapPresenter(@NonNull CourtsMapContract.View mapView) {
        mCourtsMapView = checkNotNull(mapView, "mapView cannot be null!");
        mCourtsMapView.setPresenter(this);
    }


    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
