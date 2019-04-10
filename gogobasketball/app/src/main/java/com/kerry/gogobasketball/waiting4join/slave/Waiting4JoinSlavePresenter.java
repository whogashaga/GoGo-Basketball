package com.kerry.gogobasketball.waiting4join.slave;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

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

        getRoomDocId();

    }

    private void getRoomDocId() {

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
//                                Log.w("Kerry", "Doc id = " + mDocId);
                                changeRoomPlayerAmount(document.getId());
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void changeRoomPlayerAmount(String roomId) {
        if (mWaitingRoomInfo.getPlayerAmount() < 6) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() + 1);
            setJoinerInfo(mWaitingRoomInfo.getTotalPlayerAmount(), roomId);

        } else if (mWaitingRoomInfo.getPlayerAmount() == 6 && mWaitingRoomInfo.getRefereeAmount() < 1) {
            mWaitingRoomInfo.setRefereeAmount(1);
            setJoinerInfo(mWaitingRoomInfo.getTotalPlayerAmount(), roomId);
        } else {
            Log.d(Constants.TAG,"Slave Join Room Error !");
        }
    }

    private void setJoinerInfo(int currentPlayerAmount, String roomId) {


        // 都是 hardcode 的，屆時要帶入 user info
        WaitingRoomSeats joinerSeatInfo = new WaitingRoomSeats();
        joinerSeatInfo.setAvatar("https://graph.facebook.com/2177302648995421/picture?type=large");
        joinerSeatInfo.setPosition("sg");
        joinerSeatInfo.setSort(currentPlayerAmount);
        joinerSeatInfo.setGender("female");
        joinerSeatInfo.setSeatAvailable(false);
        joinerSeatInfo.setId(GoGoBasketball.getAppContext().getString(R.string.id_player6));

        updateRoomInfo2FireBase(roomId, mWaitingRoomInfo, joinerSeatInfo);

        mWaiting4JoineView.getRoomInfoFromPresenter(mWaitingRoomInfo);
    }

    private void updateRoomInfo2FireBase(String roomId, WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats joinerInfo) {


        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomId)
                .set(waitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "加入，並改變房間人數");
                        updateJoinerInfo2FireBase(joinerInfo, roomId);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });
    }

    private void updateJoinerInfo2FireBase(WaitingRoomSeats joinerInfo, String roomId) {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomId)
                .collection(Constants.WAITING_SEATS)
                .document(joinerInfo.getId())
                .set(joinerInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "加入，並改變座位資訊！");
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
