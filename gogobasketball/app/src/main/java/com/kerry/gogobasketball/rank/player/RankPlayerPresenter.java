package com.kerry.gogobasketball.rank.player;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
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
    public void loadRankPlayerByGames() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.games", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankPlayerView.showRankPlayerUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_total_games));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByGames Error ! "));
    }

    @Override
    public void loadRankPlayerByWinning() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.avWinRate", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankPlayerView.showRankPlayerUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_win_rate));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByWinning Error ! "));
    }

    @Override
    public void loadRankPlayerByScore() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.avScore", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankPlayerView.showRankPlayerUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_av_score));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByScore Error ! "));
    }

    @Override
    public void loadRankPlayerByRebound() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.avRebound", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankPlayerView.showRankPlayerUi(mUserList,GoGoBasketball.getAppContext().getString(R.string.rank_av_rebound));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByRebound Error ! "));
    }

    @Override
    public void loadRankPlayerByFoul() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("playerRecord.avFoul", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankPlayerView.showRankPlayerUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_av_foul));
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