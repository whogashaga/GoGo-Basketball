package com.kerry.gogobasketball.home.item;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.api.LogDescriptor;
import com.google.type.LatLng;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.component.GridSpacingItemDecoration;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.LocationManager;

import java.util.ArrayList;

public class Looking4RoomFragment extends Fragment implements Looking4RoomContract.View,
        View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private Looking4RoomContract.Presenter mPresenter;
    private Looking4RoomAdapter mLooking4RoomAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<WaitingRoomInfo> mRoomInfoList;
    private Button mBtnBuildRoom;
    private Button mBtnFindHost;
    private Button mBtnFilter;

    public Looking4RoomFragment() {
        mRoomInfoList = new ArrayList<>();
    }

    public static Looking4RoomFragment newInstance() {
        return new Looking4RoomFragment();
    }

    @Override
    public void showWaitingRoomListUi(ArrayList<WaitingRoomInfo> roomInfoList) {
        mRoomInfoList.clear();
        mRoomInfoList.addAll(roomInfoList);
        mLooking4RoomAdapter.updateData(mRoomInfoList);
//        Log.d(Constants.TAG,"list size in fragment = " + mRoomInfoList.size());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(Constants.TAG, "Looking4RoomFragment onResume !");
        Log.i("Lifecycle", "Looking4RoomFragment onResume !");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_child_rooms, container, false);

        mBtnBuildRoom = root.findViewById(R.id.btn_home_rooms_build);
        mBtnBuildRoom.setOnClickListener(this);

        mBtnFindHost = root.findViewById(R.id.btn_home_rooms_find_host);
        mBtnFindHost.setOnClickListener(this);

        mBtnFilter = root.findViewById(R.id.btn_home_rooms_filter);
        mBtnFilter.setOnClickListener(this);

        // SwipeRefreshLayout
        mSwipeRefreshLayout = root.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

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

            case R.id.btn_home_rooms_find_host:
                mPresenter.openFindHostDialog();
                break;

            case R.id.btn_home_rooms_filter:
                mPresenter.openCourtsFilterDialog();
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
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        mPresenter.loadExistedRoomsData4RecyclerView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "Looking4RoomFragment onDestroy !");
        Log.d("Lifecycle", "Looking4RoomFragment onDestroy !");
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