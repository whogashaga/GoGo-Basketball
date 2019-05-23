package com.kerry.gogobasketball.playing.player;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

import javax.annotation.Nullable;

public class PlayerGoingPresenter implements PlayerGoingContract.Presenter {

    private final PlayerGoingContract.View mGamePlayingView;
    private ListenerRegistration mRoomListenerRegistration;
    private String mRoomDocId;

    public PlayerGoingPresenter(@NonNull PlayerGoingContract.View waiting4JoinView) {
        mGamePlayingView = checkNotNull(waiting4JoinView, "GamePlayingView cannot be null!");
        mGamePlayingView.setPresenter(this);
        mRoomDocId = "";
    }

    @Override
    public void getHostNameFromWaitingJoinSlave(String hostName, int nowSort) {
//        mHostName = hostName;
        getDocId(hostName, nowSort);
    }

    private void getDocId(String hostName, int nowSort) {
        FirebaseFirestore.getInstance()
                .collection(Constants.GAMING_ROOM)
                .whereEqualTo(Constants.HOST_NAME, hostName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mRoomDocId = document.getId();
                                setGamingRoomSnapshotListerPlayer(nowSort);
                            }
                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    private void setGamingRoomSnapshotListerPlayer(int nowSort) {

        Log.w(Constants.TAG, "setGamingRoomSnapshotListerPlayer !");
        final DocumentReference docRef = FirebaseFirestore.getInstance()
                .collection(Constants.GAMING_ROOM)
                .document(mRoomDocId);

        mRoomListenerRegistration = docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Log.d(Constants.TAG, "Slave Room Current data: " + snapshot.getData());
                    GamingRoomInfo newRoomInfo = snapshot.toObject(GamingRoomInfo.class);

                    if (newRoomInfo.getStatus().equals(Constants.STATUS_OVER)) {
                        Log.w(Constants.TAG, "Got Game Over !");
                        mGamePlayingView.openGameResultPlayerUi(newRoomInfo.getHostName(), nowSort);
                        Log.i("Kerry", "PlayerGoingPresenter newRoomInfo.getHostName() = " + newRoomInfo.getHostName());
                        removeListenerPlayer();
                    } else {
                        Log.w(Constants.TAG, "didn't get Game Over !");
                    }

                } else {
                    Log.d(Constants.TAG, "Current data: null");
                }
            }
        });
    }

    @Override
    public void removeListenerPlayer() {
        Log.i("Kerry", "PlayerGoing removeListenerPlayer !");
        mRoomListenerRegistration.remove();
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
    public void openGameResultPlayer(String hostName, int nowSort) {

    }

    @Override
    public void setBackKeyDisable(boolean isBackKeyDisable) {

    }

    @Override
    public void setGamingNowMessage(boolean isGamingNow) {

    }

    @Override
    public void start() {

    }
}
