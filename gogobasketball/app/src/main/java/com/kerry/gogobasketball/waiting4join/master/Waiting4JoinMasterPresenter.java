package com.kerry.gogobasketball.waiting4join.master;

import static com.google.common.base.Preconditions.checkNotNull;

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
import com.kerry.gogobasketball.data.GamingPlayer;
import com.kerry.gogobasketball.data.GamingReferee;
import com.kerry.gogobasketball.data.GamingRoomInfo;
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
    private ListenerRegistration mAllSeatsListenerRegistration;
    private ListenerRegistration mRoomListenerRegistration;

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
    public void openUserDetailMaster(int sort) {
        if (sort == 1) {
            checkIfShowDetail(1);
        } else if (sort == 2) {
            checkIfShowDetail(2);
        } else if (sort == 3) {
            checkIfShowDetail(3);
        } else if (sort == 4) {
            checkIfShowDetail(4);
        } else if (sort == 5) {
            checkIfShowDetail(5);
        } else if (sort == 6) {
            checkIfShowDetail(6);
        } else if (sort == 7) {
            checkIfShowDetail(7);
        } else {
            Log.e("Kerry", "openUserDetailMaster Error ");
        }
    }

    private void checkIfShowDetail(int sort) {
        if (mListForChangeSeat.get(sort - 1) != null && !mListForChangeSeat.get(sort - 1).getId().equals("")) {
            mWaiting4JoinMasterView.openUserDetailUi(mListForChangeSeat.get(sort - 1).getId());
        } else {
            Log.d("Kerry", "openUserDetailMaster 1 Error !");
        }
    }

    @Override
    public void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatsInfo, String roomDocId) {

        mWaitingRoomInfo = waitingRoomInfo;
        mHostSeatInfo = hostSeatsInfo;
        mCurrentSort = hostSeatsInfo.getSort();
        mWaiting4JoinMasterView.getRoomInfoFromPresenter(waitingRoomInfo);
        mRoomDocId = roomDocId;

        setRoomSnapshotListerMaster(roomDocId);
        setAllSeatSnapshotListerSlave();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* change seat */

    @Override
    public void changeMaster2NewSeat(int newSort) {
        if (mListForChangeSeat.get(newSort - 1).isSeatAvailable()) {
            findCurrentSeatDocId(newSort);
        } else {
            Log.d(Constants.TAG, "changeMaster2NewSeat Error!!");
        }
    }

    private void findCurrentSeatDocId(int newSort) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .whereEqualTo("sort", mCurrentSort)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // 只有一筆，跑 for 沒關係
                            queryExistedSort(document.getId(), newSort);
                        }
                    } else {
                        Log.w(Constants.TAG, "Error getting documents.", task.getException());
                    }
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error getting documents.", e));
    }


    private void queryExistedSort(String seatDocIdForUpdate, int newSort) {

        ArrayList<Integer> existedSortList = new ArrayList<>();

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            WaitingRoomSeats seatInfo = document.toObject(WaitingRoomSeats.class);
                            existedSortList.add(seatInfo.getSort());
                        }
//                            Log.d(Constants.TAG, "Master existedSortList = " + existedSortList.get(0));
                        updateSortForChangeSeatMaster(seatDocIdForUpdate, newSort);
                        changeRoomPlayerAmountAfterChangeSeatMaster(existedSortList, newSort);

                    } else {
                        Log.w(Constants.TAG, "Error getting documents.", task.getException());
                    }
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error getting documents.", e));
    }


    private void updateSortForChangeSeatMaster(String seatDocId, int newSort) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS)
                .document(seatDocId)
                .update(Constants.SORT, newSort)
                .addOnSuccessListener(aVoid -> {
                    mCurrentSort = newSort;
                    Log.d(Constants.TAG, "DocumentSnapshot successfully updated!");
                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error updating document", e));
    }


    private void changeRoomPlayerAmountAfterChangeSeatMaster(ArrayList<Integer> existedSortList, int newSort) {

        if (newSort == 7) {
            mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() - 1);
            mWaitingRoomInfo.setRefereeAmount(1);
        } else {
            // ArrayList 包含 7
            if (existedSortList.contains(7)) {
                if (mCurrentSort == 7) {
                    mWaitingRoomInfo.setPlayerAmount(mWaitingRoomInfo.getPlayerAmount() + 1);
                    mWaitingRoomInfo.setRefereeAmount(mWaitingRoomInfo.getRefereeAmount() - 1);
                } else {
                    Log.d(Constants.TAG, "球員間換位，數字不變！");
                }
            } else {
                Log.d(Constants.TAG, "球員間換位，數字不變！");
            }

        }

        updateRoomInfoAfterChangeSeatMaster();
    }

    private void updateRoomInfoAfterChangeSeatMaster() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .set(mWaitingRoomInfo)
                .addOnSuccessListener(aVoid -> Log.d(Constants.TAG, "Master 換位，updateRoomInfoAfterChangeSeatMaster！"))
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Listener */

    private void setAllSeatSnapshotListerSlave() {
        Query query = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .collection(Constants.WAITING_SEATS);
        mAllSeatsListenerRegistration = query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(Constants.TAG, "Listen failed.", e);
                    return;
                }
                getNewSeatsInfo();
            }
        });

    }

    private void setRoomSnapshotListerMaster(String roomDocId) {

        final DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(roomDocId);

        mRoomListenerRegistration = docRef.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                Log.w(Constants.TAG, "Listen failed.", e);
                return;
            }
            if (snapshot != null && snapshot.exists()) {
                Log.d(Constants.TAG, "Master Room Current data: " + snapshot.getData());
                WaitingRoomInfo newRoomInfo = snapshot.toObject(WaitingRoomInfo.class);
                mWaitingRoomInfo = newRoomInfo;

                // 為了跳轉 gaming 畫面，需傳入現在總人數和房主 sort
                mWaiting4JoinMasterView.getNewPlayerAmount(newRoomInfo.getTotalPlayerAmount(), mCurrentSort);
                getNewSeatsInfo();

            } else {
                Log.d(Constants.TAG, "Room Current data: null");
            }
        });
    }

    @Override
    public void removeListenerMaster() {
        mRoomListenerRegistration.remove();
        mAllSeatsListenerRegistration.remove();
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
//                            Log.e(Constants.TAG, "EmptyList size = " + emptySeatsList.size());

                        mWaiting4JoinMasterView.showWaitingSeatsMasterUi(emptySeatsList);

                        // for change seat use
                        mListForChangeSeat.addAll(emptySeatsList);

                    } else {
                        Log.w(Constants.TAG, "Master getNewSeatsInfo Error!!", task.getException());
                    }
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
        ;

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
        }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    private void checkTotalPlayerAmountMaster() {
        DocumentReference docRef =
                FirestoreHelper.getFirestore()
                        .collection(Constants.WAITING_ROOM)
                        .document(mRoomDocId);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {
                    WaitingRoomInfo waitingRoomInfo = documentSnapshot.toObject(WaitingRoomInfo.class);
                    if (waitingRoomInfo.getTotalPlayerAmount() == 0) {
                        deleteRoomDocWhenLeave();
                        Log.d(Constants.TAG, "Master 最後一個離開！");
                    } else {
                        changeRoomPlayerAmountWhenLeaveMaster();
                        Log.d(Constants.TAG, "Master 是跳狗！");
                    }
                }
            }
        }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
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
                        Log.d(Constants.TAG, "Master 跳狗，更新人數！");
                    }
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }


    private void updateStatus2Closed() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .update(Constants.ROOM_STATUS, Constants.STATUS_CLOSED)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "Room status 改為 close");
                        deleteRoomDocWhenLeave();
                    }
                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error updating document", e));
    }

    private void deleteRoomDocWhenLeave() {

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .delete().addOnSuccessListener(aVoid -> Log.d(Constants.TAG, "Master delete 房間 doc！"))
                .addOnFailureListener(e -> Log.d(Constants.TAG, "沒房間可刪除！"));
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Start game ! */

    @Override
    public void initializeGamingRoomInfo() {

        GamingRoomInfo gamingRoomInfo = new GamingRoomInfo();
        gamingRoomInfo.setHostName(mWaitingRoomInfo.getHostName());
        gamingRoomInfo.setRoomName(mWaitingRoomInfo.getRoomName());
        gamingRoomInfo.setStatus(Constants.STATUS_GAMING);
        gamingRoomInfo.setPlayer1(setGamingPlayerInfo(1));
        gamingRoomInfo.setPlayer2(setGamingPlayerInfo(2));
        gamingRoomInfo.setPlayer3(setGamingPlayerInfo(3));
        gamingRoomInfo.setPlayer4(setGamingPlayerInfo(4));
        gamingRoomInfo.setPlayer5(setGamingPlayerInfo(5));
        gamingRoomInfo.setPlayer6(setGamingPlayerInfo(6));
        gamingRoomInfo.setReferee(setGamingRefereeInfo());

        FirestoreHelper.getFirestore()
                .collection(Constants.GAMING_ROOM)
                .add(gamingRoomInfo)
                .addOnSuccessListener(documentReference -> {
                    Log.d(Constants.TAG, "initializeGamingRoomInfo : " + documentReference.getId());
                    updateRoomStatus2Gaming(gamingRoomInfo);
                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    @Override
    public void updateRoomStatus2Gaming(GamingRoomInfo gamingRoomInfo) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mRoomDocId)
                .update(Constants.ROOM_STATUS, Constants.STATUS_GAMING)
                .addOnSuccessListener(aVoid -> {
                    Log.d(Constants.TAG, "Room status 改為 gaming!!");
                    mWaiting4JoinMasterView.getGamingRoomInfoFromPresenter4GamingFragment(gamingRoomInfo);
                    removeListenerMaster();
//                        deleteHostInfoWhenLeave();
                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error updating document", e));
    }

    private GamingPlayer setGamingPlayerInfo(int sort) {

        GamingPlayer gamingPlayer = new GamingPlayer();
        gamingPlayer.setAvatar(mListForChangeSeat.get(sort - 1).getAvatar());
        gamingPlayer.setId(mListForChangeSeat.get(sort - 1).getId());
        gamingPlayer.setGender(mListForChangeSeat.get(sort - 1).getGender());
        gamingPlayer.setPosition(mListForChangeSeat.get(sort - 1).getPosition());
        gamingPlayer.setSort(sort);
        gamingPlayer.setSeatAvailable(false);

        return gamingPlayer;
    }

    private GamingReferee setGamingRefereeInfo() {

        GamingReferee gamingReferee = new GamingReferee();
        gamingReferee.setAvatar(mListForChangeSeat.get(6).getAvatar());
        gamingReferee.setId(mListForChangeSeat.get(6).getId());
        gamingReferee.setGender(mListForChangeSeat.get(6).getGender());
        gamingReferee.setPosition(mListForChangeSeat.get(6).getPosition());
        gamingReferee.setSort(7);
        gamingReferee.setSeatAvailable(false);

        return gamingReferee;
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void openInstructionDialog() {

    }

    @Override
    public void openUserDetailDialog(String userId) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void setBackKeyDisable(boolean isBackKeyDisable) {

    }

    @Override
    public void setActivityBackgroundLandScape() {

    }

    @Override
    public void setActivityBackgroundPortrait() {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public void openGamePlayingOfReferee(String hostName) {

    }

    @Override
    public void openGamePlayingOfPlayer(String hostName, int nowSort) {

    }

    @Override
    public void finishWaiting4JoinUi() {

    }

    @Override
    public void start() {

    }
}
