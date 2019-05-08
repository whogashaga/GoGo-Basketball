package com.kerry.gogobasketball.profile.id;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FireStoreHelper;
import com.kerry.gogobasketball.util.Constants;

public class ChangeIdPresenter implements ChangeIdContract.Presenter {

    private final ChangeIdContract.View mChangeIdView;
    private String mNewId;


    public ChangeIdPresenter(@NonNull ChangeIdContract.View changeIdView) {
        mChangeIdView = checkNotNull(changeIdView, "changeIdView cannot be null!");
        mChangeIdView.setPresenter(this);
        mNewId = "";
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void onUserNewIdEditTextChange(CharSequence charSequence) {
        mNewId = charSequence.toString().trim();
    }

    @Override
    public void checkIfUserNewIdExists(Activity activity) {
        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .whereEqualTo(Constants.USER_ID, mNewId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d(Constants.TAG, "checkIfUserIdExists task size = " + task.getResult().size());
                        if (task.isSuccessful()) {
                            if (task.getResult().size() == 0) {
                                Log.d(Constants.TAG, "此名稱可以使用");
                                createUserClickConfirm(activity);
                            } else {
                                Log.d(Constants.TAG, "名稱已有人使用");
                                mChangeIdView.showIdAlreadyExist();
                            }
                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                }).addOnFailureListener(e -> {
            Log.w(Constants.TAG, "checkIfUserIdExists Error !");
        });
    }


    public void createUserClickConfirm(Activity activity) {

        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .document(AccessToken.getCurrentAccessToken().getUserId().trim())
                .update(Constants.USER_ID, mNewId)
                .addOnSuccessListener(aVoid -> {
                    Log.d(Constants.TAG, "更換 Position 完成 ！!");
                    mChangeIdView.showChangeIdSuccessUi();
                    mChangeIdView.showNewProfileUi();
                    mChangeIdView.finishChangeIdUi();
                }).addOnFailureListener(e -> Log.e(Constants.TAG, "更換 Position Error !", e));
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
