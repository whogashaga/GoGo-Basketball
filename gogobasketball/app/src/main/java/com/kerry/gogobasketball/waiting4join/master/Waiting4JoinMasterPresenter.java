package com.kerry.gogobasketball.waiting4join.master;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class Waiting4JoinMasterPresenter implements Waiting4JoinMasterContract.Presenter {

    private final Waiting4JoinMasterContract.View mWaiting4JoinMasterView;

    private WaitingRoomInfo mWaitingRoomInfo;
    private String mRoomDocId;
    private ArrayList<WaitingRoomSeats> mSeatsInfoList;

    public Waiting4JoinMasterPresenter(@NonNull Waiting4JoinMasterContract.View waiting4JoinView) {
        mWaiting4JoinMasterView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoinMasterView.setPresenter(this);
        mWaitingRoomInfo = new WaitingRoomInfo();
        mRoomDocId = "";
        mSeatsInfoList = new ArrayList<>();
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatsInfo, String roomDocId) {

        mWaitingRoomInfo = waitingRoomInfo;

        mWaiting4JoinMasterView.getRoomInfoWhenCreate(waitingRoomInfo, hostSeatsInfo);
        mRoomDocId = roomDocId;

        setSnapshotListerMaster();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Listener */

    private void setSnapshotListerMaster() {

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
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WaitingRoomSeats newSeatsInfo = document.toObject(WaitingRoomSeats.class);
                                mSeatsInfoList.add(newSeatsInfo);
                            }
                            Log.d("Kerry", "boolean = " + mSeatsInfoList.size());

                            mWaiting4JoinMasterView.showWaitingSeatsUi(mSeatsInfoList);

                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void deleteRoomWhenLeaveRoom() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(Constants.TAG,"還是不要創房好了！");
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
    public void openGamePlayingOfReferee() {

    }

    @Override
    public void finishWaiting4JoinUi() {

    }

    @Override
    public void start() {

    }
}
