package com.kerry.gogobasketball;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
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
import com.kerry.gogobasketball.playing.referee.RefereeGoingContract;
import com.kerry.gogobasketball.playing.referee.RefereeGoingPresenter;
import com.kerry.gogobasketball.profile.ProfileContract;
import com.kerry.gogobasketball.profile.ProfilePresenter;
import com.kerry.gogobasketball.rank.RankContract;
import com.kerry.gogobasketball.rank.RankPresenter;
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
        RefereeGoingContract.Presenter {

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
    private RefereeGoingPresenter mPlayingOfRefereePresenter;

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

    void setGamePlayingOfRefereePresenter(RefereeGoingPresenter playingOfRefereePresenter) {
        mPlayingOfRefereePresenter = checkNotNull(playingOfRefereePresenter);
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
    public void loadPlayersInfoFromFirebase() {

    }

    @Override
    public void loadRefereeInfoFromFirebase() {

    }

    /**
     * Open Wait4Join
     */

    @Override
    public void forced2FinishPlayingUi() {

    }

    @Override
    public void showGameResultUi() {

    }

    /* ------------------------------------------------------------------------------------------ */
    /* adjust player's records - PlayingFragment */

    @Override
    public void fireStoreScorePlusP1() {
        mPlayingOfRefereePresenter.fireStoreScorePlusP1();
    }

    @Override
    public void fireStoreScoreMinusP1() {
        mPlayingOfRefereePresenter.fireStoreScoreMinusP1();
    }

    @Override
    public void fireStoreReboundPlusP1() {
        mPlayingOfRefereePresenter.fireStoreReboundPlusP1();
    }

    @Override
    public void fireStoreReboundMinusP1() {
        mPlayingOfRefereePresenter.fireStoreReboundMinusP1();
    }

    @Override
    public void fireStoreFoulPlusP1() {
        mPlayingOfRefereePresenter.fireStoreFoulPlusP1();
    }

    @Override
    public void fireStoreFoulMinusP1() {
        mPlayingOfRefereePresenter.fireStoreFoulMinusP1();
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public void openGamePlayingOfReferee() {
        mMainView.openGamePlayingOfRefereeUi();
    }

    @Override
    public void openGamePlayingOfSlave() {
//        mMainView.openGamePlayingOfSlaveUi();
    }

    @Override
    public void finishWaiting4JoinUi() {
        mMainView.popBackStackUi();
    }

//    @Override
//    public void updateRoomInfo2FireBase() {
//        mWaiting4JoinSlavePresenter.updateRoomInfo2FireBase();
//    }

    @Override
    public void getHostNameFromLooking4Room(WaitingRoomInfo waitingRoomInfo) {
        mWaiting4JoinSlavePresenter.getHostNameFromLooking4Room(waitingRoomInfo);
    }

    @Override
    public void deleteSeatInfoWhenLeaveRoom() {
        mWaiting4JoinSlavePresenter.deleteSeatInfoWhenLeaveRoom();
    }

    @Override
    public void getRoomInfoFromWant2Create(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {
        mWaiting4JoinMasterPresenter.getRoomInfoFromWant2Create(waitingRoomInfo, hostSeatInfo, roomDocId);
    }

    @Override
    public void deleteRoomWhenLeaveRoom() {
        mWaiting4JoinMasterPresenter.deleteRoomWhenLeaveRoom();
    }

    @Override
    public void openWaitingJoin(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {
        mMainView.openWait4JoinUi(waitingRoomInfo, hostSeatInfo, roomDocId);
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
    public void showErrorToast(String message) {
        Toast toast = Toast.makeText(GoGoBasketball.getAppContext(), "無效", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(16);
        toastTV.setText(message);
        toast.show();
    }


}
