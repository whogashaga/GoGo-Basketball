package com.kerry.gogobasketball.waiting4join.slave;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.LogDescriptor;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.RandomString;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlavePresenter implements Waiting4JoinSlaveContract.Presenter {

    private final Waiting4JoinSlaveContract.View mWaiting4JoinView;

    private ArrayList<WaitingRoomSeats> mSeatsInfoList;
    private ArrayList<WaitingRoomSeats> mListForChangeSeat;
    private WaitingRoomInfo mWaitingRoomInfo;
    private WaitingRoomSeats mJoinerInfo;

    private String mRoomDocId;
    private String mSeatDocId;
    private int mIntJoinerSort;

    public Waiting4JoinSlavePresenter(@NonNull Waiting4JoinSlaveContract.View waiting4JoinView) {
        mWaiting4JoinView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoinView.setPresenter(this);
        mWaitingRoomInfo = new WaitingRoomInfo();
        mJoinerInfo = new WaitingRoomSeats();
        mRoomDocId = "";
        mIntJoinerSort = 1;
        mSeatsInfoList = new ArrayList<>();
        mListForChangeSeat = new ArrayList<>();
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

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Integer> existedSortList = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                WaitingRoomSeats seatInfo = document.toObject(WaitingRoomSeats.class);
                                mSeatsInfoList.add(seatInfo);
                                existedSortList.add(seatInfo.getSort());
                            }
                            // 若已有 sort ，自動往後補
                            while (existedSortList.contains(mIntJoinerSort)) {
                                mIntJoinerSort++;
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
        mJoinerInfo.setPosition("sf");
        mJoinerInfo.setSort(mIntJoinerSort);
        mJoinerInfo.setGender("female");
        mJoinerInfo.setSeatAvailable(false);
        mJoinerInfo.setId(RandomString.getRandom(6));

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
                        Log.d(Constants.TAG, "Slave 加入，並改變房間人數");
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

        mSeatDocId = joinerInfo.getId();

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomId)
                .collection(Constants.WAITING_SEATS)
                .document(joinerInfo.getId())
                .set(joinerInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "Slave 加入，並改變座位資訊！");
                        setRoomSnapshotListerSlave();
                        setSeatSnapshotListerSlave(joinerInfo.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });
    }

    /* ------------------------------------------------------------------------------------------ */
    /* change seat method */

    @Override
    public void changeSlave2NewSeat(int newSort) {
        if (mListForChangeSeat.get(newSort - 1).isSeatAvailable()) {
            findCurrentSeatDocId(newSort);
        } else {
            Log.d("Kerry", "changeMaster2NewSeat Error!!");
        }
    }

    private void findCurrentSeatDocId(int newSort) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .whereEqualTo("sort", mIntJoinerSort)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // 只有一筆，跑 for 沒關係
                                queryExistedSortForChangeSeatSlave(document.getId(), newSort);
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void queryExistedSortForChangeSeatSlave(String seatDocIdForUpdate, int newSort) {

        ArrayList<Integer> existedSortList = new ArrayList<>();

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
                                WaitingRoomSeats seatInfo = document.toObject(WaitingRoomSeats.class);
                                existedSortList.add(seatInfo.getSort());
                            }
                            updateSortForChangeSeatSlave(seatDocIdForUpdate, newSort);
                            changeRoomPlayerAmountAfterChangeSeatSlave(existedSortList, newSort);

                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void updateSortForChangeSeatSlave(String seatDocId, int newSort) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(seatDocId)
                .update(Constants.SORT, newSort)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mIntJoinerSort = newSort;
                        Log.d(Constants.TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(Constants.TAG, "Error updating document", e);
                    }
                });

    }

    private void changeRoomPlayerAmountAfterChangeSeatSlave(ArrayList<Integer> existedSortList, int newSort) {

        if (newSort == 7) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() - 1);
            mWaitingRoomInfo.setRefereeAmount(1);
        } else {

//            Log.e("Kerry","mExistedSortList 0 = " + existedSortList.get(0));
//            Log.e("Kerry","mExistedSortList 1= " + existedSortList.get(1));

            if (existedSortList.contains(7)) {
//                Log.d("Kerry", "ArrayList 包含 7 ");
                mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() + 1);
                mWaitingRoomInfo.setRefereeAmount(mWaitingRoomInfo.getRefereeAmount() - 1);
            } else {
                Log.d(Constants.TAG, "球員間換位，數字不變！");
            }
        }
        updateRoomInfoAfterChangeSeatSlave();
    }

    private void updateRoomInfoAfterChangeSeatSlave() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .set(mWaitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "Master 換位，updateRoomInfoAfterChangeSeatMaster！");
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

    private void setSeatSnapshotListerSlave(String roomDocId) {
        final DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(mJoinerInfo.getId());

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Log.w(Constants.TAG, "Slave Seat Current data: " + snapshot.getData());

                    getNewSeatsInfo();

                } else {
                    Log.d(Constants.TAG, "Current data: null");
                }
            }
        });
    }

    private void setRoomSnapshotListerSlave() {

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
                    Log.d(Constants.TAG, "Slave Room Current data: " + snapshot.getData());
                    WaitingRoomInfo newRoomInfo = snapshot.toObject(WaitingRoomInfo.class);
                    mWaitingRoomInfo = newRoomInfo;

                    if (newRoomInfo.getStatus().equals(Constants.CLOSED)) {
                        mWaiting4JoinView.closeSlaveUiBecauseMasterOutFirst();
                    } else if (newRoomInfo.getStatus().equals(Constants.GAMING)) {
                        if (mIntJoinerSort == 7) {
                            mWaiting4JoinView.openRefereeGamingUi();
                        } else {
                            mWaiting4JoinView.openPlayerGamingUi();
                        }
                    }

                    getNewSeatsInfo();

                } else {
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

                            mSeatsInfoList.clear();
                            mListForChangeSeat.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WaitingRoomSeats newSeatInfo = document.toObject(WaitingRoomSeats.class);
                                mSeatsInfoList.add(newSeatInfo);
                            }

                            // 先給七個空的，已存在球員再 replace 空 obj 的位置
                            ArrayList<WaitingRoomSeats> emptySeatsList = new ArrayList<>();
                            for (int i = 0; i < 7; i++) {
                                emptySeatsList.add(new WaitingRoomSeats());
                            }
                            for (int j = 0; j < mSeatsInfoList.size(); j++) {
                                emptySeatsList.set(mSeatsInfoList.get(j).getSort() - 1, mSeatsInfoList.get(j));
                            }

                            mWaiting4JoinView.showWaitingSeatsSlaveUi(emptySeatsList);

                            // for change seat use
                            mListForChangeSeat.addAll(emptySeatsList);

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
                .document(mSeatDocId)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                checkTotalPlayerAmountSlave();
            }
        });
    }

    @Override
    public void checkTotalPlayerAmountSlave() {
        DocumentReference docRef =
                FirestoreHelper.getFirestore()
                        .collection(Constants.WAITING_ROOM)
                        .document(mRoomDocId);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                WaitingRoomInfo waitingRoomInfo = documentSnapshot.toObject(WaitingRoomInfo.class);

                if (waitingRoomInfo.getTotalPlayerAmount() == 0) {
                    deleteRoomDocSlave();
                } else {
                    changeRoomPlayerAmountWhenLeaveSlave();
                }
            }
        });

    }

    private void changeRoomPlayerAmountWhenLeaveSlave() {

        if (mJoinerInfo.getSort() == 0 || mJoinerInfo.getSort() == 1
                || mJoinerInfo.getSort() == 2 || mJoinerInfo.getSort() == 3
                || mJoinerInfo.getSort() == 4 || mJoinerInfo.getSort() == 5
                || mJoinerInfo.getSort() == 6) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() - 1);
            updateRoomInfoWhenLeaveSlave();
        } else {
            mWaitingRoomInfo.setRefereeAmount(0);
            updateRoomInfoWhenLeaveSlave();
        }
    }

    private void updateRoomInfoWhenLeaveSlave() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .set(mWaitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "Slave 離開，刪除資料");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });
    }

    @Override
    public void deleteRoomDocSlave() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(Constants.TAG, "只剩一個 slave 最後離去！");
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
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void openGamePlayingOfReferee() {

    }

    @Override
    public void openGamePlayingOfPlayer() {

    }

    @Override
    public void finishWaiting4JoinUi() {

    }

    @Override
    public void start() {

    }
}
