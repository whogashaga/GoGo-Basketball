package com.kerry.gogobasketball.home.item;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.GridSpacingItemDecoration;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

import java.util.ArrayList;

public class Looking4RoomFragment extends Fragment implements Looking4RoomContract.View, View.OnClickListener {

    private Looking4RoomContract.Presenter mPresenter;
    private Looking4RoomAdapter mLooking4RoomAdapter;

    private ArrayList<WaitingRoomInfo> mRoomInfoList;
    private Button mBtnBuildRoom;

    public Looking4RoomFragment() {
        mRoomInfoList = new ArrayList<>();
    }

    public static Looking4RoomFragment newInstance() {
        return new Looking4RoomFragment();
    }

    @Override
    public void showRoomsUi() {

    }

    @Override
    public void getWaitingRoomListFromPresenter(ArrayList<WaitingRoomInfo> roomInfoList) {
        mRoomInfoList.addAll(roomInfoList);
        mLooking4RoomAdapter.updateData(mRoomInfoList);
        Log.d("Kerry","list size in fragment = " + mRoomInfoList.size());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_child_rooms, container, false);

        mBtnBuildRoom = root.findViewById(R.id.btn_home_rooms_build);
        mBtnBuildRoom.setOnClickListener(this);

        mLooking4RoomAdapter = new Looking4RoomAdapter(mPresenter);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_home_child_room);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, getContext().getResources().getDimensionPixelSize(R.dimen.space_look4room_grid), true));
        recyclerView.setAdapter(mLooking4RoomAdapter);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home_rooms_build:
                mPresenter.openWant2CreateRoom();
                break;
            default:
                break;
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadExistedRoomsData4RecyclerView();


    }

    @Override
    public void setPresenter(Looking4RoomContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

}
