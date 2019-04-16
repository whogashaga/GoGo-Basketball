package com.kerry.gogobasketball.create_user;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreateUserPresenter implements CreateUserContract.Presenter {

    private final CreateUserContract.View mCreateUserView;
    private User mUser;
    private String mId;

    public CreateUserPresenter(@NonNull CreateUserContract.View createUserView) {
        mCreateUserView = checkNotNull(createUserView, "createUserView cannot be null!");
        mCreateUserView.setPresenter(this);
        mUser = new User();
        mId = "";
    }

    @Override
    public void getUserIniInfoFromLogin(String userDocId) {
        // 拿到 user doc id
        mUser.setFacebookId(userDocId);
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
    public void onUerIdEditTextChange(CharSequence charSequence) {
        mUser.setId(charSequence.toString());
        mId = charSequence.toString();
    }

    @Override
    public void getGenderFromRadioGroup(String gender) {
        mUser.setGender(gender);
    }

    @Override
    public void checkIfUserIdExisted() {
        Log.e("Kerry", "Presenter checkIfUserIdExisted : " + mUser.getId());

        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .whereEqualTo("id", mUser.getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                Log.d("Kerry","user.getId() = " + user.getId());
                                showErrorToast("此名稱已有人使用\n請重新輸入", true);
                            }
                        } else {
                            showErrorToast("此名稱可以使用", true);
                            mCreateUserView.openHomeUi();
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
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
    public void openHome() {

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
