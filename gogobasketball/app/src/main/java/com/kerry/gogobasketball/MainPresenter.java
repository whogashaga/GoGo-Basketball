package com.kerry.gogobasketball;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.kerry.gogobasketball.data.GamingRoomInfo;
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
import com.kerry.gogobasketball.playing.player.PlayerGoingContract;
import com.kerry.gogobasketball.playing.player.PlayerGoingPresenter;
import com.kerry.gogobasketball.playing.referee.RefereeGoingContract;
import com.kerry.gogobasketball.playing.referee.RefereeGoingPresenter;
import com.kerry.gogobasketball.profile.ProfileContract;
import com.kerry.gogobasketball.profile.ProfilePresenter;
import com.kerry.gogobasketball.rank.RankContract;
import com.kerry.gogobasketball.rank.RankPresenter;
import com.kerry.gogobasketball.result.referee.RefereeResultContract;
import com.kerry.gogobasketball.result.referee.RefereeResultPresenter;
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
        RefereeGoingContract.Presenter, PlayerGoingContract.Presenter, RefereeResultContract.Presenter {

    private FirebaseFirestore mDb;
    private MainContract.View mMainView;

    private HomePresenter mHomePresenter;
    private FriendPresenter mFriendPresenter;
    private RankPresenter mRankPresenter;
    private ProfilePresenter mProfilePresenter;

    private Looking4RoomPresenter mLooking4RoomPresenter;
    private CourtsMapPresenter mCourtsMapPresenter;

    private Want2CreateRoomPresenter mWant2CreateRoomPresenter;
    private Waiting4JoinMasterPresenter mWaiting4JoinMasterPresenter;
    private Waiting4JoinSlavePresenter mWaiting4JoinSlavePresenter;
    private RefereeGoingPresenter mRefereeGoingPresenter;
    private PlayerGoingPresenter mPlayerGoingPresenter;

    private RefereeResultPresenter mRefereeResultPresenter;

    private static boolean mIsBackKeyDisable;

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
    public void openGameResultSlave() {

    }

    @Override
    public void updateGameResultOfPlayer(GamingRoomInfo gamingRoomInfo) {
        mRefereeGoingPresenter.updateGameResultOfPlayer(gamingRoomInfo);
    }

    @Override
    public void openGameResultReferee(String hostName) {
        mMainView.openRefereeResultUi(hostName);
    }



    @Override
    public void forced2FinishGaming() {

    }

    @Override
    public void finishResultResultUi() {

    }

    /* ------------------------------------------------------------------------------------------ */
    /* adjust player's records - PlayingFragment */

    @Override
    public void fireStoreScorePlusP1() {
        mRefereeGoingPresenter.fireStoreScorePlusP1();
    }

    @Override
    public void fireStoreScoreMinusP1() {
        mRefereeGoingPresenter.fireStoreScoreMinusP1();
    }

    @Override
    public void fireStoreReboundPlusP1() {
        mRefereeGoingPresenter.fireStoreReboundPlusP1();
    }

    @Override
    public void fireStoreReboundMinusP1() {
        mRefereeGoingPresenter.fireStoreReboundMinusP1();
    }

    @Override
    public void fireStoreFoulPlusP1() {
        mRefereeGoingPresenter.fireStoreFoulPlusP1();
    }

    @Override
    public void fireStoreFoulMinusP1() {
        mRefereeGoingPresenter.fireStoreFoulMinusP1();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* General in both master & slave */

    @Override
    public void openGamePlayingOfReferee(String hostName) {
        mMainView.openGamePlayingOfRefereeUi(hostName);
    }

    @Override
    public void openGamePlayingOfPlayer(String hostName) {
        mMainView.openGamePlayingOfPlayerUi(hostName);
    }

    @Override
    public void finishWaiting4JoinUi() {
        mMainView.popBackStackUi();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* Waiting Slave */

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
    public void finishWant2CreateRoomUi() {
        mMainView.popBackStackUi();
    }

    @Override
    public void onRoomNameEditTextChange(CharSequence charSequence) {
        mWant2CreateRoomPresenter.onRoomNameEditTextChange(charSequence);
    }

    @Override
    public void setBackKeyDisable(boolean isBackKeyDisable) {
        mIsBackKeyDisable = isBackKeyDisable;
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

    /* ------------------------------------------------------------------------------------------ */

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
    public void onLoginSuccess(int loginFrom) {

    }

    @Override
    public void showLoginDialog(int loginFrom) {

    }

    @Override
    public void showCheckOutSuccessDialog() {

    }

    @Override
    public void showRatingRefereeSuccessDialog() {

    }

    @Override
    public void showLoginSuccessDialog() {

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
    public void loadProfileUserData() {

    }

    @Override
    public void checkProfileUserData() {

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

    @Override
    public void openWant2CreateRoom() {
        mMainView.openWant2CreateRoomUi();
    }


    @Override
    public void openWaiting4JoinSlave(WaitingRoomInfo waitingRoomInfo) {
        mMainView.openWaiting4JoinSlaveUi(waitingRoomInfo);
    }

    @Override
    public void showErrorToast(String message, boolean isShort) {
        Toast toast;

        if (isShort){
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
    public boolean disableBackKey() {
        return mIsBackKeyDisable;
    }
}
