package com.kerry.gogobasketball.playing.referee;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FirestoreHelper;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.UserManager;

public class RefereeGoingPresenter implements RefereeGoingContract.Presenter {

    private final RefereeGoingContract.View mGamePlayingView;

    private GamingRoomInfo mGamingRoomInfo;
    private String mHostName;
    private String mRoomDocId;
    private int mIntScoreP1, mIntReboundP1, mIntFoulP1;
    private int mIntScoreP2, mIntReboundP2, mIntFoulP2;
    private int mIntScoreP3, mIntReboundP3, mIntFoulP3;
    private int mIntScoreP4, mIntReboundP4, mIntFoulP4;
    private int mIntScoreP5, mIntReboundP5, mIntFoulP5;
    private int mIntScoreP6, mIntReboundP6, mIntFoulP6;
    private int mIntScoreA;
    private int mIntScoreB;

    public RefereeGoingPresenter(@NonNull RefereeGoingContract.View refereeGoingView) {
        mGamePlayingView = checkNotNull(refereeGoingView, "GamePlayingView cannot be null!");
        mGamePlayingView.setPresenter(this);
        mHostName = "";
        mRoomDocId = "";
        mGamingRoomInfo = new GamingRoomInfo();
        mIntScoreP1 = 0;
        mIntScoreP2 = 0;
        mIntScoreP3 = 0;
        mIntScoreP4 = 0;
        mIntScoreP5 = 0;
        mIntScoreP6 = 0;
        mIntReboundP1 = 0;
        mIntReboundP2 = 0;
        mIntReboundP3 = 0;
        mIntReboundP4 = 0;
        mIntReboundP5 = 0;
        mIntReboundP6 = 0;
        mIntFoulP1 = 0;
        mIntFoulP2 = 0;
        mIntFoulP3 = 0;
        mIntFoulP4 = 0;
        mIntFoulP5 = 0;
        mIntFoulP6 = 0;
        mIntScoreA = 0;
        mIntScoreB = 0;
    }

    @Override
    public void getHostNameFromWaitingJoin(String hostName) {
        mHostName = hostName;
//        mGamingRoomInfo.setHostName(hostName);
        mGamePlayingView.getHostNameFromPresenter(hostName);
    }

    @Override
    public void getGamingRoomFromFireStore(String hostName) {
        Log.d("Kerry", "getGamingRoomFromFireStore hostName = " + hostName);
        FirestoreHelper.getFirestore()
                .collection(Constants.GAMING_ROOM)
                .whereEqualTo(Constants.HOST_NAME, hostName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mRoomDocId = document.getId();
                                GamingRoomInfo gamingRoomInfo = document.toObject(GamingRoomInfo.class);
                                mGamingRoomInfo = gamingRoomInfo;
                                mGamePlayingView.showPlayingGameUi(gamingRoomInfo);
//                                Log.w("Kerry", "gaming room id = " + document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("Kerry", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Game Over */

    @Override
    public void checkWhichTeamWon() {
        if (mIntScoreA < 6 && mIntScoreB < 6) {
            mGamePlayingView.showErrorToast("請繼續完成比賽！", true);
        } else {
            if (mIntScoreA == mIntScoreB) {
                mGamePlayingView.showErrorToast("沒有平手的啦！", true);
            } else {
                updateGameResultOfPlayer(setFinalResult(mGamingRoomInfo));
            }
        }
    }

    @Override
    public void updateGameResultOfPlayer(GamingRoomInfo gamingRoomInfo) {
        FirestoreHelper.getFirestore()
                .collection(Constants.GAMING_ROOM)
                .document(mRoomDocId)
                .set(gamingRoomInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "Referee 將每人戰績上傳!");
                        mGamePlayingView.setGamingNow(false);
                        Log.d(Constants.TAG, "Referee Going gamingRoomInfo.getHostName() = ");
                        mGamePlayingView.openGameResultRefereeUi(gamingRoomInfo.getHostName());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Kerry", "Error adding document", e);
            }
        });
    }

    @Override
    public void getRefereeUserData(Activity activity) {
        UserManager.getInstance().getUserProfile(activity, new UserManager.LoadCallback() {
            @Override
            public void onSuccess(User user) {
                plusOneJustice(user);
            }

            @Override
            public void onFail(String errorMessage) {

            }

            @Override
            public void onInvalidToken(String errorMessage) {

            }
        });
    }

    private void plusOneJustice(User user) {
        user.getRefereeRecord().setJustices(user.getRefereeRecord().getJustices() + 1);
        updateRefereeJustice(user);
    }

    private void updateRefereeJustice(User user) {
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(user.getFacebookId())
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Kerry", "執法場次加一 ！!");
                }).addOnFailureListener(e -> Log.e(Constants.TAG, "執法 Error adding document", e));
    }

    private GamingRoomInfo setFinalResult(GamingRoomInfo gamingRoomInfo) {

        gamingRoomInfo.getPlayer1().setScore(mIntScoreP1);
        gamingRoomInfo.getPlayer1().setRebound(mIntReboundP1);
        gamingRoomInfo.getPlayer1().setFoul(mIntFoulP1);

        gamingRoomInfo.getPlayer2().setScore(mIntScoreP2);
        gamingRoomInfo.getPlayer2().setRebound(mIntReboundP2);
        gamingRoomInfo.getPlayer2().setFoul(mIntFoulP2);

        gamingRoomInfo.getPlayer3().setScore(mIntScoreP3);
        gamingRoomInfo.getPlayer3().setRebound(mIntReboundP3);
        gamingRoomInfo.getPlayer3().setFoul(mIntFoulP3);

        gamingRoomInfo.getPlayer4().setScore(mIntScoreP4);
        gamingRoomInfo.getPlayer4().setRebound(mIntReboundP4);
        gamingRoomInfo.getPlayer4().setFoul(mIntFoulP4);

        gamingRoomInfo.getPlayer5().setScore(mIntScoreP5);
        gamingRoomInfo.getPlayer5().setRebound(mIntReboundP5);
        gamingRoomInfo.getPlayer5().setFoul(mIntFoulP5);

        gamingRoomInfo.getPlayer6().setScore(mIntScoreP6);
        gamingRoomInfo.getPlayer6().setRebound(mIntReboundP6);
        gamingRoomInfo.getPlayer6().setFoul(mIntFoulP6);

        if (mIntScoreA > mIntScoreB) {
            gamingRoomInfo.setWinner("a");
        } else {
            gamingRoomInfo.setWinner("b");
        }

        gamingRoomInfo.setStatus(Constants.STATUS_OVER);

        return gamingRoomInfo;
    }

    /* ------------------------------------------------------------------------------------------ */

    public int getIntScoreA() {
        mIntScoreA = mIntScoreP1 + mIntScoreP2 + mIntScoreP3;
        return mIntScoreA;
    }

    public int getIntScoreB() {
        mIntScoreB = mIntScoreP4 + mIntScoreP5 + mIntScoreP6;
        return mIntScoreB;
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player1 */

    @Override
    public void increaseScoreP1() {
        if (getIntScoreA() == 6) {
            mGamePlayingView.setScorePlusClickableTeamA(false);
        } else if (mIntScoreP1 < 6 && getIntScoreA() < 6) {
            mIntScoreP1 += 1;
            mGamePlayingView.setTextScoreP1(String.valueOf(mIntScoreP1));
            mGamePlayingView.setTextScoreTeamA(String.valueOf(getIntScoreA()));
            mGamePlayingView.setBtnClickableScoreMinusP1(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP1() {
        if (mIntScoreP1 == 0) {
            mGamePlayingView.setBtnClickableScoreMinusP1(false);
        } else if (0 < getIntScoreA() && getIntScoreA() < 7) {
            mIntScoreP1 -= 1;
            mGamePlayingView.setTextScoreP1(String.valueOf(mIntScoreP1));
            mGamePlayingView.setTextScoreTeamA(String.valueOf(getIntScoreA()));
            mGamePlayingView.setScorePlusClickableTeamA(true);
        } else {
            Log.d("Kerry", "P1 Score error !");
        }
    }

    @Override
    public void increaseReboundP1() {
        if (mIntReboundP1 < 20) {
            mIntReboundP1 += 1;
            mGamePlayingView.setTextReboundP1(String.valueOf(mIntReboundP1));
            mGamePlayingView.setBtnClickableReboundMinusP1(true);
        } else {
            mGamePlayingView.setBtnClickableReboundPlusP1(false);

        }
    }

    @Override
    public void decreaseReboundP1() {
        if (0 < mIntReboundP1 && mIntReboundP1 < 21) {
            mIntReboundP1 -= 1;
            mGamePlayingView.setTextReboundP1(String.valueOf(mIntReboundP1));
            mGamePlayingView.setBtnClickableReboundPlusP1(true);
        } else {
            mGamePlayingView.setBtnClickableReboundMinusP1(false);
        }
    }

    @Override
    public void increaseFoulP1() {
        if (mIntFoulP1 < 10) {
            mIntFoulP1 += 1;

            mGamePlayingView.setTextFoulP1(String.valueOf(mIntFoulP1));
            mGamePlayingView.setBtnClickableFoulMinusP1(true);
        } else {
            mGamePlayingView.setBtnClickableFoulPlusP1(false);
        }
    }

    @Override
    public void decreaseFoulP1() {
        if (0 < mIntFoulP1 && mIntFoulP1 < 11) {
            mIntFoulP1 -= 1;
            mGamePlayingView.setTextFoulP1(String.valueOf(mIntFoulP1));
            mGamePlayingView.setBtnClickableFoulPlusP1(true);
        } else {
            mGamePlayingView.setBtnClickableFoulMinusP1(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player2 */

    @Override
    public void increaseScoreP2() {
        if (getIntScoreA() == 6) {
            mGamePlayingView.setScorePlusClickableTeamA(false);
        } else if (mIntScoreP2 < 6 && getIntScoreA() < 6) {
            mIntScoreP2 += 1;
            mGamePlayingView.setTextScoreP2(String.valueOf(mIntScoreP2));
            mGamePlayingView.setTextScoreTeamA(String.valueOf(getIntScoreA()));
            mGamePlayingView.setBtnClickableScoreMinusP2(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP2() {
        if (mIntScoreP2 == 0) {
            mGamePlayingView.setBtnClickableScoreMinusP2(false);
        } else if (0 < getIntScoreA() && getIntScoreA() < 7) {
            mIntScoreP2 -= 1;
            mGamePlayingView.setTextScoreP2(String.valueOf(mIntScoreP2));
            mGamePlayingView.setTextScoreTeamA(String.valueOf(getIntScoreA()));
            mGamePlayingView.setScorePlusClickableTeamA(true);
        } else {
            Log.d("Kerry", "P2 Score error !");
        }
    }

    @Override
    public void increaseReboundP2() {
        if (mIntReboundP2 < 20) {
            mIntReboundP2 += 1;
            mGamePlayingView.setTextReboundP2(String.valueOf(mIntReboundP2));
            mGamePlayingView.setBtnClickableReboundMinusP2(true);
        } else {
            mGamePlayingView.setBtnClickableReboundPlusP2(false);

        }
    }

    @Override
    public void decreaseReboundP2() {
        if (0 < mIntReboundP2 && mIntReboundP2 < 21) {
            mIntReboundP2 -= 1;
            mGamePlayingView.setTextReboundP2(String.valueOf(mIntReboundP2));
            mGamePlayingView.setBtnClickableReboundPlusP2(true);
        } else {
            mGamePlayingView.setBtnClickableReboundMinusP2(false);
        }
    }

    @Override
    public void increaseFoulP2() {
        if (mIntFoulP2 < 10) {
            mIntFoulP2 += 1;

            mGamePlayingView.setTextFoulP2(String.valueOf(mIntFoulP2));
            mGamePlayingView.setBtnClickableFoulMinusP2(true);
        } else {
            mGamePlayingView.setBtnClickableFoulPlusP2(false);
        }
    }

    @Override
    public void decreaseFoulP2() {
        if (0 < mIntFoulP2 && mIntFoulP2 < 11) {
            mIntFoulP2 -= 1;
            mGamePlayingView.setTextFoulP2(String.valueOf(mIntFoulP2));
            mGamePlayingView.setBtnClickableFoulPlusP2(true);
        } else {
            mGamePlayingView.setBtnClickableFoulMinusP2(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player3 */

    @Override
    public void increaseScoreP3() {
        if (getIntScoreA() == 6) {
            mGamePlayingView.setScorePlusClickableTeamA(false);
        } else if (mIntScoreP3 < 6 && getIntScoreA() < 6) {
            mIntScoreP3 += 1;
            mGamePlayingView.setTextScoreP3(String.valueOf(mIntScoreP3));
            mGamePlayingView.setTextScoreTeamA(String.valueOf(getIntScoreA()));
            mGamePlayingView.setBtnClickableScoreMinusP3(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP3() {
        if (mIntScoreP3 == 0) {
            mGamePlayingView.setBtnClickableScoreMinusP3(false);
        } else if (0 < getIntScoreA() && getIntScoreA() < 7) {
            mIntScoreP3 -= 1;
            mGamePlayingView.setTextScoreP3(String.valueOf(mIntScoreP3));
            mGamePlayingView.setTextScoreTeamA(String.valueOf(getIntScoreA()));
            mGamePlayingView.setScorePlusClickableTeamA(true);
        } else {
            Log.d("Kerry", "P3 Score error !");
        }
    }

    @Override
    public void increaseReboundP3() {
        if (mIntReboundP3 < 20) {
            mIntReboundP3 += 1;
            mGamePlayingView.setTextReboundP3(String.valueOf(mIntReboundP3));
            mGamePlayingView.setBtnClickableReboundMinusP3(true);
        } else {
            mGamePlayingView.setBtnClickableReboundPlusP3(false);

        }
    }

    @Override
    public void decreaseReboundP3() {
        if (0 < mIntReboundP3 && mIntReboundP3 < 21) {
            mIntReboundP3 -= 1;
            mGamePlayingView.setTextReboundP3(String.valueOf(mIntReboundP3));
            mGamePlayingView.setBtnClickableReboundPlusP3(true);
        } else {
            mGamePlayingView.setBtnClickableReboundMinusP3(false);
        }
    }

    @Override
    public void increaseFoulP3() {
        if (mIntFoulP3 < 10) {
            mIntFoulP3 += 1;

            mGamePlayingView.setTextFoulP3(String.valueOf(mIntFoulP3));
            mGamePlayingView.setBtnClickableFoulMinusP3(true);
        } else {
            mGamePlayingView.setBtnClickableFoulPlusP3(false);
        }
    }

    @Override
    public void decreaseFoulP3() {
        if (0 < mIntFoulP3 && mIntFoulP3 < 11) {
            mIntFoulP3 -= 1;
            mGamePlayingView.setTextFoulP3(String.valueOf(mIntFoulP3));
            mGamePlayingView.setBtnClickableFoulPlusP3(true);
        } else {
            mGamePlayingView.setBtnClickableFoulMinusP3(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player4 */

    @Override
    public void increaseScoreP4() {
        if (getIntScoreB() == 6) {
            mGamePlayingView.setScorePlusClickableTeamB(false);
        } else if (mIntScoreP4 < 6 && getIntScoreB() < 6) {
            mIntScoreP4 += 1;
            mGamePlayingView.setTextScoreP4(String.valueOf(mIntScoreP4));
            mGamePlayingView.setTextScoreTeamB(String.valueOf(getIntScoreB()));
            mGamePlayingView.setBtnClickableScoreMinusP4(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP4() {
        if (mIntScoreP4 == 0) {
            mGamePlayingView.setBtnClickableScoreMinusP4(false);
        } else if (0 < getIntScoreB() && getIntScoreB() < 7) {
            mIntScoreP4 -= 1;
            mGamePlayingView.setTextScoreP4(String.valueOf(mIntScoreP4));
            mGamePlayingView.setTextScoreTeamB(String.valueOf(getIntScoreB()));
            mGamePlayingView.setScorePlusClickableTeamB(true);
        } else {
            Log.d("Kerry", "P4 Score error !");
        }
    }

    @Override
    public void increaseReboundP4() {
        if (mIntReboundP4 < 20) {
            mIntReboundP4 += 1;
            mGamePlayingView.setTextReboundP4(String.valueOf(mIntReboundP4));
            mGamePlayingView.setBtnClickableReboundMinusP4(true);
        } else {
            mGamePlayingView.setBtnClickableReboundPlusP4(false);

        }
    }

    @Override
    public void decreaseReboundP4() {
        if (0 < mIntReboundP4 && mIntReboundP4 < 21) {
            mIntReboundP4 -= 1;
            mGamePlayingView.setTextReboundP4(String.valueOf(mIntReboundP4));
            mGamePlayingView.setBtnClickableReboundPlusP4(true);
        } else {
            mGamePlayingView.setBtnClickableReboundMinusP4(false);
        }
    }

    @Override
    public void increaseFoulP4() {
        if (mIntFoulP4 < 10) {
            mIntFoulP4 += 1;

            mGamePlayingView.setTextFoulP4(String.valueOf(mIntFoulP4));
            mGamePlayingView.setBtnClickableFoulMinusP4(true);
        } else {
            mGamePlayingView.setBtnClickableFoulPlusP4(false);
        }
    }

    @Override
    public void decreaseFoulP4() {
        if (0 < mIntFoulP4 && mIntFoulP4 < 11) {
            mIntFoulP4 -= 1;
            mGamePlayingView.setTextFoulP4(String.valueOf(mIntFoulP4));
            mGamePlayingView.setBtnClickableFoulPlusP4(true);
        } else {
            mGamePlayingView.setBtnClickableFoulMinusP4(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player5 */

    @Override
    public void increaseScoreP5() {
        if (getIntScoreB() == 6) {
            mGamePlayingView.setScorePlusClickableTeamB(false);
        } else if (mIntScoreP5 < 6 && getIntScoreB() < 6) {
            mIntScoreP5 += 1;
            mGamePlayingView.setTextScoreP5(String.valueOf(mIntScoreP5));
            mGamePlayingView.setTextScoreTeamB(String.valueOf(getIntScoreB()));
            mGamePlayingView.setBtnClickableScoreMinusP5(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP5() {
        if (mIntScoreP5 == 0) {
            mGamePlayingView.setBtnClickableScoreMinusP5(false);
        } else if (0 < getIntScoreB() && getIntScoreB() < 7) {
            mIntScoreP5 -= 1;
            mGamePlayingView.setTextScoreP5(String.valueOf(mIntScoreP5));
            mGamePlayingView.setTextScoreTeamB(String.valueOf(getIntScoreB()));
            mGamePlayingView.setScorePlusClickableTeamB(true);
        } else {
            Log.d("Kerry", "P5 Score error !");
        }
    }

    @Override
    public void increaseReboundP5() {
        if (mIntReboundP5 < 20) {
            mIntReboundP5 += 1;
            mGamePlayingView.setTextReboundP5(String.valueOf(mIntReboundP5));
            mGamePlayingView.setBtnClickableReboundMinusP5(true);
        } else {
            mGamePlayingView.setBtnClickableReboundPlusP5(false);

        }
    }

    @Override
    public void decreaseReboundP5() {
        if (0 < mIntReboundP5 && mIntReboundP5 < 21) {
            mIntReboundP5 -= 1;
            mGamePlayingView.setTextReboundP5(String.valueOf(mIntReboundP5));
            mGamePlayingView.setBtnClickableReboundPlusP5(true);
        } else {
            mGamePlayingView.setBtnClickableReboundMinusP5(false);
        }
    }

    @Override
    public void increaseFoulP5() {
        if (mIntFoulP5 < 10) {
            mIntFoulP5 += 1;

            mGamePlayingView.setTextFoulP5(String.valueOf(mIntFoulP5));
            mGamePlayingView.setBtnClickableFoulMinusP5(true);
        } else {
            mGamePlayingView.setBtnClickableFoulPlusP5(false);
        }
    }

    @Override
    public void decreaseFoulP5() {
        if (0 < mIntFoulP5 && mIntFoulP5 < 11) {
            mIntFoulP5 -= 1;
            mGamePlayingView.setTextFoulP5(String.valueOf(mIntFoulP5));
            mGamePlayingView.setBtnClickableFoulPlusP5(true);
        } else {
            mGamePlayingView.setBtnClickableFoulMinusP5(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player5 */

    @Override
    public void increaseScoreP6() {
        if (getIntScoreB() == 6) {
            mGamePlayingView.setScorePlusClickableTeamB(false);
        } else if (mIntScoreP6 < 6 && getIntScoreB() < 6) {
            mIntScoreP6 += 1;
            mGamePlayingView.setTextScoreP6(String.valueOf(mIntScoreP6));
            mGamePlayingView.setTextScoreTeamB(String.valueOf(getIntScoreB()));
            mGamePlayingView.setBtnClickableScoreMinusP6(true);
        } else {

        }
    }

    @Override
    public void decreaseScoreP6() {
        if (mIntScoreP6 == 0) {
            mGamePlayingView.setBtnClickableScoreMinusP6(false);
        } else if (0 < getIntScoreB() && getIntScoreB() < 7) {
            mIntScoreP6 -= 1;
            mGamePlayingView.setTextScoreP6(String.valueOf(mIntScoreP6));
            mGamePlayingView.setTextScoreTeamB(String.valueOf(getIntScoreB()));
            mGamePlayingView.setScorePlusClickableTeamB(true);
        } else {
            Log.d("Kerry", "P6 Score error !");
        }
    }

    @Override
    public void increaseReboundP6() {
        if (mIntReboundP6 < 20) {
            mIntReboundP6 += 1;
            mGamePlayingView.setTextReboundP6(String.valueOf(mIntReboundP6));
            mGamePlayingView.setBtnClickableReboundMinusP6(true);
        } else {
            mGamePlayingView.setBtnClickableReboundPlusP6(false);

        }
    }

    @Override
    public void decreaseReboundP6() {
        if (0 < mIntReboundP6 && mIntReboundP6 < 21) {
            mIntReboundP6 -= 1;
            mGamePlayingView.setTextReboundP6(String.valueOf(mIntReboundP6));
            mGamePlayingView.setBtnClickableReboundPlusP6(true);
        } else {
            mGamePlayingView.setBtnClickableReboundMinusP6(false);
        }
    }

    @Override
    public void increaseFoulP6() {
        if (mIntFoulP6 < 10) {
            mIntFoulP6 += 1;

            mGamePlayingView.setTextFoulP6(String.valueOf(mIntFoulP6));
            mGamePlayingView.setBtnClickableFoulMinusP6(true);
        } else {
            mGamePlayingView.setBtnClickableFoulPlusP6(false);
        }
    }

    @Override
    public void decreaseFoulP6() {
        if (0 < mIntFoulP6 && mIntFoulP6 < 11) {
            mIntFoulP6 -= 1;
            mGamePlayingView.setTextFoulP6(String.valueOf(mIntFoulP6));
            mGamePlayingView.setBtnClickableFoulPlusP6(true);
        } else {
            mGamePlayingView.setBtnClickableFoulMinusP6(false);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void setBackKeyDisable(boolean isBackKeyDisable) {

    }

    @Override
    public void setGamingNowMessage(boolean isGamingNow) {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void openGameResultReferee(String hostName) {

    }

    @Override
    public void loadRefereeInfoFromFirebase() {

    }

    @Override
    public void forced2FinishPlayingUi() {

    }

    @Override
    public void forced2FinishGaming() {

    }

}
