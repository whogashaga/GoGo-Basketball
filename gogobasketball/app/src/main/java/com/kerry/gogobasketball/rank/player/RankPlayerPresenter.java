package com.kerry.gogobasketball.rank.player;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class RankPlayerPresenter implements RankPlayerContract.Presenter {

    private final RankPlayerContract.View mRankPlayerView;
    private ArrayList<User> mUserList;

    public RankPlayerPresenter(@NonNull RankPlayerContract.View profileView) {
        mRankPlayerView = checkNotNull(profileView, "RankPlayerView cannot be null!");
        mRankPlayerView.setPresenter(this);
        mUserList = new ArrayList<>();
    }

    @Override
    public void loadRankPlayerByWinning() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.winning", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        Log.e("Kerry","presenter winning = " + GoGoBasketball.getAppContext().getString(R.string.rank_winning));
                        mRankPlayerView.showRankPlayerUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_winning));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByWinning Error ! "));
    }

    @Override
    public void loadRankPlayerByScore() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.score", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankPlayerView.showRankPlayerUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_score));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByScore Error ! "));
    }

    @Override
    public void loadRankPlayerByRebound() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.rebound", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankPlayerView.showRankPlayerUi(mUserList,GoGoBasketball.getAppContext().getString(R.string.rank_rebound));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByRebound Error ! "));
    }

    @Override
    public void loadRankPlayerByFoul() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.rebound", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankPlayerView.showRankPlayerUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_foul));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByFoul Error ! "));
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
