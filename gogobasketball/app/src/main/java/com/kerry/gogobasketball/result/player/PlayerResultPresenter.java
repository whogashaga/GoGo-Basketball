package com.kerry.gogobasketball.result.player;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.data.GamingPlayer;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.util.Constants;

public class PlayerResultPresenter implements PlayerResultContract.Presenter {

    private final PlayerResultContract.View mGameResultView;
    private String mHostName;
    private String mRoomDocId;

    public PlayerResultPresenter(@NonNull PlayerResultContract.View refereeGoingView) {
        mGameResultView = checkNotNull(refereeGoingView, "GameResultView cannot be null!");
        mGameResultView.setPresenter(this);
        mHostName = "";
        mRoomDocId = "";
    }

    @Override
    public void getHostNameFromPlayerGoing(String hostName, int currentSort) {
        Log.e("Kerry", "getHostNameFromPlayerGoing HostName = " + hostName);
        mHostName = hostName;
        mGameResultView.getHostNameFromPresenter(hostName, currentSort);
    }

    @Override
    public void getRoomInfoFromFireStorePlayer(String hostName, int currentSort) {

        FirebaseFirestore.getInstance()
                .collection(Constants.GAMING_ROOM)
                .whereEqualTo(Constants.HOST_NAME, hostName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(Constants.TAG, "mRoomDocId = " + document.getId());
                                mRoomDocId = document.getId();
                                GamingRoomInfo gamingRoomInfo = document.toObject(GamingRoomInfo.class);
                                mGameResultView.showResultPlayerUi(gamingRoomInfo, currentSort);
                                Log.w(Constants.TAG, "gamingRoomInfo = " + gamingRoomInfo.toString());
                                getPlayerRecordFromRoom(gamingRoomInfo, currentSort);
                            }
                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void getPlayerRecordFromRoom(GamingRoomInfo gamingRoomInfo, int currentSort) {

        if (currentSort == 1) {
            getUserDocId(gamingRoomInfo.getPlayer1(), gamingRoomInfo.getWinner(), currentSort);
        } else if (currentSort == 2) {
            getUserDocId(gamingRoomInfo.getPlayer2(), gamingRoomInfo.getWinner(), currentSort);
        } else if (currentSort == 3) {
            getUserDocId(gamingRoomInfo.getPlayer3(), gamingRoomInfo.getWinner(), currentSort);
        } else if (currentSort == 4) {
            getUserDocId(gamingRoomInfo.getPlayer4(), gamingRoomInfo.getWinner(), currentSort);
        } else if (currentSort == 5) {
            getUserDocId(gamingRoomInfo.getPlayer5(), gamingRoomInfo.getWinner(), currentSort);
        } else if (currentSort == 6) {
            getUserDocId(gamingRoomInfo.getPlayer6(), gamingRoomInfo.getWinner(), currentSort);
        } else {
            Log.d(Constants.TAG, "PlayerResultPresenter getPlayerRecordFromRoom Error !");
        }
    }

    private void getUserDocId(GamingPlayer gamingPlayer, String winner, int currentSort) {

        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .whereEqualTo(Constants.USER_ID, gamingPlayer.getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                Log.e(Constants.TAG, "document.toObject(User.class) ID = " + user.getId());
                                setRecords2User(user, gamingPlayer, winner, currentSort);
                            }
                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                }).addOnFailureListener(e -> {
            Log.d(Constants.TAG, "getUserDocId Error ! ");
        });
    }

    private void setRecords2User(User user, GamingPlayer gamingPlayer, String winner, int currentSort) {

        Log.d(Constants.TAG, "setRecords2User @ ");
        user.getPlayerRecord().setGames(user.getPlayerRecord().getGames() + 1);
        user.getPlayerRecord().setScore(user.getPlayerRecord().getScore() + gamingPlayer.getScore());
        user.getPlayerRecord().setRebound(user.getPlayerRecord().getRebound() + gamingPlayer.getRebound());
        user.getPlayerRecord().setFoul(user.getPlayerRecord().getFoul() + gamingPlayer.getFoul());

        if (currentSort == 1 || currentSort == 2 || currentSort == 3) {
            if (winner.equals("a")) {
                user.getPlayerRecord().setWinning(user.getPlayerRecord().getWinning() + 1);
            } else {
                Log.d(Constants.TAG, "You Lose !!");
            }
        } else {
            if (winner.equals("b")) {
                user.getPlayerRecord().setWinning(user.getPlayerRecord().getWinning() + 1);
            } else {
                Log.d(Constants.TAG, "You Lose !!");
            }
        }
        setAvRecord2User(user);
    }

    private void setAvRecord2User(User user) {
        float winRate = (float) user.getPlayerRecord().getWinning() / user.getPlayerRecord().getGames();
        user.getPlayerRecord().setAvWinRate(winRate);

        float avScore = (float) user.getPlayerRecord().getScore() / user.getPlayerRecord().getGames();
        user.getPlayerRecord().setAvScore(avScore);

        float avRebound = (float) user.getPlayerRecord().getRebound() / user.getPlayerRecord().getGames();
        user.getPlayerRecord().setAvRebound(avRebound);

        float avFoul = (float) user.getPlayerRecord().getFoul() / user.getPlayerRecord().getGames();
        user.getPlayerRecord().setAvFoul(avFoul);

        updatePersonalRecord2User(user);
    }

    private void updatePersonalRecord2User(User user) {
        Log.d(Constants.TAG, "updatePersonalRecord2User @ ");
        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .document(user.getFacebookId())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void avoid) {
                        Log.d(Constants.TAG, "個人紀錄上傳完成 ！!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(Constants.TAG, "紀錄上傳失敗！", e);
            }

        });
    }

    @Override
    public void setBack2LobbyVisible() {
        mGameResultView.showLobbyButton();
    }

    @Override
    public void setHave2Comment(boolean have2) {

    }


    @Override
    public void setActivityBackgroundLandScape() {

    }

    @Override
    public void setActivityBackgroundPortrait() {

    }

    @Override
    public void openCommentReferee(String hostName) {

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
    public void setBackKeyDisable(boolean isBackKeyDisable) {

    }

    @Override
    public void start() {

    }
}
