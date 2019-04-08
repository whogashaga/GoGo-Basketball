package com.kerry.gogobasketball.want2create;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.data.WaitingRoomInfo;

public class Want2CreateRoomPresenter implements Want2CreateRoomContract.Presenter {

    private final Want2CreateRoomContract.View mWant2CreateRoomView;

    private WaitingRoomInfo mWaitingRoomInfo;

    public Want2CreateRoomPresenter(@NonNull Want2CreateRoomContract.View want2CreateRoomView) {
        mWant2CreateRoomView = checkNotNull(want2CreateRoomView, "Want2CreateRoomView cannot be null!");
        mWant2CreateRoomView.setPresenter(this);

        mWaitingRoomInfo = new WaitingRoomInfo();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public void updateRoomName2Firebase() {

    }

    @Override
    public void getCourtLocationFromSpinner(String courtLocation) {
        mWaitingRoomInfo.setCourtLocation(courtLocation);
    }

    @Override
    public void openWaitingJoin() {

    }

    @Override
    public void finishWant2CreateRoomUi() {

    }

    @Override
    public void onRoomNameEditTextChange(CharSequence roomName) {
//        Log.d("Kerry", "room name = " + roomName.length());
            mWaitingRoomInfo.setRoomName(roomName.toString());
    }

    @Override
    public void start() {

    }
}
