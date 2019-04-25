package com.kerry.gogobasketball.profile.change_position;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.MainActivity;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.util.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChangePositionPresenter implements ChangePositionContract.Presenter {

    private final ChangePositionContract.View mChangePositionView;
    private String mNewPosition;
    private String mOldPosition;


    public ChangePositionPresenter(@NonNull ChangePositionContract.View changePositionView) {
        mChangePositionView = checkNotNull(changePositionView, "changePositionView cannot be null!");
        mChangePositionView.setPresenter(this);
        mNewPosition = "c";
        mOldPosition = "";
    }

    @Override
    public void getNowPositionFromProfile(String currentPosition) {
        mOldPosition = currentPosition;
    }

    @Override
    public void getPositionFromWheel(String position) {

        if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_center))) {
            mNewPosition = Constants.POSITION_CENTER;
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_pf))) {
            mNewPosition = Constants.POSITION_PF;
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_sf))) {
            mNewPosition = Constants.POSITION_SF;
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_pg))) {
            mNewPosition = Constants.POSITION_PG;
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_sg))) {
            mNewPosition = Constants.POSITION_SG;
        } else {
            Log.d(Constants.TAG, "getPositionFromWheel Error ! ");
        }
    }

    @Override
    public void compareNewOldPosition(Activity activity) {

        if (mNewPosition.equals(mOldPosition)){
            mChangePositionView.showErrorPosition();
        } else {
            updatePositionData(activity);
        }

    }

    private void updatePositionData(Activity activity) {
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(((MainActivity) activity).getFacebookIdString(Constants.FACEBOOK_ID_FILE))
                .update(Constants.USER_POSITION, mNewPosition)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "更換 position 完成 ！!");
                        mChangePositionView.showChangePositionSuccessUi();
                        mChangePositionView.showNewProfileUi();
                        mChangePositionView.finishChangePositionUi();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(Constants.TAG, "更換 position Error !", e);
            }

        });
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void showDataChangeSuccessDialog() {

    }

    @Override
    public void loadProfileUserData(Activity activity) {

    }

    @Override
    public void start() {

    }
}
