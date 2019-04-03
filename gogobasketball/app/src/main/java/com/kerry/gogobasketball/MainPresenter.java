package com.kerry.gogobasketball;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.kerry.gogobasketball.home.HomeContract;
import com.kerry.gogobasketball.home.HomePresenter;
import com.kerry.gogobasketball.home.item.LookingForRoomContract;
import com.kerry.gogobasketball.home.item.LookingForRoomFragment;
import com.kerry.gogobasketball.home.item.LookingForRoomPresenter;
import com.kerry.gogobasketball.home.map.CourtsMapContract;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.home.map.CourtsMapPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter, HomeContract.Presenter,
        LookingForRoomContract.Presenter, CourtsMapContract.Presenter {

    private MainContract.View mMainView;

    private HomePresenter mHomePresenter;
    private LookingForRoomPresenter mLookingForRoomPresenter;
    private CourtsMapPresenter mCourtsMapPresenter;

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


    void setLookingForRoomPresenter(LookingForRoomPresenter lookingForRoomPresenter) {
        mLookingForRoomPresenter = checkNotNull(lookingForRoomPresenter);
    }

    void setCourtsMapPresenter(CourtsMapPresenter courtsMapPresenter) {
        mCourtsMapPresenter = checkNotNull(courtsMapPresenter);
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

    }

    @Override
    public void openRank() {

    }

    @Override
    public void openProfile() {

    }

    @Override
    public void switchToProfileByBottomNavigation() {

    }

    @Override
    public void hideToolbarAndBottomNavigation() {

    }

    @Override
    public void showToolbarAndBottomNavigation() {

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

    @Override
    public void result(int requestCode, int resultCode) {

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


}
