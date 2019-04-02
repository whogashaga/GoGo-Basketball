package com.kerry.gogobasketball.home;

import android.support.v4.app.Fragment;

import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.home.HomeContract;

public class HomeFragment extends Fragment implements HomeContract.View {



    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }



}
