package com.kerry.gogobasketball.result.referee;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

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
        mHostName = hostName;
        mGameResultView.getHostNameFromPresenter(hostName);
    }

    @Override
    public void getRoomInfoFromFireStore(String hostName) {
        Log.e("Kerry", "getGamingRoomFromFireStore hostName = " + hostName);
        FirestoreHelper.getFirestore()
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
//                                Log.w("Kerry", "gaming room id = " + document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
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
    public void finishResultResultUi() {

    }

    @Override
    public void setBackKeyDisable(boolean isBackKeyDisable) {

    }

    @Override
    public void start() {

    }
}
