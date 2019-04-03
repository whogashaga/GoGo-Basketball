package com.kerry.gogobasketball.home.item;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

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
    public void start() {

    }
}
