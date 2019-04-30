package com.kerry.gogobasketball.home.map;

import android.app.Activity;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

import java.util.ArrayList;

public interface CourtsMapContract {

    interface View extends BaseView<Presenter> {

        void getPopulationFromPresenter(ArrayList<String> populationList);

        void refreshMarkers();

        void initMap();

        void getLocationPermissionGranted(boolean locationPermissionGranted);

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void getCurrentCourtPopulation();

        void setOnPopulationChangeListener();

        void removeListener();

        void getLocationPermission(Activity activity);

    }
}