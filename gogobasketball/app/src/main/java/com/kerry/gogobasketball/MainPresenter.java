package com.kerry.gogobasketball;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kerry.gogobasketball.create1user.CreateUserContract;
import com.kerry.gogobasketball.create1user.CreateUserPresenter;
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
import com.kerry.gogobasketball.home.item.filter.CourtsFilterContract;
import com.kerry.gogobasketball.home.item.filter.CourtsFilterPresenter;
import com.kerry.gogobasketball.home.item.find1host.FindHostContract;
import com.kerry.gogobasketball.home.item.find1host.FindHostPresenter;
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
import com.kerry.gogobasketball.profile.gender.ChangeGenderContract;
import com.kerry.gogobasketball.profile.gender.ChangeGenderPresenter;
import com.kerry.gogobasketball.profile.id.ChangeIdContract;
import com.kerry.gogobasketball.profile.id.ChangeIdPresenter;
import com.kerry.gogobasketball.profile.logout.LogoutContract;
import com.kerry.gogobasketball.profile.logout.LogoutPresenter;
import com.kerry.gogobasketball.profile.position.ChangePositionContract;
import com.kerry.gogobasketball.profile.position.ChangePositionPresenter;
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
import com.kerry.gogobasketball.waiting4join.detail.UserDetailContract;
import com.kerry.gogobasketball.waiting4join.detail.UserDetailPresenter;
import com.kerry.gogobasketball.waiting4join.instruction.InstructionContract;
import com.kerry.gogobasketball.waiting4join.instruction.InstructionPresenter;
import com.kerry.gogobasketball.waiting4join.master.Waiting4JoinMasterContract;
import com.kerry.gogobasketball.waiting4join.master.Waiting4JoinMasterPresenter;
import com.kerry.gogobasketball.waiting4join.slave.Waiting4JoinSlaveContract;
import com.kerry.gogobasketball.waiting4join.slave.Waiting4JoinSlavePresenter;
import com.kerry.gogobasketball.want2create.Want2CreateRoomContract;
import com.kerry.gogobasketball.want2create.Want2CreateRoomPresenter;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter, HomeContract.Presenter,
        Looking4RoomContract.Presenter, CourtsMapContract.Presenter, ProfileContract.Presenter,
        FriendContract.Presenter, RankContract.Presenter, Want2CreateRoomContract.Presenter,
        Waiting4JoinMasterContract.Presenter, Waiting4JoinSlaveContract.Presenter,
        RefereeGoingContract.Presenter, PlayerGoingContract.Presenter,
        RefereeResultContract.Presenter, PlayerResultContract.Presenter,
        LoginContract.Presenter, CreateUserContract.Presenter,
        CommentRefereeContract.Presenter, RankRefereeContract.Presenter,
        RankPlayerContract.Presenter, LogoutContract.Presenter, ChangeIdContract.Presenter,
        ChangePositionContract.Presenter, ChangeGenderContract.Presenter,
        FindHostContract.Presenter, InstructionContract.Presenter,
        UserDetailContract.Presenter, CourtsFilterContract.Presenter {

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

    private LogoutPresenter mLogoutPresenter;
    private ChangeIdPresenter mChangeIdPresenter;
    private ChangePositionPresenter mChangePositionPresenter;
    private ChangeGenderPresenter mChangeGenderPresenter;
    private FindHostPresenter mFindHostPresenter;
    private CourtsFilterPresenter mCourtsFilterPresenter;
    private InstructionPresenter mInstructionPresenter;
    private UserDetailPresenter mUserDetailPresenter;

    private boolean mIsBackKeyDisable;
    private boolean mIsGamingNow;
    private boolean mAlreadyComment;
    private boolean mIsCreatingRoomNow;
    private User mUser;
    private String mCourtsLocation;
    private Handler mHandler;
    private Runnable mRunnable;

    public MainPresenter(@NonNull MainContract.View mainView) {
        mMainView = checkNotNull(mainView, "mainView cannot be null!");
        mMainView.setPresenter(this);
        mUser = new User();
        mHandler = new Handler();
        mCourtsLocation = "";
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

    void setUserDetailPresenter(UserDetailPresenter userDetailPresenter) {
        mUserDetailPresenter = checkNotNull(userDetailPresenter);
    }

    void setLogoutPresenter(LogoutPresenter logoutPresenter) {
        mLogoutPresenter = checkNotNull(logoutPresenter);
    }

    void setChangeIdPresenter(ChangeIdPresenter changeIdPresenter) {
        mChangeIdPresenter = checkNotNull(changeIdPresenter);
    }

    void setChangePositionPresenter(ChangePositionPresenter changePositionPresenter) {
        mChangePositionPresenter = checkNotNull(changePositionPresenter);
    }

    void setChangeGenderPresenter(ChangeGenderPresenter changeGenderPresenter) {
        mChangeGenderPresenter = checkNotNull(changeGenderPresenter);
    }

    void setFindHostPresenter(FindHostPresenter findHostPresenter) {
        mFindHostPresenter = checkNotNull(findHostPresenter);
    }

    void setCourtsFilterPresenter(CourtsFilterPresenter courtsFilterPresenter) {
        mCourtsFilterPresenter = checkNotNull(courtsFilterPresenter);
    }

    void setInstructionPresenter(InstructionPresenter instructionPresenter) {
        mInstructionPresenter = checkNotNull(instructionPresenter);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void getCurrentCourtPopulation() {
        mCourtsMapPresenter.getCurrentCourtPopulation();
    }

    @Override
    public void setOnPopulationChangeListener() {
        mCourtsMapPresenter.setOnPopulationChangeListener();
    }

    @Override
    public void removeListener() {
        mCourtsMapPresenter.removeListener();
    }

    @Override
    public void getLocationPermission(Activity activity) {
        if (mCourtsMapPresenter != null) {
            mCourtsMapPresenter.getLocationPermission(activity);
        } else {
            Log.d(Constants.TAG, "getLocationPermission Fail : mCourtsMapPresenter is null !");
        }
    }

    @Override
    public void logoutFacebook() {
        mLogoutPresenter.logoutFacebook();
    }

    @Override
    public void openHome() {
        mMainView.openHomeUi();
        Log.d(Constants.TAG, "openHome: openHomeUi ");
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

    /**
     * Switch to Profile by BottomNavigation.setSelectedItemId.
     */
    @Override
    public void switchToProfileByBottomNavigation() {
        mMainView.switchProfileUiInitiative();
    }

    @Override
    public void switchToHotsByBottomNavigation() {
        mMainView.switchHotsUiInitiative();
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

    /**
     * Open CreateUserFragment
     */

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
        Log.d(Constants.TAG, "onCreateUserSuccess: openHomeUi");
        mMainView.openHomeUi();
    }

    @Override
    public void checkIfUserIdExists() {
        mCreateUserPresenter.checkIfUserIdExists();
    }

    @Override
    public void finishCreateUser() {
        mMainView.popBackStackUi();
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
    public void forced2FinishGaming() {
        mRefereeGoingPresenter.forced2FinishGaming();
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
    public void removeListenerPlayer() {
        mPlayerGoingPresenter.removeListenerPlayer();
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
    public void loadJoinerUserData() {
        mWaiting4JoinSlavePresenter.loadJoinerUserData();
    }

    @Override
    public void removeListenerSlave() {
        mWaiting4JoinSlavePresenter.removeListenerSlave();
    }

    @Override
    public void setHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo) {
        mWaiting4JoinSlavePresenter.setHostNameFromLooking4Room(waitingRoomInfo);
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
    public void setRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {
        mWaiting4JoinMasterPresenter.setRoomInfoFromWant2Create(waitingRoomInfo, hostSeatInfo, roomDocId);
    }

    @Override
    public void openWaitingJoinMaster(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {
        mMainView.openWait4JoinUi(waitingRoomInfo, hostSeatInfo, roomDocId);
    }

    @Override
    public void changeMaster2NewSeat(int newSort) {
        mWaiting4JoinMasterPresenter.changeMaster2NewSeat(newSort);
    }

    @Override
    public void deleteHostSeatWhenLeave() {
        mWaiting4JoinMasterPresenter.deleteHostSeatWhenLeave();
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
    public void setRoomInfo() {
        mWant2CreateRoomPresenter.setRoomInfo();
    }

    @Override
    public void updateHostSeat2Cloud(WaitingRoomSeats hostPlayer, String roomDocId) {
        mWant2CreateRoomPresenter.updateHostSeat2Cloud(hostPlayer, roomDocId);
    }

    @Override
    public void getSpinnerCourtLocation(String courtLocation) {
        mWant2CreateRoomPresenter.getSpinnerCourtLocation(courtLocation);
    }

    @Override
    public void getRadioRefereeMode(boolean justice) {
        mWant2CreateRoomPresenter.getRadioRefereeMode(justice);
    }

    @Override
    public void loadHostUserData() {
        mWant2CreateRoomPresenter.loadHostUserData();
    }

    @Override
    public void setCreateBtnClickable() {
        mWant2CreateRoomPresenter.setCreateBtnClickable();
    }

    @Override
    public void getCourtsListFromCloud() {
        mWant2CreateRoomPresenter.getCourtsListFromCloud();
    }

    /* ------------------------------------------------------------------------------------------ */
    /*  Change Profile Data Dialog */

    @Override
    public void getPositionFromWheel(String position) {
        mChangePositionPresenter.getPositionFromWheel(position);
    }

    @Override
    public void compareNewOldPosition(Activity activity) {
        mChangePositionPresenter.compareNewOldPosition(activity);
    }

    @Override
    public void getGenderFromRadios(String gender) {
        mChangeGenderPresenter.getGenderFromRadios(gender);
    }

    @Override
    public void compareOldNewGender(Activity activity) {
        mChangeGenderPresenter.compareOldNewGender(activity);
    }

    /* ------------------------------------------------------------------------------------------ */
    /*  Find Host by ID Dialog */

    @Override
    public void checkIfRoomExists(Activity activity) {
        mFindHostPresenter.checkIfRoomExists(activity);
    }

    @Override
    public void onHostIdEditTextChange(CharSequence charSequence) {
        mFindHostPresenter.onHostIdEditTextChange(charSequence);
    }

    @Override
    public void updateRecyclerView(Activity activity) {
        mFindHostPresenter.updateRecyclerView(activity);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* CourtsFilter Presenter */

    @Override
    public void getLocationFromWheel(String position) {
        mCourtsFilterPresenter.getLocationFromWheel(position);
    }

    @Override
    public void getCourtsListFromFirebase() {
        mCourtsFilterPresenter.getCourtsListFromFirebase();
    }

    @Override
    public void queryCourts() {
        mCourtsFilterPresenter.queryCourts();
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
    public void updateToolbar(String title) {
        mMainView.setToolbarTitleUi(title);
    }

    @Override
    public void showLoginFragment() {
        mMainView.openLoginUi();
    }

    @Override
    public void showLogoutSuccessDialog() {
        mMainView.showMessageDialogUi(MessageDialog.LOGOUT_SUCCESS);
        hideToolbarAndBottomNavigation();
//        getUserInfoWhenGetOutOfApp();
        mMainView.stopGettingLocationService();
    }

    @Override
    public void showDataChangeSuccessDialog() {
        mMainView.showMessageDialogUi(MessageDialog.DATA_CHANGE_SUCCESS);
    }


    @Override
    public void showLoginSuccessDialog() {
        mMainView.showMessageDialogUi(MessageDialog.LOGIN_SUCCESS);
    }

    @Override
    public void showCreateUserSuccessDialog() {
        mMainView.showMessageDialogUi(MessageDialog.CREATE_USER_SUCCESS);
    }

    @Override
    public void getLocationPermissionWhenLogin(Activity activity) {
        mLoginPresenter.getLocationPermissionWhenLogin(activity);
    }

    // from login fragment
    @Override
    public void loginFbOnClick(Activity activity) {
        mLoginPresenter.loginFbOnClick(activity);
    }

    @Override
    public void onLoginSuccess(User user) {
        checkIfUserCreatedAfterLogin(user);
    }

    private void checkIfUserCreatedAfterLogin(User user) {

        DocumentReference docRef = FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .document(user.getFacebookId());

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d(Constants.TAG, "DocumentSnapshot data: " + document.getData());
                    User userInfo = document.toObject(User.class);
                    if (userInfo.getId().equals("")) {
                        mMainView.openCreateUserUi(user.getFacebookId());
                    } else {
                        showToolbarAndBottomNavigation();
                        switchToHotsByBottomNavigation();
                        Log.d(Constants.TAG, "checkIfUserCreatedAfterLogin: openHomeUi");
                        mMainView.openHomeByBackStack();
//                        mMainView.openHomeUi();
                    }
                } else {
                    updateUser2FireStore(user);
                    Log.d(Constants.TAG, "No such document");
                }
            } else {
                Log.d(Constants.TAG, "get failed with ", task.getException());
            }
        });
    }

    private void updateUser2FireStore(User user) {
        FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .document(user.getFacebookId())
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Log.d(Constants.TAG, "User登入後資料上傳!");
                    mMainView.openCreateUserUi(user.getFacebookId());
                }).addOnFailureListener(e -> Log.w(Constants.TAG, "Error adding document", e));
    }

    // from MainActivity because already login but haven't create user data
    @Override
    public void onLoginSuccessBeforeOpenApp(String userFbDocId) {
        checkIfUserCreated(userFbDocId);
    }

    private void checkIfUserCreated(String userDocId) {

        Log.d(Constants.TAG, "checkIfUserCreate doc id = " + userDocId);

        DocumentReference docRef = FirebaseFirestore.getInstance()
                .collection(Constants.USERS)
                .document(userDocId);

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d(Constants.TAG, "checkIfUserCreated DocumentSnapshot data: " + document.getData());
                    User userInfo = document.toObject(User.class);
                    if (userInfo.getId().equals("")) {
                        mMainView.openCreateUserUi(userDocId);
                    } else {
                        Log.d(Constants.TAG, "checkIfUserCreated: openHomeUi");
                        mMainView.openHomeUi();
                    }
                } else {

                    Log.d(Constants.TAG, "No such document");
                }
            } else {
                Log.d(Constants.TAG, "get failed with ", task.getException());
            }
        });
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

    @Override
    public void getNowGenderFromProfile(String currentGender) {
        mChangeGenderPresenter.getNowGenderFromProfile(currentGender);
    }

    @Override
    public void openLogoutDialog() {
        mMainView.openLogOutUi();
    }

    @Override
    public void openChangeIdDialog() {
        mMainView.openChangeIdUi();
    }

    @Override
    public void openChangeGender(String currentGender) {
        mMainView.openChangeGenderUi(currentGender);
    }

    @Override
    public void getNowPositionFromProfile(String currentPosition) {
        mChangePositionPresenter.getNowPositionFromProfile(currentPosition);
    }

    @Override
    public void openChangePosition(String currentPosition) {
        mMainView.openChangePositionUi(currentPosition);
    }

    @Override
    public void openFindHostDialog() {
        mMainView.openFindHostUi();
    }

    @Override
    public void openCourtsFilterDialog() {
        mMainView.openCourtsFilterUi();
    }

    @Override
    public void openInstructionDialog() {
        mMainView.openInstructionUi();
    }

    @Override
    public void openUserDetailSlave(int sort) {
        mWaiting4JoinSlavePresenter.openUserDetailSlave(sort);
    }

    @Override
    public void openUserDetailDialog(String userId) {
        mMainView.openUserDetailUi(userId);
    }

    @Override
    public void openUserDetailMaster(int sort) {
        mWaiting4JoinMasterPresenter.openUserDetailMaster(sort);
    }

    @Override
    public void kickOutPlayer(int sort) {
        mWaiting4JoinMasterPresenter.kickOutPlayer(sort);
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
    public void updateLooking4RoomView(ArrayList<WaitingRoomInfo> list) {
        mLooking4RoomPresenter.updateLooking4RoomView(list);
    }

    @Override
    public void setExistedRoomsData() {
        mLooking4RoomPresenter.setExistedRoomsData();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* LookingForRoom Presenter */

    @Override
    public void loadRankPlayerByWinRate() {
        mRankPlayerPresenter.loadRankPlayerByWinRate();
    }

    @Override
    public void loadRankPlayerByAvScore() {
        mRankPlayerPresenter.loadRankPlayerByAvScore();
    }

    @Override
    public void loadRankPlayerByAvRebound() {
        mRankPlayerPresenter.loadRankPlayerByAvRebound();
    }

    @Override
    public void loadRankPlayerByAvFoul() {
        mRankPlayerPresenter.loadRankPlayerByAvFoul();
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
    public void openProgressDialog() {
        mMainView.openProgressDialogUi();
    }

    @Override
    public void closeProgressDialog() {
        mMainView.closeProgressDialogUi();
    }

    @Override
    public void getUserIdFromWaiting(String userId) {
        mUserDetailPresenter.getUserIdFromWaiting(userId);
    }

    @Override
    public void queryUserInfoDocId() {
        mUserDetailPresenter.queryUserInfoDocId();
    }

    /* ------------------------------------------------------------------------------------------ */
    /*  change ID Dialog */

    @Override
    public void onUserNewIdEditTextChange(CharSequence charSequence) {
        mChangeIdPresenter.onUserNewIdEditTextChange(charSequence);
    }

    @Override
    public void checkIfUserNewIdExists(Activity activity) {
        mChangeIdPresenter.checkIfUserNewIdExists(activity);

    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void setBackKeyDisable(boolean isBackKeyDisable) {
        mIsBackKeyDisable = isBackKeyDisable;
    }

    @Override
    public void setGamingNowMessage(boolean isGamingNow) {
        mIsGamingNow = isGamingNow;
    }

    @Override
    public void setWant2CreateNow(boolean isCreatingNow) {
        mIsCreatingRoomNow = isCreatingNow;
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

    @Override
    public boolean openingWant2CreateRoom() {
        return mIsCreatingRoomNow;
    }



}
