package com.kerry.gogobasketball.want2create;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
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

    private String mRoomDocId;

    public Want2CreateRoomPresenter(@NonNull Want2CreateRoomContract.View want2CreateRoomView) {
        mWant2CreateRoomView = checkNotNull(want2CreateRoomView, "Want2CreateRoomView cannot be null!");
        mWant2CreateRoomView.setPresenter(this);

        mWaitingRoomInfo = new WaitingRoomInfo();
        mRoomDocId = "";
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
    public void updateRoomInfo2FireStore() {

        //  set Host Player Info (hardcode，屆時要帶入 user info)
        WaitingRoomSeats hostSeatInfo = new WaitingRoomSeats();
        hostSeatInfo.setAvatar("https://graph.facebook.com/2177302648995421/picture?type=large");
        hostSeatInfo.setPosition("pg");
        hostSeatInfo.setSort(0);
        hostSeatInfo.setGender("male");
        hostSeatInfo.setSeatAvailable(false);
        hostSeatInfo.setId(GoGoBasketball.getAppContext().getString(R.string.id_player1));

        // set Room Info
        WaitingRoomInfo waitingRoomInfo = new WaitingRoomInfo();
        waitingRoomInfo.setJustice(mWaitingRoomInfo.getJustice());
        waitingRoomInfo.setCourtLocation(mWaitingRoomInfo.getCourtLocation());
        waitingRoomInfo.setRoomName(mWaitingRoomInfo.getRoomName());
        waitingRoomInfo.setHostName(hostSeatInfo.getId());
        waitingRoomInfo.setPlayerAmount(1);
        waitingRoomInfo.setRefereeAmount(0);

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .add(waitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        mRoomDocId = documentReference.getId();
                        // for open waiting4join bind view
                        mWant2CreateRoomView.getRoomInfoFromPresenter(waitingRoomInfo, hostSeatInfo, documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Kerry", "Error adding document", e);
                    }
                });

    }

    @Override
    public void updateUserInfo2FireBase(WaitingRoomSeats hostPlayer, String roomDocId) {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(String.valueOf(hostPlayer.getSort()))
                .set(hostPlayer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "等待中 - set seats info ！!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }

        });
    }

    /* ------------------------------------------------------------------------------------------ */
    /* implement in MainPresenter */

    @Override
    public void openWaitingJoin(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {

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
    public void finishWant2CreateRoomUi() {

    }


    @Override
    public void start() {

    }
}
