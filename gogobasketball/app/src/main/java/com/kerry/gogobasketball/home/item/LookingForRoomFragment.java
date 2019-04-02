package com.kerry.gogobasketball.home.item;

import android.support.v4.app.Fragment;

import com.kerry.gogobasketball.MainMvpController;

import static com.google.common.base.Preconditions.checkNotNull;

public class LookingForRoomFragment extends Fragment implements LookingForRoomContract.View {

    private LookingForRoomContract.Presenter mPresenter;

    private String mItemType;

    public LookingForRoomFragment(){}

    public static LookingForRoomFragment newInstance() {
        return new LookingForRoomFragment();
    }

    @Override
    public void showRoomsUi() {

    }

    @Override
    public void setPresenter(LookingForRoomContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public void setItemType(@MainMvpController.HomeItem String itemType) {
        mItemType = itemType;
    }

}
