package com.kerry.gogobasketball.waiting4join;

import android.support.v4.app.Fragment;

public class Waiting4JoinFragment extends Fragment implements Waiting4JoinContract.View {


    @Override
    public void showWaiting4JoinUi() {

    }

    @Override
    public boolean needTimer() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(Waiting4JoinContract.Presenter presenter) {

    }
}
