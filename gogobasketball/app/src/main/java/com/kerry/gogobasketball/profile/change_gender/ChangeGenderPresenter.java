package com.kerry.gogobasketball.profile.change_gender;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.MainActivity;
import com.kerry.gogobasketball.util.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChangeGenderPresenter implements ChangeGenderContract.Presenter {

    private final ChangeGenderContract.View mChangeGenderView;
    private String mNewGender;
    private String mOldGender;


    public ChangeGenderPresenter(@NonNull ChangeGenderContract.View changeGenderView) {
        mChangeGenderView = checkNotNull(changeGenderView, "changeGenderView cannot be null!");
        mChangeGenderView.setPresenter(this);
        mNewGender = "male";
        mOldGender = "";
    }

    @Override
    public void getNowGenderFromProfile(String currentGender) {
        mOldGender = currentGender;
    }

    @Override
    public void getGenderFromRadios(String gender) {
        Log.e("Kerry", "getGenderFromRadios : " + gender);
        mNewGender = gender;
    }

    @Override
    public void compareOldNewGender(Activity activity) {
        Log.d("Kerry", "Old gender : " + mOldGender);
        Log.d("Kerry", "New gender : " + mNewGender);
        if (mNewGender.equals(mOldGender)) {
            mChangeGenderView.showErrorGender();
        } else {
            updateGenderData(activity);
        }
    }

    private void updateGenderData(Activity activity) {
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(((MainActivity) activity).getFacebookIdString(Constants.FACEBOOK_ID_FILE))
                .update(Constants.USER_GENDER, mNewGender)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "更換 ID 完成 ！!");
                        mChangeGenderView.showChangeGenderSuccessUi();
                        mChangeGenderView.showNewProfileUi();
                        mChangeGenderView.finishChangeGenderUi();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(Constants.TAG, "更換 ID Error !", e);
            }

        });
    }

    @Override
    public void showDataChangeSuccessDialog() {

    }

    @Override
    public void loadProfileUserData(Activity activity) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }

}