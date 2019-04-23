package com.kerry.gogobasketball.rank.referee;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.GoGoBasketball;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.rank.RankContract;
import com.kerry.gogobasketball.rank.player.RankPlayerFragment;
import com.kerry.gogobasketball.util.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class RankRefereePresenter implements RankRefereeContract.Presenter {

    private final RankRefereeContract.View mRankRefereeView;
    private ArrayList<User> mUserList;

    public RankRefereePresenter(@NonNull RankRefereeContract.View profileView) {
        mRankRefereeView = checkNotNull(profileView, "RankRefereeView cannot be null!");
        mRankRefereeView.setPresenter(this);
        mUserList = new ArrayList<>();
    }

    @Override
    public void loadRankRefereeByJustices() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("refereeRecord.justices", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankRefereeView.showRankRefereeUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_total_justices));
                        Log.d("Kerry","mUserList size = "+mUserList.size());
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByGames Error ! "));
    }

    @Override
    public void loadRankRefereeByRating() {
        mUserList.clear();
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .orderBy("refereeRecord.avRating", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            mUserList.add(user);
                        }
                        mRankRefereeView.showRankRefereeUi(mUserList, GoGoBasketball.getAppContext().getString(R.string.rank_total_rating));
                    }
                }).addOnFailureListener(e -> Log.d(Constants.TAG, "loadRankPlayerByGames Error ! "));
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
