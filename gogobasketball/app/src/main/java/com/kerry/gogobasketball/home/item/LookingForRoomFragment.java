package com.kerry.gogobasketball.home.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class LookingForRoomFragment extends Fragment implements LookingForRoomContract.View, View.OnClickListener {

    private LookingForRoomContract.Presenter mPresenter;


    private String mItemType;
    private Button mBtnBuildRoom;

    public LookingForRoomFragment(){}

    public static LookingForRoomFragment newInstance() {
        return new LookingForRoomFragment();
    }

    @Override
    public void showRoomsUi() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_child_rooms, container, false);

        mBtnBuildRoom = root.findViewById(R.id.btn_home_rooms_build);
        mBtnBuildRoom.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home_rooms_build:
                mPresenter.openWant2CreateRoom();
                break;
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setPresenter(LookingForRoomContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public void setItemType(@MainMvpController.HomeItem String itemType) {
        mItemType = itemType;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }


}
