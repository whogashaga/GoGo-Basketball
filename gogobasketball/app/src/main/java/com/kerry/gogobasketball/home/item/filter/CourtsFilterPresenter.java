package com.kerry.gogobasketball.home.item.filter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.CourtsInfo;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class CourtsFilterPresenter implements CourtsFilterContract.Presenter {

    private CourtsFilterContract.View mFilterView;
    private ArrayList<String> mCourtsList;
    private String mLocation;

    public CourtsFilterPresenter(@NonNull CourtsFilterContract.View filterView) {
        mFilterView = checkNotNull(filterView, "filterView cannot be null!");
        mFilterView.setPresenter(this);
        mCourtsList = new ArrayList<>();
        mLocation = GoGoBasketball.getAppContext().getString(R.string.adidas_101);
    }

    @Override
    public void getLocationFromWheel(String position) {
        if (position.equals(GoGoBasketball.getAppContext().getString(R.string.adidas_101))) {
            mLocation = GoGoBasketball.getAppContext().getString(R.string.adidas_101);
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.ba_der_park))) {
            mLocation = GoGoBasketball.getAppContext().getString(R.string.ba_der_park);
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.banqiao_second))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.banqiao_second));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.da_an_park))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.da_an_park));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.dinosaur_park))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.dinosaur_park));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.fourth_823_park))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.fourth_823_park));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.fu_her_bridge))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.fu_her_bridge));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.green_stone_park))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.green_stone_park));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.gu_ting_river))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.gu_ting_river));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.mac_handsome_bridge))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.mac_handsome_bridge));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.shuang_yuan_river))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.shuang_yuan_river));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.song_san_high_school))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.song_san_high_school));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.tai_da_central))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.tai_da_central));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.wan_ban_bridge))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.wan_ban_bridge));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.xin_sheng_park))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.xin_sheng_park));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.xin_sheng_high))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.xin_sheng_high));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.xiu_lang_bridge))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.xiu_lang_bridge));
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.younth_park))) {
            mLocation = (GoGoBasketball.getAppContext().getString(R.string.younth_park));
        } else {
            Log.d(Constants.TAG, "getPositionFromWheel Error ! ");
        }
    }

    @Override
    public void queryCourts(){
        FirebaseFirestore.getInstance()
                .collection(Constants.WAITING_ROOM)
                .whereEqualTo("courtLocation", mLocation)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d(Constants.TAG, "checkIfUserIdExists task size = " + task.getResult().size());
                        if (task.isSuccessful()) {
                            if (task.getResult().size() == 0) {
                                 mFilterView.showFindNoCourts();
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    WaitingRoomInfo waitingRoomInfo = document.toObject(WaitingRoomInfo.class);
                                    ArrayList<WaitingRoomInfo> list = new ArrayList<>();
                                    list.add(waitingRoomInfo);
                                    mFilterView.showCourtsFilterSuccessUi(list);
                                }
                            }
                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                }).addOnFailureListener(e -> {
            Log.w(Constants.TAG, "checkIfUserIdExists Error !");
        });
    }

    @Override
    public void getCourtsListFromFirebase() {
        mCourtsList.clear();

        FirebaseFirestore.getInstance()
                .collection(Constants.COURTS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CourtsInfo courtsInfo = document.toObject(CourtsInfo.class);
                                mCourtsList.add(courtsInfo.getLocation());
                            }
                            mFilterView.setWheelPicker(mCourtsList);

                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void updateRecyclerView(Activity activity) {

    }

    @Override
    public void updateLooking4RoomView(ArrayList<WaitingRoomInfo> list) {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
