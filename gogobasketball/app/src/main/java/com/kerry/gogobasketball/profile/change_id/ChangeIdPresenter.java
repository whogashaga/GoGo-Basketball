package com.kerry.gogobasketball.profile.change_id;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.MainActivity;
import com.kerry.gogobasketball.util.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChangeIdPresenter implements ChangeIdContract.Presenter {

    private final ChangeIdContract.View mChangeIdView;
    private String mNewId;


    public ChangeIdPresenter(@NonNull ChangeIdContract.View commentView) {
        mChangeIdView = checkNotNull(commentView, "commentView cannot be null!");
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
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .whereEqualTo(Constants.USER_ID, mNewId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d("Kerry", "checkIfUserIdExists task size = " + task.getResult().size());
                        if (task.isSuccessful()) {
                            if (task.getResult().size() == 0) {
                                Log.d("Kerry", "此名稱可以使用");
                                createUserClickConfirm(activity);
                            } else {
                                Log.d("Kerry", "名稱已有人使用");
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

        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(((MainActivity) activity).getFacebookIdString(Constants.FACEBOOK_ID_FILE))
                .update(Constants.USER_ID, mNewId)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "更換 ID 完成 ！!");
                        mChangeIdView.showChangeIdSuccessUi();
                        mChangeIdView.showNewProfileUi();
                        mChangeIdView.finishChangeIdUi();
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
    public void start() {

    }
}
