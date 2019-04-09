package com.kerry.gogobasketball.want2create;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

public class Want2CreateRoomPresenter implements Want2CreateRoomContract.Presenter {

    private final Want2CreateRoomContract.View mWant2CreateRoomView;

    private WaitingRoomInfo mWaitingRoomInfo;



    public Want2CreateRoomPresenter(@NonNull Want2CreateRoomContract.View want2CreateRoomView) {
        mWant2CreateRoomView = checkNotNull(want2CreateRoomView, "Want2CreateRoomView cannot be null!");
        mWant2CreateRoomView.setPresenter(this);

        mWaitingRoomInfo = new WaitingRoomInfo();
    }

    @Override
    public void getCourtLocationFromSpinner(String courtLocation) {
        mWaitingRoomInfo.setCourtLocation(courtLocation);
    }

    @Override
    public void getRefereeOnOffFromRadioGroup(boolean justice) {
        mWaitingRoomInfo.setJustice(justice);
    }

    @Override
    public void onRoomNameEditTextChange(CharSequence roomName) {
//        Log.d("Kerry", "room name = " + roomName.length());
        mWaitingRoomInfo.setRoomName(roomName.toString());
    }

    @Override
    public void updateWaitingRoomInfo2FireBase() {

        // 都是 hardcode 的，屆時要帶入 user info
        WaitingRoomSeats waitingRoomSeats = new WaitingRoomSeats();
        waitingRoomSeats.setAvatar("https://graph.facebook.com/2177302648995421/picture?type=large");
        waitingRoomSeats.setPosition("c");
        waitingRoomSeats.setSort(0);
        waitingRoomSeats.setGender("male");
        waitingRoomSeats.setSeatAvailable(true);
        waitingRoomSeats.setId(GoGoBasketball.getAppContext().getString(R.string.id_player4));

        ArrayList<WaitingRoomSeats> waitingSeatList = new ArrayList<>();
        waitingSeatList.add(waitingRoomSeats);

        WaitingRoomInfo waitingRoomInfo = new WaitingRoomInfo();
        waitingRoomInfo.setJustice(mWaitingRoomInfo.getJustice());
        waitingRoomInfo.setCourtLocation(mWaitingRoomInfo.getCourtLocation());
        waitingRoomInfo.setRoomName(mWaitingRoomInfo.getRoomName());
        waitingRoomInfo.setHostName(waitingRoomSeats.getId());
        waitingRoomInfo.setPlayerAmount(1);
        waitingRoomInfo.setRefereeAmount(0);
        waitingRoomInfo.setWaitingPlayersList(waitingSeatList);

        mWant2CreateRoomView.getRoomInfoFromPresenter(waitingRoomInfo);

        // Add a new document with a generated ID
        FirestoreHelper.getFirestore().collection(Constants.WAITING_ROOM)
                .document(mWaitingRoomInfo.getRoomName())
                .set(waitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "創建 waiting room 成功！!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });


    }

    /* ------------------------------------------------------------------------------------------ */

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
    public void openWaitingJoin(WaitingRoomInfo waitingRoomInfo) {

    }

    @Override
    public void finishWant2CreateRoomUi() {

    }

    @Override
    public void start() {

    }
}
