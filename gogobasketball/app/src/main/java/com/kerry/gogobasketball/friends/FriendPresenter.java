package com.kerry.gogobasketball.friends;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.util.Constants;

public class FriendPresenter implements FriendContract.Presenter {

    private final FriendContract.View mFriendView;
    private WaitingRoomSeats mWaitingRoomSeats;
    private String mFbId;

    public FriendPresenter(@NonNull FriendContract.View friendView) {
        mFriendView = checkNotNull(friendView, "FriendView cannot be null!");
        mFriendView.setPresenter(this);
        mWaitingRoomSeats = new WaitingRoomSeats();
        mFbId = "2681615865187626";
    }

    @Override
    public void postDemoPlayer(int position) {
        if (position == 0) {
            setWaitingSeatInfo("https://graph.facebook.com/2656927084321759/picture?type=large",
                    Constants.GENDER_MALE, "某王姓房東", Constants.POSITION_PF, 1);
        } else if (position == 1) {
            setWaitingSeatInfo("https://graph.facebook.com/2712588565425023/picture?type=large",
                    Constants.GENDER_MALE, "AKA小安老師", Constants.POSITION_SG, 2);
        } else if (position == 2) {
            setWaitingSeatInfo("https://graph.facebook.com/2349781725073504/picture?type=large",
                    Constants.GENDER_MALE, "感林梁家輝", Constants.POSITION_CENTER, 3);
        } else if (position == 3) {
            setWaitingSeatInfo("https://graph.facebook.com/2308451832569744/picture?type=large",
                    Constants.GENDER_FEMALE, "安卓女王", Constants.POSITION_PG, 4);
        } else if (position == 4) {
            setWaitingSeatInfo("https://graph.facebook.com/2211003892309520/picture?type=large",
                    Constants.GENDER_MALE, "賣鞋張家輝", Constants.POSITION_PF, 5);
        } else if (position == 5) {
            setWaitingSeatInfo("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRm5GEvbdDIT79MAb8GMwFJCTCsv5rHCcWF2MbSBSQr9k3VtaMk",
                    Constants.GENDER_MALE, "最高領導人", Constants.POSITION_SG, 6);
        } else {
            Log.d(Constants.TAG, "postDemoPlayer Error !");
        }
    }

    private void setWaitingSeatInfo(String avatar, String gender, String id, String position, int sort) {

        mWaitingRoomSeats.setAvatar(avatar);
        mWaitingRoomSeats.setGender(gender);
        mWaitingRoomSeats.setId(id);
        mWaitingRoomSeats.setPosition(position);
        mWaitingRoomSeats.setSort(sort);
        mWaitingRoomSeats.setSeatAvailable(false);

        updateDemoSeat(mWaitingRoomSeats);
    }

    private void updateDemoSeat(WaitingRoomSeats waitingRoomSeats) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mFbId)
                .collection(Constants.WAITING_SEATS)
                .document(waitingRoomSeats.getId())
                .set(waitingRoomSeats)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Kerry", "DEMO人物加入成功");

                }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    @Override
    public void updateDemoTotalNumber(int amount) {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mFbId)
                .update("totalPlayerAmount", amount)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Kerry", "Demo 總人數更新為 " + amount);
                    if (amount == 6){
                        updateDemoPlayerNumber5();
                    } else {
                        updateDemoPlayerNumber6();
                    }
                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error updating document", e));
    }

    private void updateDemoPlayerNumber5() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mFbId)
                .update("playerAmount", 5)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Kerry", "Demo 球員人數更新完成！");

                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error updating document", e));
    }

    private void updateDemoPlayerNumber6() {
        FirestoreHelper.getFirestore()
                .collection(Constants.WAITING_ROOM)
                .document(mFbId)
                .update("playerAmount", 6)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Kerry", "Demo 球員人數更新完成！");

                })
                .addOnFailureListener(e -> Log.w(Constants.TAG, "Error updating document", e));
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
