package com.kerry.gogobasketball.waiting4join.slave;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlavePresenter implements Waiting4JoinSlaveContract.Presenter {

    private final Waiting4JoinSlaveContract.View mWaiting4JoinView;

    private ArrayList<WaitingRoomSeats> mSeatsInfoList;
    private WaitingRoomInfo mWaitingRoomInfo;
    private WaitingRoomSeats mJoinerInfo;

    private String mRoomDocId;
    private String mSortDocId;
    private int mIntJoinerSort;


    public Waiting4JoinSlavePresenter(@NonNull Waiting4JoinSlaveContract.View waiting4JoinView) {
        mWaiting4JoinView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoinView.setPresenter(this);
        mWaitingRoomInfo = new WaitingRoomInfo();
        mJoinerInfo = new WaitingRoomSeats();
        mRoomDocId = "";
        mIntJoinerSort = 1;
        mSeatsInfoList = new ArrayList<>();
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

                                WaitingRoomSeats seatInfo = document.toObject(WaitingRoomSeats.class);
                                mSeatsInfoList.add(seatInfo);
                            }

                            // 若已有 sort ，自動往後補
                            while (mSeatsInfoList.get(mIntJoinerSort - 1).getSort() == mIntJoinerSort) {
                                mIntJoinerSort++;
                            }
//                            while (existedSortList.contains(String.valueOf(mIntJoinerSort))) {
//                                mIntJoinerSort++;
//                            }

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
        mJoinerInfo.setPosition("sf");
        mJoinerInfo.setSort(mIntJoinerSort);
        mJoinerInfo.setGender("female");
        mJoinerInfo.setSeatAvailable(false);
        mJoinerInfo.setId(GoGoBasketball.getAppContext().getString(R.string.id_player6));

        // 把自己這筆加進去，for bind view
        mSeatsInfoList.add(mJoinerInfo);
        mWaiting4JoinView.showRoomName(mWaitingRoomInfo);
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
                        setSnapshotListerSlave();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Listener */

    private void setSnapshotListerSlave() {

        final DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId);

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Log.d(Constants.TAG, "Current data: " + snapshot.getData());

                    getNewSeatsInfo();

                } else {

                    mWaiting4JoinView.closeWaitingSlaveUi(false);

                    Toast.makeText(GoGoBasketball.getAppContext(), "房主落跑了...", Toast.LENGTH_SHORT).show();
                    Log.d(Constants.TAG, "Current data: null");
                }
            }
        });
    }

    private void getNewSeatsInfo() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (mWaitingRoomInfo.getStatus().equals("waiting")) {
                                mSeatsInfoList.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    WaitingRoomSeats newSeatInfo = document.toObject(WaitingRoomSeats.class);
                                    mSeatsInfoList.add(newSeatInfo);
                                }

                                ArrayList<WaitingRoomSeats> emptySeatsList = new ArrayList<>();
                                for (int i = mSeatsInfoList.size(); i < 7; i++) {
                                    emptySeatsList.add(new WaitingRoomSeats());
                                }

                                for (int j = 0; j < mSeatsInfoList.size(); j++) {
                                    emptySeatsList.add(mSeatsInfoList.get(j).getSort() - 1, mSeatsInfoList.get(j));
                                }

                                mWaiting4JoinView.showWaitingSeatsSlaveUi(emptySeatsList);

                            } else if (mWaitingRoomInfo.getStatus().equals("close")) {
                                mWaiting4JoinView.closeWaitingSlaveUi(false);
                            }

                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    /* ------------------------------------------------------------------------------------------ */
    /* delete when get out */

    @Override
    public void deleteSeatsInfoWhenLeaveRoom() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(mSortDocId)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }

    @Override
    public void changeRoomPlayerAmountWhenLeave() {

        if (mJoinerInfo.getSort() == 0 || mJoinerInfo.getSort() == 1
                || mJoinerInfo.getSort() == 2 || mJoinerInfo.getSort() == 3
                || mJoinerInfo.getSort() == 4 || mJoinerInfo.getSort() == 5
                || mJoinerInfo.getSort() == 6) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() - 1);
        } else {
            mWaitingRoomInfo.setRefereeAmount(0);
        }
    }

    @Override
    public void updateRoomInfoWhenLeaveSlave() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .set(mWaitingRoomInfo)
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
