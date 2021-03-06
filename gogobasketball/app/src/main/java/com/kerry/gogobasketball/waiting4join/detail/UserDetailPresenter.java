package com.kerry.gogobasketball.waiting4join.detail;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;

public class UserDetailPresenter implements UserDetailContract.Presenter {

    private final UserDetailContract.View mUserDetailView;

    public UserDetailPresenter(@NonNull UserDetailContract.View userDetailView) {
        mUserDetailView = checkNotNull(userDetailView, "userDetailView cannot be null!");
        mUserDetailView.setPresenter(this);
    }

    @Override
    public void getUserIdFromWaiting(String userId) {
        Log.d(Constants.TAG, "UserDetailPresenter getUserIdFromWaiting userID = " + userId);
        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .whereEqualTo(Constants.USER_ID, userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserDetailView.shotDetailUi(user);
                        }
                    } else {
                        Log.w(Constants.TAG, "Error getting documents.", task.getException());
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "getUserIdFromWaiting onFailure Error !"));
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void queryUserInfoDocId() {

    }

    @Override
    public void start() {

    }
}
