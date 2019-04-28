package com.kerry.gogobasketball.home.map;

import com.kerry.gogobasketball.BasePresenter;
import com.kerry.gogobasketball.BaseView;

import java.util.ArrayList;

public interface CourtsMapContract {

    interface View extends BaseView<Presenter> {

        void getPopulationFromPresenter(ArrayList<String> populationList);

        void refreshMarkers();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void getCurrentCourtPopulation();

        void setOnPopulationChangeListener();

        void removeListener();

    }
}