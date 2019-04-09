package com.kerry.gogobasketball.waiting4join.slave;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
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
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlavePresenter implements Waiting4JoinSlaveContract.Presenter {

    private final Waiting4JoinSlaveContract.View mWaiting4JoineView;

    private WaitingRoomInfo mWaitingRoomInfo;
    private String mDocId;

    public Waiting4JoinSlavePresenter(@NonNull Waiting4JoinSlaveContract.View waiting4JoinView) {
        mWaiting4JoineView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoineView.setPresenter(this);
        mWaitingRoomInfo = new WaitingRoomInfo();
        mDocId = "";
    }

    @Override
    public void getHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo) {
        mWaitingRoomInfo = waitingRoomInfo;

        setJoinerInfo();
//        updateMyInfo2FireBase();

    }

    public void setJoinerInfo() {

        // 都是 hardcode 的，屆時要帶入 user info
        WaitingRoomSeats waitingRoomSeats = new WaitingRoomSeats();
        waitingRoomSeats.setAvatar("https://graph.facebook.com/2177302648995421/picture?type=large");
        waitingRoomSeats.setPosition("sg");
        waitingRoomSeats.setSort(mWaitingRoomInfo.getWaitingPlayersList().size() + 1);
        waitingRoomSeats.setGender("female");
        waitingRoomSeats.setSeatAvailable(false);
        waitingRoomSeats.setId(GoGoBasketball.getAppContext().getString(R.string.id_player6));

        mWaitingRoomInfo.getWaitingPlayersList().add(waitingRoomSeats);

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .whereEqualTo("hostName", mWaitingRoomInfo.getHostName())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mDocId = document.getId();
                                updateMyInfo2FireBase(mDocId,mWaitingRoomInfo);
//                                Log.w("Kerry", "Doc id = " + mDocId);
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });

        mWaiting4JoineView.getRoomInfoFromPresenter(mWaitingRoomInfo);
    }

    public void updateMyInfo2FireBase(String roomId, WaitingRoomInfo waitingRoomInfo) {
        
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomId)
                .set(waitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "我加入囉！");
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
