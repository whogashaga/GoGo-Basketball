package com.kerry.gogobasketball.home.map;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

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
