package com.kerry.gogobasketball.create1user;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;

public class CreateUserPresenter implements CreateUserContract.Presenter {

    private final CreateUserContract.View mCreateUserView;
    private User mUser;
    private String mUserFbId;
    private String mId;

    public CreateUserPresenter(@NonNull CreateUserContract.View createUserView) {
        mCreateUserView = checkNotNull(createUserView, "createUserView cannot be null!");
        mCreateUserView.setPresenter(this);
        mUser = new User();
        mId = "";
        mUserFbId = "";
    }

    @Override
    public void getUserIniInfoFromLogin(String userFbId) {
        // 拿到 user doc id
        mUserFbId = userFbId;
        getUserDocFromLoginUpdate(userFbId);
    }

    private void getUserDocFromLoginUpdate(String userFbId) {

        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .document(userFbId)
                .get()
                .addOnSuccessListener(snapshot -> {
                    User user = snapshot.toObject(User.class);
                    mUser = user;

                }).addOnFailureListener(e -> Log.d(Constants.TAG, "no internet to create user ！"));
    }

    @Override
    public void getPositionFromSpinner(String position) {
        if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_center))) {
            mUser.setPosition(Constants.POSITION_CENTER);
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_pf))) {
            mUser.setPosition(Constants.POSITION_PF);
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_sf))) {
            mUser.setPosition(Constants.POSITION_SF);
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_pg))) {
            mUser.setPosition(Constants.POSITION_PG);
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_sg))) {
            mUser.setPosition(Constants.POSITION_SG);
        } else {
            Log.d(Constants.TAG, "CreateUser Fragment getPositionFromSpinner Error!! ");
        }
    }

    @Override
    public void onUserIdEditTextChange(CharSequence charSequence) {
        mUser.setId(charSequence.toString().trim());
        mId = charSequence.toString().trim();
    }

    @Override
    public void getGenderFromRadioGroup(String gender) {
        Log.d(Constants.TAG, "getGenderFromRadioGroup = " + gender);
        mUser.setGender(gender);
    }

    @Override
    public void checkIfUserIdExists() {

        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .whereEqualTo(Constants.USER_ID, mUser.getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d(Constants.TAG, "checkIfUserIdExists task size = " + task.getResult().size());
                        if (task.isSuccessful()) {
                            if (task.getResult().size() == 0) {
                                Log.d(Constants.TAG, "此名稱可以使用");
                                createUserClickConfirm();
                            } else {
                                Log.d(Constants.TAG, "名稱已有人使用");
                                mCreateUserView.showIdAlreadyExist();
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
    public void createUserClickConfirm() {

        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .document(mUser.getFacebookId())
                .set(mUser)
                .addOnSuccessListener(avoid -> {
                    Log.d(Constants.TAG, "創建角色完成 ！!");
                    mCreateUserView.showCreateUserSuccessUi();
                }).addOnFailureListener(e -> Log.e(Constants.TAG, "創建角色 Error adding document", e));
    }

    @Override
    public void finishCreateUser() {

    }

    @Override
    public void showCreateUserSuccessDialog() {

    }

    @Override
    public void onCreateUserSuccess() {

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
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void start() {

    }

}
