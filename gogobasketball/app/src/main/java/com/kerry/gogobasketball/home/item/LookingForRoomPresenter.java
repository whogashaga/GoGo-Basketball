package com.kerry.gogobasketball.home.item;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

public class LookingForRoomPresenter implements LookingForRoomContract.Presenter {

    private final LookingForRoomContract.View mLookingForRoomView;

    public LookingForRoomPresenter(@NonNull LookingForRoomContract.View lookingForRoomView) {
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
    public void start() {

    }
}
