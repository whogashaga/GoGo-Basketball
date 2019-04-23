package com.kerry.gogobasketball;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kerry.gogobasketball.create_user.CreateUserContract;
import com.kerry.gogobasketball.create_user.CreateUserPresenter;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.friends.FriendContract;
import com.kerry.gogobasketball.friends.FriendPresenter;
import com.kerry.gogobasketball.home.HomeContract;
import com.kerry.gogobasketball.home.HomePresenter;
import com.kerry.gogobasketball.home.item.Looking4RoomContract;
import com.kerry.gogobasketball.home.item.Looking4RoomFragment;
import com.kerry.gogobasketball.home.item.Looking4RoomPresenter;
import com.kerry.gogobasketball.home.map.CourtsMapContract;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.home.map.CourtsMapPresenter;
import com.kerry.gogobasketball.login.LoginContract;
import com.kerry.gogobasketball.login.LoginPresenter;
import com.kerry.gogobasketball.playing.player.PlayerGoingContract;
import com.kerry.gogobasketball.playing.player.PlayerGoingPresenter;
import com.kerry.gogobasketball.playing.referee.RefereeGoingContract;
import com.kerry.gogobasketball.playing.referee.RefereeGoingPresenter;
import com.kerry.gogobasketball.profile.ProfileContract;
import com.kerry.gogobasketball.profile.ProfilePresenter;
import com.kerry.gogobasketball.rank.RankContract;
import com.kerry.gogobasketball.rank.RankPresenter;
import com.kerry.gogobasketball.rank.player.RankPlayerContract;
import com.kerry.gogobasketball.rank.player.RankPlayerFragment;
import com.kerry.gogobasketball.rank.player.RankPlayerPresenter;
import com.kerry.gogobasketball.rank.referee.RankRefereeContract;
import com.kerry.gogobasketball.rank.referee.RankRefereeFragment;
import com.kerry.gogobasketball.rank.referee.RankRefereePresenter;
import com.kerry.gogobasketball.result.player.PlayerResultContract;
import com.kerry.gogobasketball.result.player.PlayerResultPresenter;
import com.kerry.gogobasketball.result.player.comment.CommentRefereeContract;
import com.kerry.gogobasketball.result.player.comment.CommentRefereePresenter;
import com.kerry.gogobasketball.result.referee.RefereeResultContract;
import com.kerry.gogobasketball.result.referee.RefereeResultPresenter;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.waiting4join.master.Waiting4JoinMasterContract;
import com.kerry.gogobasketball.waiting4join.master.Waiting4JoinMasterPresenter;
import com.kerry.gogobasketball.waiting4join.slave.Waiting4JoinSlaveContract;
import com.kerry.gogobasketball.waiting4join.slave.Waiting4JoinSlavePresenter;
import com.kerry.gogobasketball.want2create.Want2CreateRoomContract;
import com.kerry.gogobasketball.want2create.Want2CreateRoomPresenter;

public class MainPresenter implements MainContract.Presenter, HomeContract.Presenter,
        Looking4RoomContract.Presenter, CourtsMapContract.Presenter, ProfileContract.Presenter,
        FriendContract.Presenter, RankContract.Presenter, Want2CreateRoomContract.Presenter,
        Waiting4JoinMasterContract.Presenter, Waiting4JoinSlaveContract.Presenter,
        RefereeGoingContract.Presenter, PlayerGoingContract.Presenter,
        RefereeResultContract.Presenter, PlayerResultContract.Presenter,
        LoginContract.Presenter, CreateUserContract.Presenter,
        CommentRefereeContract.Presenter, RankRefereeContract.Presenter,
        RankPlayerContract.Presenter {

    private FirebaseFirestore mDb;
    private MainContract.View mMainView;

    private HomePresenter mHomePresenter;
    private FriendPresenter mFriendPresenter;
    private RankPresenter mRankPresenter;
    private ProfilePresenter mProfilePresenter;

    private LoginPresenter mLoginPresenter;
    private CreateUserPresenter mCreateUserPresenter;

    private Looking4RoomPresenter mLooking4RoomPresenter;
    private CourtsMapPresenter mCourtsMapPresenter;

    private RankRefereePresenter mRankRefereePresenter;
    private RankPlayerPresenter mRankPlayerPresenter;

    private Want2CreateRoomPresenter mWant2CreateRoomPresenter;
    private Waiting4JoinMasterPresenter mWaiting4JoinMasterPresenter;
    private Waiting4JoinSlavePresenter mWaiting4JoinSlavePresenter;
    private RefereeGoingPresenter mRefereeGoingPresenter;
    private PlayerGoingPresenter mPlayerGoingPresenter;

    private RefereeResultPresenter mRefereeResultPresenter;
    private PlayerResultPresenter mPlayerResultPresenter;
    private CommentRefereePresenter mCommentRefereePresenter;

    private static boolean mIsBackKeyDisable;
    private static boolean mIsGamingNow;
    private static boolean mAlreadyComment;

//    public MainPresenter(
//            @NonNull StylishRepository stylishRepository,
//            @NonNull MainContract.View mainView) {
//        mStylishRepository = checkNotNull(stylishRepository, "stylishRepository cannot be null!");
//        mMainView = checkNotNull(mainView, "mainView cannot be null!");
//        mMainView.setPresenter(this);
//    }

    public MainPresenter(@NonNull MainContract.View mainView) {
        mMainView = checkNotNull(mainView, "mainView cannot be null!");
        mMainView.setPresenter(this);
    }

    void setHomePresenter(HomePresenter homePresenter) {
        mHomePresenter = checkNotNull(homePresenter);
    }

    void setLoginPresenter(LoginPresenter loginPresenter) {
        mLoginPresenter = checkNotNull(loginPresenter);
    }

    void setFriendPresenter(FriendPresenter friendPresenter) {
        mFriendPresenter = checkNotNull(friendPresenter);
    }

    void setRankPresenter(RankPresenter rankPresenter) {
        mRankPresenter = checkNotNull(rankPresenter);
    }

    void setProfilePresenter(ProfilePresenter profilePresenter) {
        mProfilePresenter = checkNotNull(profilePresenter);
    }

    void setLookingForRoomPresenter(Looking4RoomPresenter looking4RoomPresenter) {
        mLooking4RoomPresenter = checkNotNull(looking4RoomPresenter);
    }

    void setCourtsMapPresenter(CourtsMapPresenter courtsMapPresenter) {
        mCourtsMapPresenter = checkNotNull(courtsMapPresenter);
    }

    void setRankPlayerPresenter(RankPlayerPresenter rankRefereePresenter) {
        mRankPlayerPresenter = checkNotNull(rankRefereePresenter);
    }

    void setRankRefereePresenter(RankRefereePresenter rankRefereePresenter) {
        mRankRefereePresenter = checkNotNull(rankRefereePresenter);
    }

    void setWant2CreateRoomPresenter(Want2CreateRoomPresenter want2CreateRoomPresenter) {
        mWant2CreateRoomPresenter = checkNotNull(want2CreateRoomPresenter);
    }

    void setWaiting4JoinPresenter(Waiting4JoinMasterPresenter waiting4JoinPresenter) {
        mWaiting4JoinMasterPresenter = checkNotNull(waiting4JoinPresenter);
    }

    void setWaiting4JoinSlavePresenter(Waiting4JoinSlavePresenter waiting4JoinSlavePresenter) {
        mWaiting4JoinSlavePresenter = checkNotNull(waiting4JoinSlavePresenter);
    }

    void setRefereeGoingPresenter(RefereeGoingPresenter playingOfRefereePresenter) {
        mRefereeGoingPresenter = checkNotNull(playingOfRefereePresenter);
    }

    void setPlayerGoingPresenter(PlayerGoingPresenter playerGoingPresenter) {
        mPlayerGoingPresenter = checkNotNull(playerGoingPresenter);
    }

    void setRefereeResultPresenter(RefereeResultPresenter refereeResultPresenter) {
        mRefereeResultPresenter = checkNotNull(refereeResultPresenter);
    }

    void setPlayerResultPresenter(PlayerResultPresenter playerResultPresenter) {
        mPlayerResultPresenter = checkNotNull(playerResultPresenter);
    }

    void setCreateUserPresenter(CreateUserPresenter createUserPresenter) {
        mCreateUserPresenter = checkNotNull(createUserPresenter);
    }

    void setCommentRefereePresenter(CommentRefereePresenter commentPresenter) {
        mCommentRefereePresenter = checkNotNull(commentPresenter);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void openHome() {
        mMainView.openHomeUi();
    }

    @Override
    public void openCommentReferee(String refereeName) {
        mMainView.openCommentRefereeUi(refereeName);
    }

    @Override
    public void setBack2LobbyVisible() {
        mPlayerResultPresenter.setBack2LobbyVisible();
    }

    @Override
    public void openFriend() {
        mMainView.openFriendsUi();
    }

    @Override
    public void openRank() {
        mMainView.openRankUi();
    }


    /**
     * Open Profile
     */
    @Override
    public void openProfile() {
        mMainView.openProfileUi();
    }

    @Override
    public void switchToProfileByBottomNavigation() {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {
        mMainView.hideToolbarUi();
        mMainView.hideBottomNavigationUi();
    }

    @Override
    public void showToolbarAndBottomNavigation() {
        mMainView.showToolbarUi();
        mMainView.showBottomNavigationUi();
    }

    @Override
    public void loadProfileUserData() {

    }

    /**
     * Open CreateUserFragment
     */
    @Override
    public void showCreateSuccessDialog() {

    }

    @Override
    public void getUserIniInfoFromLogin(String userFbId) {
        mCreateUserPresenter.getUserIniInfoFromLogin(userFbId);
    }

    @Override
    public void getPositionFromSpinner(String position) {
        mCreateUserPresenter.getPositionFromSpinner(position);
    }

    @Override
    public void onUserIdEditTextChange(CharSequence charSequence) {
        mCreateUserPresenter.onUserIdEditTextChange(charSequence);
    }

    @Override
    public void getGenderFromRadioGroup(String gender) {
        mCreateUserPresenter.getGenderFromRadioGroup(gender);
    }

    @Override
    public void createUserClickConfirm() {
        mCreateUserPresenter.createUserClickConfirm();
    }

    @Override
    public void onCreateUserSuccess() {
        mMainView.openHomeUi();
    }

    /**
     * Open Wait4Join
     */

    @Override
    public void loadRefereeInfoFromFirebase() {

    }

    /**
     * Open Gaming Referee
     */
    @Override
    public void getHostNameFromWaitingJoin(String hostName) {
        mRefereeGoingPresenter.getHostNameFromWaitingJoin(hostName);
    }

    @Override
    public void getGamingRoomFromFireStore(String hostName) {
        mRefereeGoingPresenter.getGamingRoomFromFireStore(hostName);
    }

    @Override
    public void loadPlayersInfoFromFirebase() {
        mRefereeGoingPresenter.loadRefereeInfoFromFirebase();
    }

    /**
     * Open GameResult
     */

    @Override
    public void getHostNameFromRefereeGoing(String hostName) {
        mRefereeResultPresenter.getHostNameFromRefereeGoing(hostName);
    }

    @Override
    public void getRoomInfoFromFireStore(String hostName) {
        mRefereeResultPresenter.getRoomInfoFromFireStore(hostName);
    }

    @Override
    public void forced2FinishPlayingUi() {

    }

    @Override
    public void openGameResultPlayer(String hostName, int nowSort) {
        mMainView.openPlayerResultUi(hostName, nowSort);
    }

    @Override
    public void openGameResultReferee(String hostName) {
        mMainView.openRefereeResultUi(hostName);
    }

    @Override
    public void getRefereeUserData(Activity activity) {
        mRefereeGoingPresenter.getRefereeUserData(activity);
    }

    @Override
    public void updateGameResultOfPlayer(GamingRoomInfo gamingRoomInfo) {
        mRefereeGoingPresenter.updateGameResultOfPlayer(gamingRoomInfo);
    }

    @Override
    public void checkWhichTeamWon() {
        mRefereeGoingPresenter.checkWhichTeamWon();
    }

    @Override
    public void forced2FinishGaming() {

    }

    @Override
    public void finishResultResultUi() {

    }

    /**
     * open CommentReferee
     */

    @Override
    public void showSendCommentSuccessDialog() {
        mMainView.showMessageDialogUi(MessageDialog.SEND_COMMENT_SUCCESS);
    }

    @Override
    public void getRefereeNameFromResult(String hostName) {
        mCommentRefereePresenter.getRefereeNameFromResult(hostName);
    }

    @Override
    public void onWheelViewChanged(int rating) {
        mCommentRefereePresenter.onWheelViewChanged(rating);
    }

    @Override
    public void queryRefereeUserDocId() {
        mCommentRefereePresenter.queryRefereeUserDocId();
    }

    @Override
    public void showBack2LobbyButtonPlayerResult() {
        mPlayerResultPresenter.setBack2LobbyVisible();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Player Result */

    @Override
    public void getHostNameFromPlayerGoing(String hostName, int nowSort) {
        mPlayerResultPresenter.getHostNameFromPlayerGoing(hostName, nowSort);
    }

    @Override
    public void getRoomInfoFromFireStorePlayer(String hostName, int nowSort) {
        mPlayerResultPresenter.getRoomInfoFromFireStorePlayer(hostName, nowSort);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* adjust player's records - PlayingFragment */

    @Override
    public void increaseScoreP1() {
        mRefereeGoingPresenter.increaseScoreP1();
    }

    @Override
    public void decreaseScoreP1() {
        mRefereeGoingPresenter.decreaseScoreP1();
    }

    @Override
    public void increaseReboundP1() {
        mRefereeGoingPresenter.increaseReboundP1();
    }

    @Override
    public void decreaseReboundP1() {
        mRefereeGoingPresenter.decreaseReboundP1();
    }

    @Override
    public void increaseFoulP1() {
        mRefereeGoingPresenter.increaseFoulP1();
    }

    @Override
    public void decreaseFoulP1() {
        mRefereeGoingPresenter.decreaseFoulP1();
    }

    @Override
    public void increaseScoreP2() {
        mRefereeGoingPresenter.increaseScoreP2();
    }

    @Override
    public void decreaseScoreP2() {
        mRefereeGoingPresenter.decreaseScoreP2();
    }

    @Override
    public void increaseReboundP2() {
        mRefereeGoingPresenter.increaseReboundP2();
    }

    @Override
    public void decreaseReboundP2() {
        mRefereeGoingPresenter.decreaseReboundP2();
    }

    @Override
    public void increaseFoulP2() {
        mRefereeGoingPresenter.increaseFoulP2();
    }

    @Override
    public void decreaseFoulP2() {
        mRefereeGoingPresenter.decreaseFoulP2();
    }

    @Override
    public void increaseScoreP3() {
        mRefereeGoingPresenter.increaseScoreP3();
    }

    @Override
    public void decreaseScoreP3() {
        mRefereeGoingPresenter.decreaseScoreP3();
    }

    @Override
    public void increaseReboundP3() {
        mRefereeGoingPresenter.increaseReboundP3();
    }

    @Override
    public void decreaseReboundP3() {
        mRefereeGoingPresenter.decreaseReboundP3();
    }

    @Override
    public void increaseFoulP3() {
        mRefereeGoingPresenter.increaseFoulP3();
    }

    @Override
    public void decreaseFoulP3() {
        mRefereeGoingPresenter.decreaseFoulP3();
    }

    @Override
    public void increaseScoreP4() {
        mRefereeGoingPresenter.increaseScoreP4();
    }

    @Override
    public void decreaseScoreP4() {
        mRefereeGoingPresenter.decreaseScoreP4();
    }

    @Override
    public void increaseReboundP4() {
        mRefereeGoingPresenter.increaseReboundP4();
    }

    @Override
    public void decreaseReboundP4() {
        mRefereeGoingPresenter.decreaseReboundP4();
    }

    @Override
    public void increaseFoulP4() {
        mRefereeGoingPresenter.increaseFoulP4();
    }

    @Override
    public void decreaseFoulP4() {
        mRefereeGoingPresenter.decreaseFoulP4();
    }

    @Override
    public void increaseScoreP5() {
        mRefereeGoingPresenter.increaseScoreP5();
    }

    @Override
    public void decreaseScoreP5() {
        mRefereeGoingPresenter.decreaseScoreP5();
    }

    @Override
    public void increaseReboundP5() {
        mRefereeGoingPresenter.increaseReboundP5();
    }

    @Override
    public void decreaseReboundP5() {
        mRefereeGoingPresenter.decreaseReboundP5();
    }

    @Override
    public void increaseFoulP5() {
        mRefereeGoingPresenter.increaseFoulP5();
    }

    @Override
    public void decreaseFoulP5() {
        mRefereeGoingPresenter.decreaseFoulP5();
    }

    @Override
    public void increaseScoreP6() {
        mRefereeGoingPresenter.increaseScoreP6();
    }

    @Override
    public void decreaseScoreP6() {
        mRefereeGoingPresenter.decreaseScoreP6();
    }

    @Override
    public void increaseReboundP6() {
        mRefereeGoingPresenter.increaseReboundP6();
    }

    @Override
    public void decreaseReboundP6() {
        mRefereeGoingPresenter.decreaseReboundP6();
    }

    @Override
    public void increaseFoulP6() {
        mRefereeGoingPresenter.increaseFoulP6();
    }

    @Override
    public void decreaseFoulP6() {
        mRefereeGoingPresenter.decreaseFoulP6();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* General in both master & slave */

    @Override
    public void openGamePlayingOfReferee(String hostName) {
        mMainView.openGamePlayingOfRefereeUi(hostName);
    }

    @Override
    public void openGamePlayingOfPlayer(String hostName, int nowSort) {
        mMainView.openGamePlayingOfPlayerUi(hostName, nowSort);
    }

    @Override
    public void finishWaiting4JoinUi() {
        mMainView.showActivityBackgroundWhenPortrait();
        mMainView.popBackStackUi();
        mWant2CreateRoomPresenter.setCreateBtnClickable();
    }

    @Override
    public void finishWaiting4JoinSlaveUi() {
        mMainView.showActivityBackgroundWhenPortrait();
        mMainView.popBackStackUi();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Waiting Slave */


    @Override
    public void openWaiting4JoinSlave(WaitingRoomInfo waitingRoomInfo) {
        mMainView.openWaiting4JoinSlaveUi(waitingRoomInfo);
    }

    @Override
    public void getProfileUserDataSlave(Activity activity) {
        mWaiting4JoinSlavePresenter.getProfileUserDataSlave(activity);
    }

    @Override
    public void removeListenerSlave() {
        mWaiting4JoinSlavePresenter.removeListenerSlave();
    }

    @Override
    public void getHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo) {
        mWaiting4JoinSlavePresenter.getHostNameFromLooking4Room(waitingRoomInfo);
    }

    @Override
    public void deleteSeatsInfoWhenLeaveRoom() {
        mWaiting4JoinSlavePresenter.deleteSeatsInfoWhenLeaveRoom();
    }

    @Override
    public void changeSlave2NewSeat(int newSort) {
        mWaiting4JoinSlavePresenter.changeSlave2NewSeat(newSort);
    }

    @Override
    public void checkTotalPlayerAmountSlave() {

    }

    @Override
    public void deleteRoomDocSlave() {

    }

    /* ------------------------------------------------------------------------------------------ */
    /* Waiting Master */

    @Override
    public void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {
        mWaiting4JoinMasterPresenter.getRoomInfoFromWant2Create(waitingRoomInfo, hostSeatInfo, roomDocId);
    }

    @Override
    public void openWaitingJoin(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {
        mMainView.openWait4JoinUi(waitingRoomInfo, hostSeatInfo, roomDocId);
    }

    @Override
    public void changeMaster2NewSeat(int newSort) {
        mWaiting4JoinMasterPresenter.changeMaster2NewSeat(newSort);
    }

    @Override
    public void deleteHostInfoWhenLeave() {
        mWaiting4JoinMasterPresenter.deleteHostInfoWhenLeave();
    }

    @Override
    public void updateRoomStatus2Gaming(GamingRoomInfo gamingRoomInfo) {
        mWaiting4JoinMasterPresenter.updateRoomStatus2Gaming(gamingRoomInfo);
    }

    @Override
    public void initializeGamingRoomInfo() {
        mWaiting4JoinMasterPresenter.initializeGamingRoomInfo();
    }

    /**
     * Open Want2CreateRoom
     */

    @Override
    public void openWant2CreateRoom() {
        mMainView.openWant2CreateRoomUi();
    }

    @Override
    public void finishWant2CreateRoomUi() {
        mMainView.popBackStackUi();
    }

    @Override
    public void onRoomNameEditTextChange(CharSequence charSequence) {
        mWant2CreateRoomPresenter.onRoomNameEditTextChange(charSequence);
    }



    @Override
    public void getHostNameFromWaitingJoinSlave(String hostName, int nowSort) {
        mPlayerGoingPresenter.getHostNameFromWaitingJoinSlave(hostName, nowSort);
    }

    @Override
    public void updateRoomInfo2FireStore() {
        mWant2CreateRoomPresenter.updateRoomInfo2FireStore();
    }

    @Override
    public void updateUserInfo2FireBase(WaitingRoomSeats hostPlayer, String roomDocId) {
        mWant2CreateRoomPresenter.updateUserInfo2FireBase(hostPlayer, roomDocId);
    }

    @Override
    public void getCourtLocationFromSpinner(String courtLocation) {
        mWant2CreateRoomPresenter.getCourtLocationFromSpinner(courtLocation);
    }

    @Override
    public void getRefereeOnOffFromRadioGroup(boolean justice) {
        mWant2CreateRoomPresenter.getRefereeOnOffFromRadioGroup(justice);
    }

    @Override
    public void loadProfileUserDataWant2Create(Activity activity) {
        mWant2CreateRoomPresenter.loadProfileUserDataWant2Create(activity);
    }

    @Override
    public void setCreateBtnClickable() {
        mWant2CreateRoomPresenter.setCreateBtnClickable();
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void setActivityBackgroundLandScape() {
        mMainView.showActivityBackgroundWhenLandScape();
    }

    @Override
    public void setActivityBackgroundPortrait() {
        mMainView.showActivityBackgroundWhenPortrait();
    }

    @Override
    public void removeListenerMaster() {
        mWaiting4JoinMasterPresenter.removeListenerMaster();
    }

    @Override
    public void deleteGamingRoom() {
        mRefereeResultPresenter.deleteGamingRoom();
    }

    @Override
    public void hideBottomNavigation() {

    }

    @Override
    public void showBottomNavigation() {

    }

    @Override
    public void updateFriendBadge() {

    }

    @Override
    public void updateToolbar(String title) {
        mMainView.setToolbarTitleUi(title);
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void showLoginFragment() {
        mMainView.openLoginUi();
    }

    @Override
    public void showCheckOutSuccessDialog() {

    }

    @Override
    public void showRatingRefereeSuccessDialog() {

    }

    @Override
    public void showLoginSuccessDialog() {
        mMainView.showMessageDialogUi(MessageDialog.LOGIN_SUCCESS);
    }

    @Override
    public void showCreateUserSuccessDialog() {
        mMainView.showMessageDialogUi(MessageDialog.CREATE_USER_SUCCESS);
    }

    // from login fragment
    @Override
    public void loginFbOnClick(Activity activity) {
        mLoginPresenter.loginFbOnClick(activity);
    }

    @Override
    public void onLoginSuccess(User user) {
//        updateUser2FireStore(user);
        checkIfUserCreatedAfterLogin(user);
    }

    private void checkIfUserCreatedAfterLogin(User user) {

        DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(user.getFacebookId());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(Constants.TAG, "DocumentSnapshot data: " + document.getData());
                        User userInfo = document.toObject(User.class);
                        if (userInfo.getId().equals("")) {
                            mMainView.openCreateUserUi(user.getFacebookId());
                        } else {
                            mMainView.openHomeUi();
                        }
                    } else {
                        updateUser2FireStore(user);
                        Log.d(Constants.TAG, "No such document");
                    }
                } else {
                    Log.d(Constants.TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void updateUser2FireStore(User user) {
        FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(user.getFacebookId())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "User登入後資料上傳!");
                        mMainView.openCreateUserUi(user.getFacebookId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(Constants.TAG, "Error adding document", e);
            }
        });
    }

    // from MainActivity because already login but haven't create user data
    @Override
    public void onLoginSuccessBeforeOpenApp(String userFbDocId) {
        checkIfUserCreated(userFbDocId);
    }


    private void checkIfUserCreated(String userDocId) {

        Log.d(Constants.TAG, "checkIfUserCreate doc id = " + userDocId);

        DocumentReference docRef = FirestoreHelper.getFirestore()
                .collection(Constants.USERS)
                .document(userDocId);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(Constants.TAG, "checkIfUserCreated DocumentSnapshot data: " + document.getData());
                        User userInfo = document.toObject(User.class);
                        if (userInfo.getId().equals("")) {
                            mMainView.openCreateUserUi(userDocId);
                        } else {
                            mMainView.openHomeUi();
                        }
                    } else {

                        Log.d(Constants.TAG, "No such document");
                    }
                } else {
                    Log.d(Constants.TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    @Override
    public void showToast(String message) {


    }

    @Override
    public void onDrawerOpened() {

    }

    @Override
    public void onClickDrawerAvatar() {

    }

    @Override
    public void start() {

    }

    /* ------------------------------------------------------------------------------------------ */
    /* Profile Presenter Use Only */

    @Override
    public void loadProfileUserData(Activity activity) {
        mProfilePresenter.loadProfileUserData(activity);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Home View Pager Use Only */

    @Override
    public RankPlayerFragment findPlayerRankView() {
        return mMainView.findRankPlayerView();
    }

    @Override
    public RankRefereeFragment findRefereeRankView() {
        return mMainView.findRankRefereeView();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Home View Pager Use Only */

    @Override
    public Looking4RoomFragment findRoomsView() {
        return mMainView.findHomeView();
    }

    @Override
    public CourtsMapFragment findMapView() {
        return mMainView.findMapView();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* LookingForRoom Presenter */

    @Override
    public void loadExistedRoomsData4RecyclerView() {
        mLooking4RoomPresenter.loadExistedRoomsData4RecyclerView();
    }


    @Override
    public void setRoomListSnapshotListerSlave() {
        mLooking4RoomPresenter.setRoomListSnapshotListerSlave();
    }

    @Override
    public void setExistedRoomsData() {
        mLooking4RoomPresenter.setExistedRoomsData();
    }

    @Override
    public boolean isHomeItemHasNextPaging(String itemType) {
        return false;
    }

    @Override
    public void onHomeItemScrollToBottom(String itemType) {

    }

    /* ------------------------------------------------------------------------------------------ */
    /* LookingForRoom Presenter */

    @Override
    public void loadRankPlayerByWinning() {
        mRankPlayerPresenter.loadRankPlayerByWinning();
    }

    @Override
    public void loadRankPlayerByScore() {
        mRankPlayerPresenter.loadRankPlayerByScore();
    }

    @Override
    public void loadRankPlayerByRebound() {
        mRankPlayerPresenter.loadRankPlayerByRebound();
    }

    @Override
    public void loadRankPlayerByFoul() {
        mRankPlayerPresenter.loadRankPlayerByFoul();
    }

    @Override
    public void loadRankPlayerByGames() {
        mRankPlayerPresenter.loadRankPlayerByGames();
    }

    @Override
    public void loadRankRefereeByJustices() {
        mRankRefereePresenter.loadRankRefereeByJustices();
    }

    @Override
    public void loadRankRefereeByRating() {
        mRankRefereePresenter.loadRankRefereeByRating();
    }

    /* ------------------------------------------------------------------------------------------ */
    /*  General Function */

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
    public void setBackKeyDisable(boolean isBackKeyDisable) {
        mIsBackKeyDisable = isBackKeyDisable;
    }

    @Override
    public void setGamingNowMessage(boolean isGamingNow) {
        mIsGamingNow = isGamingNow;
    }

    @Override
    public void setHave2Comment(boolean have2) {
        mAlreadyComment = have2;
    }

    @Override
    public boolean disableBackKey() {
        return mIsBackKeyDisable;
    }

    @Override
    public boolean isGamingNow() {
        return mIsGamingNow;
    }

    @Override
    public boolean have2Comment() {
        return mAlreadyComment;
    }
}
