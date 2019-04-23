package com.kerry.gogobasketball.waiting4join.slave;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.UserManager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class Waiting4JoinSlavePresenter implements Waiting4JoinSlaveContract.Presenter {

    private final Waiting4JoinSlaveContract.View mWaiting4JoinView;

    private ArrayList<WaitingRoomSeats> mSeatsInfoList;
    private ArrayList<WaitingRoomSeats> mListForChangeSeat;
    private ArrayList<Integer> mExistedSortList;
    private WaitingRoomInfo mWaitingRoomInfo;
    private WaitingRoomSeats mJoinerInfo;
    private User mUser;

    private String mRoomDocId;
    private String mSeatDocId;
    private int mIntJoinerSort;
    private ListenerRegistration mAllSeatsListenerRegistration;
    private ListenerRegistration mRoomListenerRegistration;

    public Waiting4JoinSlavePresenter(@NonNull Waiting4JoinSlaveContract.View waiting4JoinView) {
        mWaiting4JoinView = checkNotNull(waiting4JoinView, "Waiting4JoinView cannot be null!");
        mWaiting4JoinView.setPresenter(this);
        mUser = new User();
        mWaitingRoomInfo = new WaitingRoomInfo();
        mJoinerInfo = new WaitingRoomSeats();
        mExistedSortList = new ArrayList<>();
        mRoomDocId = "";
        mIntJoinerSort = 1;
        mSeatsInfoList = new ArrayList<>();
        mListForChangeSeat = new ArrayList<>();
    }

    @Override
    public void getProfileUserData(Activity activity) {
        UserManager.getInstance().getUserProfile(activity, new UserManager.LoadCallback() {
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
    public void getHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo) {
        mWaitingRoomInfo = waitingRoomInfo;
        getRoomDocId();
    }

    private void getRoomDocId() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .whereEqualTo(Constants.HOST_NAME, mWaitingRoomInfo.getHostName())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(Constants.TAG, "doc size : " + task.getResult().size());
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // 只有一筆，跑 for 沒關係
                            mRoomDocId = document.getId();
                            changeRoomPlayerAmountWhenJoin(document.getId());
                        }
                    } else {
                        Log.w(Constants.TAG, "Error getting documents.", task.getException());
                    }
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "queryExistedSort Error", e));
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
                .addOnCompleteListener(task -> {
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
                        Log.w(Constants.TAG, "Error getting documents.", task.getException());
                    }
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "queryExistedSort Error", e));
    }

    private void setJoinerInfo() {

        // 都是 hardcode 的，屆時要帶入 user info
        mJoinerInfo.setAvatar(mUser.getAvatar());
        mJoinerInfo.setPosition(mUser.getPosition());
        mJoinerInfo.setSort(mIntJoinerSort);
        mJoinerInfo.setGender(mUser.getGender());
        mJoinerInfo.setSeatAvailable(false);
        mJoinerInfo.setId(mUser.getId());

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
                .addOnSuccessListener(aVoid -> {
                    Log.d(Constants.TAG, "Slave 加入，並改變房間人數");
                    updateJoinerInfo2FireBase(mJoinerInfo, mRoomDocId);
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    private void updateJoinerInfo2FireBase(WaitingRoomSeats joinerInfo, String roomId) {

        mSeatDocId = joinerInfo.getId();

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomId)
                .collection(Constants.WAITING_SEATS)
                .document(joinerInfo.getId())
                .set(joinerInfo)
                .addOnSuccessListener(aVoid -> {
                    Log.d(Constants.TAG, "Slave 加入，並改變座位資訊！");
                    mWaiting4JoinView.setBackBtnClickable();
                    setRoomSnapshotListerSlave();
                    setAllSnapshotListerSlave();
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    /* ------------------------------------------------------------------------------------------ */
    /* change seat method */

    @Override
    public void changeSlave2NewSeat(int newSort) {
        if (mListForChangeSeat.get(newSort - 1).isSeatAvailable()) {
            findCurrentSeatDocId(newSort);
        } else {
            Log.d(Constants.TAG, "changeSlave2NewSeat Error!!");
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
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "findCurrentSeatDocId Error", e));
    }

    private void queryExistedSortForChangeSeatSlave(String seatDocIdForUpdate, int newSort) {

        mExistedSortList.clear();

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            WaitingRoomSeats seatInfo = document.toObject(WaitingRoomSeats.class);
                            mExistedSortList.add(seatInfo.getSort());
                        }
                        Log.w(Constants.TAG, "onComplete: " + mExistedSortList.size());

                        updateSortForChangeSeatSlave(seatDocIdForUpdate, newSort);
                        changeRoomPlayerAmountAfterChangeSeatSlave(mExistedSortList, newSort);

                    } else {
                        Log.w(Constants.TAG, "Error getting documents.", task.getException());
                    }
                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error updating document", e));
    }

    private void updateSortForChangeSeatSlave(String seatDocId, int newSort) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(seatDocId)
                .update(Constants.SORT, newSort)
                .addOnSuccessListener(aVoid -> {
                    mIntJoinerSort = newSort;
                    Log.d(Constants.TAG, "DocumentSnapshot successfully updated!");
                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "updateSortForChangeSeatSlave Error", e));
    }

    private void changeRoomPlayerAmountAfterChangeSeatSlave(ArrayList<Integer> existedSortList, int newSort) {

        if (newSort == 7) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() - 1);
            mWaitingRoomInfo.setRefereeAmount(1);
        } else {
            // ArrayList 已經包含 7
            if (existedSortList.contains(7)) {
                if (mIntJoinerSort == 7) {
                    mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() + 1);
                    mWaitingRoomInfo.setRefereeAmount(mWaitingRoomInfo.getRefereeAmount() - 1);
                } else {
                    Log.d(Constants.TAG, "球員間換位，數字不變！");
                }
            } else {
                Log.d(Constants.TAG, "球員間換位，數字不變！！");
            }
        }
        updateRoomInfoAfterChangeSeatSlave();
    }

    private void updateRoomInfoAfterChangeSeatSlave() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .set(mWaitingRoomInfo)
                .addOnSuccessListener(aVoid -> Log.d(Constants.TAG, "Slave 換位，updateRoomInfoAfterChangeSeatSlave！"))
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Listener */

    private void setAllSnapshotListerSlave() {
        Query query = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS);

        mAllSeatsListenerRegistration = query.addSnapshotListener((value, e) -> {
            if (e != null) {
                Log.w(Constants.TAG, "Listen failed.", e);
                return;
            }
            getNewSeatsInfo();
        });

    }

    private void setRoomSnapshotListerSlave() {

        DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId);

        mRoomListenerRegistration = docRef.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                Log.w(Constants.TAG, "Listen failed.", e);
                return;
            }
            if (snapshot != null && snapshot.exists()) {
                Log.d(Constants.TAG, "Slave Room Current data: " + snapshot.getData());
                WaitingRoomInfo newRoomInfo = snapshot.toObject(WaitingRoomInfo.class);
                mWaitingRoomInfo = newRoomInfo;

                if (newRoomInfo.getStatus().equals(Constants.STATUS_CLOSED)) {

                    mWaiting4JoinView.closeSlaveUiBecauseMasterOutFirst();

                } else if (newRoomInfo.getStatus().equals(Constants.STATUS_GAMING)) {
                    queryCurrentSort();
                }

                getNewSeatsInfo();

            } else {
                Log.d(Constants.TAG, "Current data: null");
            }
        });
    }

    @Override
    public void removeListenerSlave() {
        mRoomListenerRegistration.remove();
        mAllSeatsListenerRegistration.remove();
    }

    private void queryCurrentSort() {
        DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(mSeatDocId);

        docRef.get().addOnSuccessListener(documentSnapshot -> {
            WaitingRoomSeats currentSeat = documentSnapshot.toObject(WaitingRoomSeats.class);
            Log.w(Constants.TAG, "queryCurrentSort @!!");
            if (currentSeat.getSort() == 7) {
                mWaiting4JoinView.openRefereeGamingUi(mWaitingRoomInfo.getHostName());
                removeListenerSlave();
            } else {
                mWaiting4JoinView.openPlayerGamingUi(mWaitingRoomInfo.getHostName(), currentSeat.getSort());
                removeListenerSlave();
            }
        })
                .addOnFailureListener(e -> Log.d(Constants.TAG, "Slave queryCurrentSort Error！"));

    }

    private void getNewSeatsInfo() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .get()
                .addOnCompleteListener(task -> {
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
                        Log.w(Constants.TAG, "Error getting documents.", task.getException());
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "Slave getNewSeatsInfo Error！"));
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
                .delete().addOnSuccessListener(aVoid -> checkTotalPlayerAmountSlave())
                .addOnFailureListener(e -> Log.d(Constants.TAG, "deleteSeatsInfoWhenLeaveRoom Error！"));
    }

    @Override
    public void checkTotalPlayerAmountSlave() {
        DocumentReference docRef =
                FirestoreHelper.getFirestore()
                        .collection(Constants.WAITING_ROOM)
                        .document(mRoomDocId);

        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                WaitingRoomInfo waitingRoomInfo = documentSnapshot.toObject(WaitingRoomInfo.class);

                if (waitingRoomInfo.getTotalPlayerAmount() == 1) {
                    deleteRoomDocSlave();
                } else {
                    changeRoomPlayerAmountWhenLeaveSlave();
                }
            }
        }).addOnFailureListener(e -> Log.d(Constants.TAG, "checkTotalPlayerAmountSlave Error！"));


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
                .addOnSuccessListener(aVoid -> Log.d(Constants.TAG, "Slave 離開，刪除資料"))
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    @Override
    public void deleteRoomDocSlave() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .delete().addOnSuccessListener(aVoid -> Log.d(Constants.TAG, "只剩一個 slave 最後離去！"))
                .addOnFailureListener(e -> Log.d(Constants.TAG, "沒房間可刪除！"));
    }

    /* ------------------------------------------------------------------------------------------ */

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
    public void setBackKeyDisable(boolean isBackKeyDisable) {

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
    public void openGamePlayingOfReferee(String hostName) {

    }

    @Override
    public void openGamePlayingOfPlayer(String hostName, int nowSort) {

    }

    @Override
    public void finishWaiting4JoinSlaveUi() {

    }

    @Override
    public void start() {

    }
}
