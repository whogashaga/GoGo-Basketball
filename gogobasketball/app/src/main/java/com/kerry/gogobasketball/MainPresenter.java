package com.kerry.gogobasketball;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.FirebaseFirestore;
import com.kerry.gogobasketball.friends.FriendContract;
import com.kerry.gogobasketball.friends.FriendPresenter;
import com.kerry.gogobasketball.home.HomeContract;
import com.kerry.gogobasketball.home.HomePresenter;
import com.kerry.gogobasketball.home.item.LookingForRoomContract;
import com.kerry.gogobasketball.home.item.LookingForRoomFragment;
import com.kerry.gogobasketball.home.item.LookingForRoomPresenter;
import com.kerry.gogobasketball.home.map.CourtsMapContract;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.home.map.CourtsMapPresenter;
import com.kerry.gogobasketball.playing0referee.GamePlayingOfRefereeContract;
import com.kerry.gogobasketball.playing0referee.GamePlayingOfRefereePresenter;
import com.kerry.gogobasketball.profile.ProfileContract;
import com.kerry.gogobasketball.profile.ProfilePresenter;
import com.kerry.gogobasketball.rank.RankContract;
import com.kerry.gogobasketball.rank.RankPresenter;
import com.kerry.gogobasketball.waiting4join.Waiting4JoinContract;
import com.kerry.gogobasketball.waiting4join.Waiting4JoinPresenter;
import com.kerry.gogobasketball.want2create.Want2CreateRoomContract;
import com.kerry.gogobasketball.want2create.Want2CreateRoomPresenter;

public class MainPresenter implements MainContract.Presenter, HomeContract.Presenter,
        LookingForRoomContract.Presenter, CourtsMapContract.Presenter, ProfileContract.Presenter,
        FriendContract.Presenter, RankContract.Presenter, Want2CreateRoomContract.Presenter,
        Waiting4JoinContract.Presenter, GamePlayingOfRefereeContract.Presenter {

    private FirebaseFirestore mDb;
    private MainContract.View mMainView;

    private HomePresenter mHomePresenter;
    private FriendPresenter mFriendPresenter;
    private RankPresenter mRankPresenter;
    private ProfilePresenter mProfilePresenter;

    private LookingForRoomPresenter mLookingForRoomPresenter;
    private CourtsMapPresenter mCourtsMapPresenter;

    private Want2CreateRoomPresenter mWant2CreateRoomPresenter;
    private Waiting4JoinPresenter mWaiting4JoinPresenter;
    private GamePlayingOfRefereePresenter mPlayingOfRefereePresenter;

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

    void setLookingForRoomPresenter(LookingForRoomPresenter lookingForRoomPresenter) {
        mLookingForRoomPresenter = checkNotNull(lookingForRoomPresenter);
    }

    void setCourtsMapPresenter(CourtsMapPresenter courtsMapPresenter) {
        mCourtsMapPresenter = checkNotNull(courtsMapPresenter);
    }

    void setWant2CreateRoomPresenter(Want2CreateRoomPresenter want2CreateRoomPresenter) {
        mWant2CreateRoomPresenter = checkNotNull(want2CreateRoomPresenter);
    }

    void setWaiting4JoinPresenter(Waiting4JoinPresenter waiting4JoinPresenter) {
        mWaiting4JoinPresenter = checkNotNull(waiting4JoinPresenter);
    }

    void setGamePlayingOfRefereePresenter(GamePlayingOfRefereePresenter playingOfRefereePresenter) {
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
    public void finishWaiting4JoinUi() {
        mMainView.popBackStackUi();
    }

    @Override
    public void openWaitingJoin() {
        mMainView.openWait4JoinUi();
    }

    @Override
    public void finishWant2CreateRoomUi() {
        mMainView.popBackStackUi();
    }

    /**
     * Open Want2CreateRoom
     */
    @Override
    public void updateRoomName2Firebase() {

    }

    @Override
    public void updateLocation2Firebase() {

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
    public LookingForRoomFragment findRoomsView() {
        return mMainView.findHomeView();
    }

    @Override
    public CourtsMapFragment findMapView() {
        return mMainView.findMapView();
    }

    /* ------------------------------------------------------------------------------------------ */
    /* LookingForRoom Presenter */

    @Override
    public void loadExistedRoomsData() {

    }

    @Override
    public void setExistedRoomsData() {

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


}
