package com.kerry.gogobasketball.playing.referee;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

public class RefereeGoingPresenter implements RefereeGoingContract.Presenter {

    private final RefereeGoingContract.View mGamePlayingView;

    private GamingRoomInfo mGamingRoomInfo;
    private String mHostName;

    public RefereeGoingPresenter(@NonNull RefereeGoingContract.View refereeGoingView) {
        mGamePlayingView = checkNotNull(refereeGoingView, "GamePlayingView cannot be null!");
        mGamePlayingView.setPresenter(this);
        mHostName = "";
        mGamingRoomInfo = new GamingRoomInfo();
    }

    @Override
    public void getHostNameFromWaitingJoin(String hostName) {
        this.mHostName = hostName;
        Log.w("Kerry", "getHostNameFromWaitingJoin hostName : " + hostName );

        mGamePlayingView.getHostNameFromPresenter(hostName);
    }

    @Override
    public void getGamingRoomFromFireStore(String hostName) {
        Log.e("Kerry", "getGamingRoomFromFireStore hostName = " + hostName );
        FirestoreHelper.getFirestore()
                .collection(Constants.GAMING_ROOM)
                .whereEqualTo(Constants.HOST_NAME, hostName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                GamingRoomInfo gamingRoomInfo = document.toObject(GamingRoomInfo.class);
                                mGamePlayingView.showPlayingGameUi(gamingRoomInfo);
//                                Log.w("Kerry", "gaming room id = " + document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void loadRefereeInfoFromFirebase() {

    }

    @Override
    public void forced2FinishPlayingUi() {

    }

    @Override
    public void showGameResult() {

    }

    @Override
    public void forced2FinishGaming() {

    }

    @Override
    public void fireStoreScorePlusP1() {

    }

    @Override
    public void fireStoreScoreMinusP1() {

    }

    @Override
    public void fireStoreReboundPlusP1() {

    }

    @Override
    public void fireStoreReboundMinusP1() {

    }

    @Override
    public void fireStoreFoulPlusP1() {

    }

    @Override
    public void fireStoreFoulMinusP1() {

    }

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void setBackKeyDisable(boolean isBackKeyDisable) {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }
}
