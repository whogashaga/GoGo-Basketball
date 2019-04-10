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

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlavePresenter implements Waiting4JoinSlaveContract.Presenter {

    private final Waiting4JoinSlaveContract.View mWaiting4JoineView;

    private WaitingRoomInfo mWaitingRoomInfo;
    private WaitingRoomSeats mJoinerInfo;

    private String mRoomDocId;
    private String mSortDocId;
    private int mIntJoinerSort;

    public Waiting4JoinSlavePresenter(@NonNull Waiting4JoinSlaveContract.View waiting4JoinView) {
        mWaiting4JoineView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoineView.setPresenter(this);
        mWaitingRoomInfo = new WaitingRoomInfo();
        mJoinerInfo = new WaitingRoomSeats();
        mRoomDocId = "";
        mIntJoinerSort = 0;
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
                                // 只有一筆，跑 for 沒關係
                                mRoomDocId = document.getId();
                                changeRoomPlayerAmountWhenJoin(document.getId());
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void changeRoomPlayerAmountWhenJoin(String roomId) {
        if (mWaitingRoomInfo.getPlayerAmount() < 6) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() + 1);

            queryExistedSort();

        } else if (mWaitingRoomInfo.getPlayerAmount() == 6 && mWaitingRoomInfo.getRefereeAmount() < 1) {
            mWaitingRoomInfo.setRefereeAmount(1);

            queryExistedSort();

        } else {
            Log.d(Constants.TAG, "Slave Join Room Error !");
        }
    }

    private void queryExistedSort() {

        ArrayList<String> existedSortList = new ArrayList<>();

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                existedSortList.add(document.getId());
                            }

                            Log.d("Kerry","boolean = " + (existedSortList.contains(String.valueOf(mIntJoinerSort))));
                            Log.d("Kerry","boolean = " + existedSortList.toString());
                            while (existedSortList.contains(String.valueOf(mIntJoinerSort))) {
                                mIntJoinerSort++;
                                Log.w("Kerry","mIntJoinerSort = " + mIntJoinerSort);
                            }

                            setJoinerInfo();

                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void setJoinerInfo() {


        // 都是 hardcode 的，屆時要帶入 user info

        mJoinerInfo.setAvatar("https://graph.facebook.com/2177302648995421/picture?type=large");
        mJoinerInfo.setPosition("c");
        mJoinerInfo.setSort(mIntJoinerSort);
        mJoinerInfo.setGender("male");
        mJoinerInfo.setSeatAvailable(false);
        mJoinerInfo.setId(GoGoBasketball.getAppContext().getString(R.string.id_player6));

        mWaiting4JoineView.getRoomInfoFromPresenter(mWaitingRoomInfo);

        updateRoomInfoWhenJoin(mWaitingRoomInfo);

    }

    private void updateRoomInfoWhenJoin(WaitingRoomInfo waitingRoomInfo) {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .set(waitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "Slave 加入，並改變房間人數");
                        updateJoinerInfo2FireBase(mJoinerInfo, mRoomDocId);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });
    }

    private void updateJoinerInfo2FireBase(WaitingRoomSeats joinerInfo, String roomId) {

        mSortDocId = String.valueOf(joinerInfo.getSort());

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomId)
                .collection(Constants.WAITING_SEATS)
                .document(String.valueOf(joinerInfo.getSort()))
                .set(joinerInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "Slave 加入，並改變座位資訊！");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });
    }

    /* ------------------------------------------------------------------------------------------ */
    /* delete */

    @Override
    public void deleteSeatInfoWhenLeaveRoom() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(mSortDocId)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                changeRoomPlayerAmountWhenLeave();
            }
        });
    }

    private void changeRoomPlayerAmountWhenLeave() {

        if (mJoinerInfo.getSort() == 0 || mJoinerInfo.getSort() == 1
                || mJoinerInfo.getSort() == 2
                || mJoinerInfo.getSort() == 3
                || mJoinerInfo.getSort() == 4
                || mJoinerInfo.getSort() == 5
                || mJoinerInfo.getSort() == 6) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() - 1);
            updateRoomInfoWhenLeave(mWaitingRoomInfo);
        } else {
            mWaitingRoomInfo.setRefereeAmount(0);
            updateRoomInfoWhenLeave(mWaitingRoomInfo);
        }
    }

    private void updateRoomInfoWhenLeave(WaitingRoomInfo waitingRoomInfo) {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .set(waitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Kerry", "Slave 離開，刪除資料");
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
