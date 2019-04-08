package com.kerry.gogobasketball.home.item;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

public class Looking4RoomPresenter implements Looking4RoomContract.Presenter {

    private final Looking4RoomContract.View mLookingForRoomView;

    public Looking4RoomPresenter(@NonNull Looking4RoomContract.View lookingForRoomView) {
        mLookingForRoomView = checkNotNull(lookingForRoomView, "catalogItemView cannot be null!");
        mLookingForRoomView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadExistedRoomsData() {

    }

    @Override
    public void setExistedRoomsData() {

    }

    @Override
    public boolean isHomeItemHasNextPaging(String itemType) {
        return false;
    }

    @Override
    public void onHomeItemScrollToBottom(String itemType) {

    }

    @Override
    public void openWant2CreateRoom() {

    }

    @Override
    public void openWaiting4JoinSlave() {

    }

    @Override
    public void start() {

    }
}
