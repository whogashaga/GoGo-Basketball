package com.kerry.gogobasketball.home.map;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class CourtsMapPresenter implements CourtsMapContract.Presenter {


    private CourtsMapContract.View mMapView;


    public CourtsMapPresenter(@NonNull CourtsMapContract.View oderItemView) {

        mMapView = checkNotNull(oderItemView, "OrderItemView cannot be null!");
        mMapView.setPresenter(this);
    }


    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
