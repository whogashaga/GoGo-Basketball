package com.kerry.gogobasketball.waiting4join.master;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class Waiting4JoinMasterPresenter implements Waiting4JoinMasterContract.Presenter {

    private final Waiting4JoinMasterContract.View mWaiting4JoinMasterView;

    private WaitingRoomInfo mWaitingRoomInfo;
    private WaitingRoomSeats mHostSeatInfo;
    private String mRoomDocId;
    private ArrayList<WaitingRoomSeats> mSeatsInfoList;
    private ArrayList<WaitingRoomSeats> mListForChangeSeat;
    private int mCurrentSort;

    public Waiting4JoinMasterPresenter(@NonNull Waiting4JoinMasterContract.View waiting4JoinView) {
        mWaiting4JoinMasterView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoinMasterView.setPresenter(this);
        mWaitingRoomInfo = new WaitingRoomInfo();
        mRoomDocId = "";
        mSeatsInfoList = new ArrayList<>();
        mListForChangeSeat = new ArrayList<>();
        mCurrentSort = -1;
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatsInfo, String roomDocId) {

        mWaitingRoomInfo = waitingRoomInfo;
        mHostSeatInfo = hostSeatsInfo;
        mCurrentSort = hostSeatsInfo.getSort();
        mWaiting4JoinMasterView.getRoomInfoFromPresenter(waitingRoomInfo);
        mRoomDocId = roomDocId;

        setRoomSnapshotListerMaster(roomDocId);
        setSeatSnapshotListerMaster(roomDocId);
    }

    @Override
    public void changeMaster2NewSeat(int newSort) {
        if (mListForChangeSeat.get(newSort - 1).isSeatAvailable()) {
            Log.w("Kerry", "onClick changeMaster2NewSeat");
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
                .whereEqualTo("sort", mCurrentSort)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("Kerry", "seat doc id = " +document.getId());
                                // 只有一筆，跑 for 沒關係
                                updateSortForChangeSeat(document.getId(), newSort);
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void updateSortForChangeSeat(String seatDocId, int newSort) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(seatDocId)
                .update(Constants.SORT, newSort)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mCurrentSort = newSort;
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

    /* ------------------------------------------------------------------------------------------ */
    /* Listener */

    private void setSeatSnapshotListerMaster(String roomDocId) {
        final DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(mHostSeatInfo.getId());

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Log.w("Kerry", "Seat Current data: " + snapshot.getData());

                    getNewSeatsInfo();

                } else {
                    Log.d(Constants.TAG, "Current data: null");
                }
            }
        });
    }


    private void setRoomSnapshotListerMaster(String roomDocId) {

        final DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomDocId);

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Log.d(Constants.TAG, "Room Current data: " + snapshot.getData());
                    Log.w("Kerry", "Room Current data: " + snapshot.getData());

                    getNewSeatsInfo();

                } else {
                    Log.d(Constants.TAG, "Room Current data: null");
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
                                WaitingRoomSeats newSeatsInfo = document.toObject(WaitingRoomSeats.class);
                                mSeatsInfoList.add(newSeatsInfo);
                            }

                            ArrayList<WaitingRoomSeats> emptySeatsList = new ArrayList<>();
                            for (int i = 0; i < 7; i++) {
                                emptySeatsList.add(new WaitingRoomSeats());
                            }
                            for (int j = 0; j < mSeatsInfoList.size(); j++) {
                                emptySeatsList.set(mSeatsInfoList.get(j).getSort() - 1, mSeatsInfoList.get(j));
                            }

//                            Log.e("Kerry", "EmptyList size = " + emptySeatsList.size());

                            mWaiting4JoinMasterView.showWaitingSeatsMasterUi(emptySeatsList);

                            // for change seat use
                            mListForChangeSeat.addAll(emptySeatsList);

                        } else {
                            Log.w(Constants.TAG, "Master getNewSeatsInfo Error!!", task.getException());
                        }
                    }
                });

    }

    private void getNewRoomInfo() {

        DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                WaitingRoomInfo waitingRoomInfo = documentSnapshot.toObject(WaitingRoomInfo.class);
                mWaiting4JoinMasterView.getRoomInfoFromPresenter(waitingRoomInfo);
            }
        });
    }

    /* ------------------------------------------------------------------------------------------ */
    /* delete 流程 */

    @Override
    public void deleteHostInfoWhenLeave() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(mHostSeatInfo.getId())
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(Constants.TAG, "刪除 Master Seat！");
                checkTotalPlayerAmountMaster();
            }
        });
    }

    private void checkTotalPlayerAmountMaster() {
        DocumentReference docRef =
                FirestoreHelper.getFirestore()
                        .collection(Constants.WAITING_ROOM)
                        .document(mRoomDocId);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                WaitingRoomInfo waitingRoomInfo = documentSnapshot.toObject(WaitingRoomInfo.class);

                if (waitingRoomInfo.getTotalPlayerAmount() == 0) {
                    deleteRoomDocWhenLeave();
                    Log.d(Constants.TAG, "Master 最後一個離開！");
                } else {
                    changeRoomPlayerAmountWhenLeaveMaster();
                    Log.d(Constants.TAG, "Master 是跳狗！");
                }
            }
        });
    }

    private void changeRoomPlayerAmountWhenLeaveMaster() {

        if (mCurrentSort == 0 || mCurrentSort == 1 || mCurrentSort == 2 || mCurrentSort == 3
                || mCurrentSort == 4 || mCurrentSort == 5 || mCurrentSort == 6) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() - 1);
            updatePlayerAmountWhenLeaveMaster();
        } else {
            mWaitingRoomInfo.setRefereeAmount(0);
            updatePlayerAmountWhenLeaveMaster();
        }
    }

    private void updatePlayerAmountWhenLeaveMaster() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .set(mWaitingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStatus2Closed();
                        Log.d("Kerry", "Master 跳狗，更新人數！");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });
    }


    private void updateStatus2Closed() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .update(Constants.ROOM_STATUS, Constants.CLOSED)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "Room status 改為 close");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(Constants.TAG, "Error updating document", e);
                    }
                });
    }

    private void deleteRoomDocWhenLeave() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(Constants.TAG, "delete 房間 doc！");
            }
        });
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void showErrorToast(String message) {
        Toast toast = Toast.makeText(GoGoBasketball.getAppContext(), "無效", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(16);
        toastTV.setText(message);
        toast.show();
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
    public void loadPlayersInfoFromFirebase() {

    }

    @Override
    public void loadRefereeInfoFromFirebase() {

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
