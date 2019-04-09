package com.kerry.gogobasketball.waiting4join.slave;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlavePresenter implements Waiting4JoinSlaveContract.Presenter {

    private final Waiting4JoinSlaveContract.View mWaiting4JoineView;

    private WaitingRoomInfo mWaitingRoomInfo;


    public Waiting4JoinSlavePresenter(@NonNull Waiting4JoinSlaveContract.View waiting4JoinView) {
        mWaiting4JoineView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoineView.setPresenter(this);
        mWaitingRoomInfo = new WaitingRoomInfo();
    }

    @Override
    public void getHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo) {
        mWaitingRoomInfo = waitingRoomInfo;

        updateMyInfo2FireBase();
//        Log.w("Kerry","host name in wait4join pre = " + hostName);
    }

    @Override
    public void updateMyInfo2FireBase() {

        // 都是 hardcode 的，屆時要帶入 user info
        WaitingRoomSeats waitingRoomSeats = new WaitingRoomSeats();
        waitingRoomSeats.setAvatar("https://graph.facebook.com/2177302648995421/picture?type=large");
        waitingRoomSeats.setPosition("sg");
        waitingRoomSeats.setSort(mWaitingRoomInfo.getWaitingPlayersList().size() + 1);
        waitingRoomSeats.setGender("female");
        waitingRoomSeats.setSeatAvailable(false);
        waitingRoomSeats.setId(GoGoBasketball.getAppContext().getString(R.string.id_player6));

        mWaitingRoomInfo.getWaitingPlayersList().add(waitingRoomSeats);

        mWaiting4JoineView.getRoomInfoFromPresenter(mWaitingRoomInfo);

        // Add a new document with a generated ID
        FirestoreHelper.getFirestore().collection(Constants.WAITING_ROOM)
                .document(mWaitingRoomInfo.getRoomName())
                .set(mWaitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "post my info to waiting room 成功！!");
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
    public void loadPlayersInfoFromFirebase() {

    }

    @Override
    public void loadRefereeInfoFromFirebase() {

    }

    @Override
    public void openGamePlayingOfSlave() {

    }

    @Override
    public void finishWaiting4JoinUi() {

    }

    @Override
    public void start() {

    }
}
