package com.kerry.gogobasketball.home.item;

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
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;
import java.util.List;

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

        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WaitingRoomInfo waitingRoomInfo = document.toObject(WaitingRoomInfo.class);
                                mWaitingRoomInfoList.add(waitingRoomInfo);
//                                Log.d("Kerry", "doc id = " + document.getId());
                            }
//                            Log.e("Kerry", "doc count = " + mWaitingRoomInfoList.size());
                            mLookingForRoomView.getWaitingRoomListFromPresenter(mWaitingRoomInfoList);
                        }
                    }
                });
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
    public void showErrorToast(String message) {

    }

    @Override
    public void start() {

    }

    @Override
    public boolean isHomeItemHasNextPaging(String itemType) {
        return false;
    }

    @Override
    public void onHomeItemScrollToBottom(String itemType) {

    }
}
