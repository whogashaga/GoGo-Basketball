package com.kerry.gogobasketball.want2create;

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
import com.kerry.gogobasketball.data.CourtsInfo;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.UserManager;

import java.util.ArrayList;

public class Want2CreateRoomPresenter implements Want2CreateRoomContract.Presenter {

    private final Want2CreateRoomContract.View mWant2CreateRoomView;
    private WaitingRoomInfo mWaitingRoomInfo;
    private ArrayList<String> mCourtsList;
    private User mUser;

    public Want2CreateRoomPresenter(@NonNull Want2CreateRoomContract.View want2CreateRoomView) {
        mWant2CreateRoomView = checkNotNull(want2CreateRoomView, "Want2CreateRoomView cannot be null!");
        mWant2CreateRoomView.setPresenter(this);

        mWaitingRoomInfo = new WaitingRoomInfo();
        mCourtsList = new ArrayList<>();
        mUser = new User();
    }

    @Override
    public void getSpinnerCourtLocation(String courtLocation) {
        mWaitingRoomInfo.setCourtLocation(courtLocation);
    }

    @Override
    public void getRadioRefereeMode(boolean justice) {
        mWaitingRoomInfo.setJustice(justice);
    }

    @Override
    public void onRoomNameEditTextChange(CharSequence roomName) {
//        Log.d(Constants.TAG, "room name = " + roomName.length());
        mWaitingRoomInfo.setRoomName(roomName.toString());
    }

    @Override
    public void updateRoomInfo2Cloud() {

        //  set Host Player Info (hardcode，屆時要帶入 user info)
        WaitingRoomSeats hostSeatInfo = new WaitingRoomSeats();
        hostSeatInfo.setAvatar(mUser.getAvatar());
        hostSeatInfo.setPosition(mUser.getPosition());
        hostSeatInfo.setSort(1);
        hostSeatInfo.setGender(mUser.getGender());
        hostSeatInfo.setSeatAvailable(false);
        hostSeatInfo.setId(mUser.getId());

        // set Room Info
        WaitingRoomInfo waitingRoomInfo = new WaitingRoomInfo();
        waitingRoomInfo.setJustice(mWaitingRoomInfo.getJustice());
        waitingRoomInfo.setCourtLocation(mWaitingRoomInfo.getCourtLocation());
        waitingRoomInfo.setRoomName(mWaitingRoomInfo.getRoomName());
        waitingRoomInfo.setHostName(hostSeatInfo.getId());
        waitingRoomInfo.setPlayerAmount(1);
        waitingRoomInfo.setRefereeAmount(0);
        waitingRoomInfo.setStatus(Constants.STATUS_WAITING);

        FirebaseFirestore.getInstance()
                .collection(Constants.WAITING_ROOM)
                .document(mUser.getFacebookId())
                .set(waitingRoomInfo)
                .addOnSuccessListener(documentReference -> {
                    // for open waiting4join bind view
                    mWant2CreateRoomView.getRoomInfoFromPresenter4NextFragment(waitingRoomInfo, hostSeatInfo, mUser.getFacebookId());
                    Log.d(Constants.TAG, "Master創建房間 ！!");
                })
                .addOnFailureListener(e -> Log.e(Constants.TAG, "Error adding document", e));

    }

    @Override
    public void updateHostSeat2Cloud(WaitingRoomSeats hostPlayer, String roomDocId) {

        FirebaseFirestore.getInstance()
                .collection(Constants.WAITING_ROOM)
                .document(roomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(hostPlayer.getId())
                .set(hostPlayer)
                .addOnSuccessListener(avoid -> Log.d(Constants.TAG, "Master進入房間 ！!"))
                .addOnFailureListener(e -> Log.e(Constants.TAG, "Error adding document", e));
    }

    @Override
    public void loadHostUserData() {
        UserManager.getInstance().getUserProfile(new UserManager.LoadCallback() {
            @Override
            public void onSuccess(User user) {
                mUser = user;
            }

            @Override
            public void onFail(String errorMessage) {

            }

            @Override
            public void onInvalidToken(String errorMessage) {

            }
        });
    }

    @Override
    public void setCreateBtnClickable() {
        mWant2CreateRoomView.setCreateRoomBtnClickable();
    }

    @Override
    public void getCourtsListFromCloud() {
        FirebaseFirestore.getInstance()
                .collection(Constants.COURTS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            CourtsInfo courtsInfo = document.toObject(CourtsInfo.class);
                            mCourtsList.add(courtsInfo.getLocation());
                        }
                        mWant2CreateRoomView.setSpinnerCourts(mCourtsList);
                    } else {
                        Log.w(Constants.TAG, "Error getting documents.", task.getException());
                    }
                }).addOnFailureListener(e -> Log.e(Constants.TAG, "onFailure: ", e));
    }

    /* ------------------------------------------------------------------------------------------ */
    /* implement in MainPresenter */

    @Override
    public void openWaitingJoinMaster(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void setWant2CreateNow(boolean isCreatingNow) {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public void finishWant2CreateRoomUi() {

    }

    @Override
    public void setActivityBackgroundLandScape() {

    }

    @Override
    public void setActivityBackgroundPortrait() {

    }

    @Override
    public void start() {

    }

}
