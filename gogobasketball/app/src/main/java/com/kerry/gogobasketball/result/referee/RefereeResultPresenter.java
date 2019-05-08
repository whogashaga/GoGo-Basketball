package com.kerry.gogobasketball.result.referee;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FireStoreHelper;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

public class RefereeResultPresenter implements RefereeResultContract.Presenter {


    private final RefereeResultContract.View mGameResultView;
    private String mHostName;
    private String mRoomDocId;

    public RefereeResultPresenter(@NonNull RefereeResultContract.View refereeGoingView) {
        mGameResultView = checkNotNull(refereeGoingView, "GameResultView cannot be null!");
        mGameResultView.setPresenter(this);
        mHostName = "";
        mRoomDocId = "";
    }

    @Override
    public void getHostNameFromRefereeGoing(String hostName) {
        Log.w(Constants.TAG, "getHostNameFromRefereeGoing Result Referee hostName = " + hostName);
        mHostName = hostName;
        mGameResultView.getHostNameFromPresenter(hostName);
    }

    @Override
    public void getRoomInfoFromFireStore(String hostName) {
        Log.e(Constants.TAG, "getRoomFromFireStore hostName = " + hostName);
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
                                GamingRoomInfo gamingRoomInfo = document.toObject(GamingRoomInfo.class);
                                mGameResultView.showResultRefereeUi(gamingRoomInfo);
                            }
                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void deleteGamingRoom() {
        FirebaseFirestore.getInstance()
                .collection(Constants.GAMING_ROOM)
                .document(mRoomDocId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "刪除 gaming room document!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(Constants.TAG, "deleteGamingRoom Error !", e);
                    }
                });
    }

    @Override
    public void setActivityBackgroundLandScape() {

    }

    @Override
    public void setActivityBackgroundPortrait() {

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
    public void setBackKeyDisable(boolean isBackKeyDisable) {

    }

    @Override
    public void start() {

    }
}
