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
        mHostSeatInfo = hostSeatsInfo;
        mWaiting4JoinMasterView.getRoomInfoFromPresenter(waitingRoomInfo);
        mRoomDocId = roomDocId;

        setSnapshotListerMaster(roomDocId);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Listener */

    private void setSnapshotListerMaster(String roomDocId) {

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
                    Log.d(Constants.TAG, "Current data: " + snapshot.getData());
                    Log.w("Kerry", "Current data: " + snapshot.getData());

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

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WaitingRoomSeats newSeatsInfo = document.toObject(WaitingRoomSeats.class);
                                mSeatsInfoList.add(newSeatsInfo);
                            }

                            ArrayList<WaitingRoomSeats> emptySeatsList = new ArrayList<>();
                            for (int i = mSeatsInfoList.size(); i < 7; i++) {
                                emptySeatsList.add(new WaitingRoomSeats());
                            }

                            for (int j = 0; j < mSeatsInfoList.size(); j++) {
                                emptySeatsList.add(mSeatsInfoList.get(j).getSort() - 1, mSeatsInfoList.get(j));
                            }

                            Log.e("Kerry", "EmptyList size = " + emptySeatsList.size());

                            mWaiting4JoinMasterView.showWaitingSeatsMasterUi(emptySeatsList);

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

    @Override
    public void updateRoomInfoWhenLeaveMaster() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .update(Constants.ROOM_STATUS, "close")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "Room status 改為 close");
                        deleteHostInfoWhenLeave();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(Constants.TAG, "Error updating document", e);
                    }
                });
    }

    private void deleteHostInfoWhenLeave() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(String.valueOf(mHostSeatInfo.getSort()))
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(Constants.TAG, "刪除 Master Seat！");
                deleteRoomDocWhenLeave();
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
                Log.d(Constants.TAG, "還是不要創房好了！");
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
    public void finishWaiting4JoinUi() {

    }

    @Override
    public void start() {

    }
}
