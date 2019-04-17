package com.kerry.gogobasketball.create_user;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

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

        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(userFbId)
                .get()
                .addOnSuccessListener(snapshot -> {
                   User user = snapshot.toObject(User.class);
                   mUser = user;

                }).addOnFailureListener(e -> Log.d("Kerry", "no internet to create user ！"));
    }

    @Override
    public void getPositionFromSpinner(String position) {
        if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_center))) {
            mUser.setPosition("center");
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_pf))) {
            mUser.setPosition("pf");
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_sf))) {
            mUser.setPosition("sf");
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_pg))) {
            mUser.setPosition("pg");
        } else if (position.equals(GoGoBasketball.getAppContext().getString(R.string.position_sg))) {
            mUser.setPosition("sg");
        } else {
            Log.d(Constants.TAG, "CreateUser Fragment getPositionFromSpinner Error!! ");
        }
    }

    @Override
    public void onUserIdEditTextChange(CharSequence charSequence) {
        mUser.setId(charSequence.toString());
        mId = charSequence.toString();
    }

    @Override
    public void getGenderFromRadioGroup(String gender) {
        Log.d("Kerry", "getGenderFromRadioGroup = " + gender);
        mUser.setGender(gender);
    }

    @Override
    public void createUserClickConfirm() {

        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(mUser.getFacebookId())
                .set(mUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "創建角色完成 ！!");
                        mCreateUserView.showCreateUserSuccessUi();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(Constants.TAG, "創建角色 Error adding document", e);
            }

        });
    }

    @Override
    public void showCreateUserSuccessDialog() {

    }

    @Override
    public void onCreateUserSuccess() {

    }

    @Override
    public void showCreateSuccessDialog() {

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
        Toast toast;

        if (isShort) {
            toast = Toast.makeText(GoGoBasketball.getAppContext(), "無效", Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(GoGoBasketball.getAppContext(), "無效", Toast.LENGTH_LONG);
        }

        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(16);
        toastTV.setText(message);
        toast.show();
    }

    @Override
    public void start() {

    }

}
