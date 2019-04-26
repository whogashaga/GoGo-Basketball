package com.kerry.gogobasketball.home.item;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class Looking4RoomPresenter implements Looking4RoomContract.Presenter {

    private final Looking4RoomContract.View mLookingForRoomView;
    private ArrayList<WaitingRoomInfo> mWaitingRoomInfoList;

    public Looking4RoomPresenter(@NonNull Looking4RoomContract.View lookingForRoomView) {
        mLookingForRoomView = checkNotNull(lookingForRoomView, "Looking4RoomView cannot be null!");
        mLookingForRoomView.setPresenter(this);
        mWaitingRoomInfoList = new ArrayList<>();

    }


    @Override
    public void loadExistedRoomsData4RecyclerView() {
        mWaitingRoomInfoList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .orderBy("totalPlayerAmount")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WaitingRoomInfo waitingRoomInfo = document.toObject(WaitingRoomInfo.class);
                                mWaitingRoomInfoList.add(waitingRoomInfo);
                            }
                            mLookingForRoomView.showWaitingRoomListUi(mWaitingRoomInfoList);
                        }
                    }
                });
    }

    @Override
    public void setRoomListSnapshotListerSlave() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(Constants.TAG, "Listen failed.", e);
                            return;
                        }
                        loadExistedRoomsData4RecyclerView();
                    }
                });

    }

    @Override
    public void openFindHostDialog() {

    }

    @Override
    public void getWaitingRoomFromFindHost(ArrayList<WaitingRoomInfo> list) {
        mLookingForRoomView.showWaitingRoomListUi(list);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }


    @Override
    public void setExistedRoomsData() {

    }

    @Override
    public void openWant2CreateRoom() {

    }

    @Override
    public void openWaiting4JoinSlave(WaitingRoomInfo waitingRoomInfo) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void start() {

    }
}
