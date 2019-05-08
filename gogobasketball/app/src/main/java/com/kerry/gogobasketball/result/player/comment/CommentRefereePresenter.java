package com.kerry.gogobasketball.result.player.comment;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FireStoreHelper;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;

public class CommentRefereePresenter implements CommentRefereeContract.Presenter {


    private final CommentRefereeContract.View mCommentRefereeView;
    private String mRefereeName;
    private int mRating;

    public CommentRefereePresenter(@NonNull CommentRefereeContract.View commentView) {
        mCommentRefereeView = checkNotNull(commentView, "commentView cannot be null!");
        mCommentRefereeView.setPresenter(this);
        mRefereeName = "";
        mRating = -1;
    }


    @Override
    public void getRefereeNameFromResult(String refereeName) {
        Log.i(Constants.TAG, "getRefereeNameFromResult refereeName : " + refereeName);
        mRefereeName = refereeName;
    }

    @Override
    public void onWheelViewChanged(int rating) {
        mRating = rating;
    }

    @Override
    public void queryRefereeUserDocId() {
        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .whereEqualTo(Constants.USER_ID, mRefereeName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                setRefereeRating(user);
                            }
                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void setRefereeRating(User user) {
        user.getRefereeRecord().setRating(user.getRefereeRecord().getRating() + mRating);
        user.getRefereeRecord().setCommenter(user.getRefereeRecord().getCommenter() + 1);
        setRefereeAvRating(user);
    }

    private void setRefereeAvRating(User user) {
        float avRating = (float) user.getRefereeRecord().getRating() / user.getRefereeRecord().getCommenter();
        user.getRefereeRecord().setAvRating(avRating);
        updateRefereeRating2FireStore(user);
    }

    private void updateRefereeRating2FireStore(User user) {
        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .document(user.getFacebookId())
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Log.d(Constants.TAG, "上傳評價完成 ！!");
                    mCommentRefereeView.finishCommentUi();
                }).addOnFailureListener(e -> Log.e(Constants.TAG, "評價失敗 Error adding document", e));

    }

    @Override
    public void showBack2LobbyButtonPlayerResult() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showSendCommentSuccessDialog() {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void start() {

    }
}
