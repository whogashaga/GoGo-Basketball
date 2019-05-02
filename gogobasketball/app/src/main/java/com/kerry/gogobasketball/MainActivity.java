package com.kerry.gogobasketball;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.kerry.gogobasketball.data.CourtsInfo;
import com.kerry.gogobasketball.data.GamingPlayer;
import com.kerry.gogobasketball.data.GamingReferee;
import com.kerry.gogobasketball.data.GamingRoomInfo;
import com.kerry.gogobasketball.data.RecordOfPlayer;
import com.kerry.gogobasketball.data.RecordOfReferee;
import com.kerry.gogobasketball.data.User;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.home.item.Looking4RoomFragment;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.rank.player.RankPlayerFragment;
import com.kerry.gogobasketball.rank.referee.RankRefereeFragment;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.UserManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends BaseActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    private Button mBtnCreateUser;
    private View mView;
    FirebaseFirestore mDb = FirebaseFirestore.getInstance();

    private BottomNavigationView mBottomNavigation;
    private MessageDialog mMessageDialog;
    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private ImageView mToolbarLogo;
    private MainMvpController mMainMvpController;
    private boolean doubleBackToExitPressedOnce = false;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
//        startActivity(new Intent(this, LogoActivity.class));
        init();
        mView = this.findViewById(R.id.layout_main);
        mView.setBackgroundResource(R.drawable.wheel_dunk_28);

//        postCustomObject();
//        getCustomObject();

    }

    private void init() {
        setContentView(R.layout.activity_main);
        mMainMvpController = MainMvpController.create(this);

        if (UserManager.getInstance().isLoggedIn()) {
            mPresenter.onLoginSuccessBeforeOpenApp(AccessToken.getCurrentAccessToken().getUserId().trim());
        } else {
            mPresenter.showLoginFragment();
        }

        setToolbar();
        setBottomNavigation();

        mPresenter.getDeviceCurrentLocation();
//        mPresenter.setLocationHandler(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UserManager.getInstance().getFbCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    public void postCustomObject() {

        CourtsInfo courtsInfo = new CourtsInfo();

        courtsInfo.setLocation(getString(R.string.song_san_high_school));
        courtsInfo.setLat(25.043572);
        courtsInfo.setLong(121.565559);
        courtsInfo.setLatMin(25.043095);
        courtsInfo.setLongMin(121.563557);
        courtsInfo.setLatMax(25.044416);
        courtsInfo.setLongMax(121.565868);
        courtsInfo.setPopulation(0);

//        courtsInfo.setLocation(getString(R.string.adidas_101));
//        courtsInfo.setLat(25.032598);
//        courtsInfo.setLong(121.561610);
//        courtsInfo.setLatMin(25.032135);
//        courtsInfo.setLongMin(121.561168);
//        courtsInfo.setLatMax(25.032994);
//        courtsInfo.setLongMax(121.562496);
//        courtsInfo.setPopulation(0);

//        courtsInfo.setLocation(getString(R.string.young_park));
//        courtsInfo.setLat(25.021023);
//        courtsInfo.setLong(121.505110);
//        courtsInfo.setLatMin(25.020526);
//        courtsInfo.setLongMin(121.504470);
//        courtsInfo.setLatMax(25.021701);
//        courtsInfo.setLongMax(121.505921);
//        courtsInfo.setPopulation(0);

//        courtsInfo.setLocation(getString(R.string.xin_sheng_high));
//        courtsInfo.setLat(25.045040);
//        courtsInfo.setLong(121.530423);
//        courtsInfo.setLatMin(25.044851);
//        courtsInfo.setLongMin(121.530165);
//        courtsInfo.setLatMax(25.045585);
//        courtsInfo.setLongMax(121.530777);
//        courtsInfo.setPopulation(0);

//        courtsInfo.setLocation(getString(R.string.da_an_park));
//        courtsInfo.setLat(25.031693);
//        courtsInfo.setLong(121.535961);
//        courtsInfo.setLatMin(25.030690);
//        courtsInfo.setLongMin(121.534593);
//        courtsInfo.setLatMax(25.032751);
//        courtsInfo.setLongMax(121.537530);
//        courtsInfo.setPopulation(0);

//        courtsInfo.setLocation(getString(R.string.tai_da_central));
//        courtsInfo.setLat(25.020213);
//        courtsInfo.setLong(121.536475);
//        courtsInfo.setLatMin(25.019771);
//        courtsInfo.setLongMin(121.535612);
//        courtsInfo.setLatMax(25.020811);
//        courtsInfo.setLongMax(121.537397);
//        courtsInfo.setPopulation(0);

        mBtnCreateUser = findViewById(R.id.main_layout_create_user);
        mBtnCreateUser.setVisibility(View.VISIBLE);
        mBtnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirestoreHelper.getFirestore()
                        .collection(Constants.COURTS)
                        .document(Constants.SONG_SAN_HIGH_SCHOOL)
                        .set(courtsInfo)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d(Constants.TAG, "onComplete: courts update");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(Constants.TAG, "Error adding document", e);
                            }
                        });
            }
        });
    }

    public void getCustomObject() {

        DocumentReference docRef = mDb.collection("custom_obj")
                .document("waitingRoomInfo1");

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                WaitingRoomInfo waitingRoomInfo = documentSnapshot.toObject(WaitingRoomInfo.class);
            }
        });

    }

    /**
     * Let toolbar to extend to status bar.
     *
     * @notice this method have to be used after setContentView.
     */
    private void setToolbar() {
        // Retrieve the AppCompact Toolbar
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        // Set the padding to match the Status Bar height
        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        mToolbarTitle = mToolbar.findViewById(R.id.text_toolbar_title);
        mToolbarTitle.setVisibility(View.VISIBLE);
        mToolbarLogo = mToolbar.findViewById(R.id.image_toolbar_logo);
        mToolbarLogo.setVisibility(View.GONE);
    }

    /**
     * plugin: BottomNavigationViewEx.
     */
    private void setBottomNavigation() {

        mBottomNavigation = findViewById(R.id.bottom_navigation_main);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigation.setBackgroundTintList(null);

        BottomNavigationMenuView menuView =
                (BottomNavigationMenuView) mBottomNavigation.getChildAt(0);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            layoutParams.height = (int) getResources().getDimension(R.dimen.size_bottom_nav_icon);
            layoutParams.width = (int) getResources().getDimension(R.dimen.size_bottom_nav_icon);
            iconView.setLayoutParams(layoutParams);
        }

        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(1);
//        mBadge = LayoutInflater.from(this)
//                .inflate(R.layout.badge_main_bottom, itemView, true);

//        mPresenter.updateFriendBadge();
    }

//    /**
//     * Set Drawer
//     */
//    private void setDrawerLayout() {
//
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.layout_main);
//        mDrawerLayout.setFitsSystemWindows(true);
//        mDrawerLayout.setClipToPadding(false);
//
//        mActionBarDrawerToggle = new ActionBarDrawerToggle(
//                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//
//                mPresenter.onDrawerOpened();
//            }
//        };
//        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
//        mActionBarDrawerToggle.syncState();
//
//        NavigationView navigationView = findViewById(R.id.navigation_drawer);
//        navigationView.setNavigationItemSelectedListener(this);

    // nav view header
//        mDrawerUserImage = navigationView.getHeaderView(0).findViewById(R.id.image_drawer_avatar);
//        mDrawerUserImage.setOutlineProvider(new SeatAvatarOutlineProvider());
//        mDrawerUserImage.setOnClickListener(v -> mPresenter.onClickDrawerAvatar());
//
//        mDrawerUserName = navigationView.getHeaderView(0).findViewById(R.id.image_drawer_name);
//
//        mDrawerUserInfo = navigationView.getHeaderView(0).findViewById(R.id.image_drawer_info);
//    }

    /**
     * @return height of status bar
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * It's the item selected listener of bottom navigation.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {

        switch (item.getItemId()) {
            case R.id.navigation_home:

                mPresenter.updateToolbar(MainActivity.this.getResources().getString(R.string.lobby));
                mPresenter.openHome();
                return true;

            case R.id.navigation_friends:

                mPresenter.updateToolbar(MainActivity.this.getResources().getString(R.string.friends));
                mPresenter.openFriend();
                return true;

            case R.id.navigation_rank:

                mPresenter.updateToolbar(MainActivity.this.getResources().getString(R.string.rank));
                mPresenter.openRank();
                return true;

            case R.id.navigation_profile:

                mPresenter.updateToolbar(MainActivity.this.getResources().getString(R.string.profile));
                mPresenter.openProfile();
                return true;

            default:
                return false;
        }

    };

    @Override
    public void switchProfileUiInitiative() {
        mBottomNavigation.setSelectedItemId(R.id.navigation_profile);
    }

    @Override
    public void switchHotsUiInitiative() {
        mBottomNavigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

        String string = "";

        switch (item.getItemId()) {

        }
        return true;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    /* ------------------------------------------------------------------------------------------ */
    /* open UI */
    @Override
    public void openHomeUi() {
        mMainMvpController.findOrCreateHomeView();
    }

    @Override
    public void openProfileUi() {
        mMainMvpController.findOrCreateProfileView();
    }

    @Override
    public void openFriendsUi() {
        mMainMvpController.findOrCreateFriendsView();
    }

    @Override
    public void openRankUi() {
        mMainMvpController.findOrCreateRankView();
    }

    @Override
    public void openWant2CreateRoomUi() {
        mMainMvpController.findOrCreateWant2CreateRoomView();
    }

    @Override
    public void openWaiting4JoinSlaveUi(WaitingRoomInfo waitingRoomInfo) {
        mMainMvpController.findOrCreateWaiting4JoinSlaveView(waitingRoomInfo);
    }

    @Override
    public void openWait4JoinUi(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatInfo, String roomDocId) {
        mMainMvpController.findOrCreateWaiting4JoinView(waitingRoomInfo, hostSeatInfo, roomDocId);
    }

    @Override
    public void openGamePlayingOfRefereeUi(String hostName) {
        mMainMvpController.findOrCreateRefereeGoingView(hostName);
    }

    @Override
    public void openGamePlayingOfPlayerUi(String hostName, int nowSort) {
        mMainMvpController.findOrCreatePlayerGoingView(hostName, nowSort);
    }

    @Override
    public void openRefereeResultUi(String hostName) {
        mMainMvpController.findOrCreateRefereeResultView(hostName);
    }

    @Override
    public void openPlayerResultUi(String hostName, int nowSort) {
        mMainMvpController.findOrCreatePlayerResultView(hostName, nowSort);
    }

    @Override
    public void openCommentRefereeUi(String refereeName) {
        mMainMvpController.findOrCreateCommentRefereeView(refereeName);
    }

    @Override
    public void openLogOutUi() {
        mMainMvpController.findOrCreateLogoutView();
    }

    @Override
    public void openChangeIdUi() {
        mMainMvpController.findOrCreateChangeIdView();
    }

    @Override
    public void openChangeGenderUi(String currentGender) {
        mMainMvpController.findOrCreateChangeGenderView(currentGender);
    }

    @Override
    public void openChangePositionUi(String currentPosition) {
        mMainMvpController.findOrCreateChangePositionView(currentPosition);
    }

    @Override
    public void openFindHostUi() {
        mMainMvpController.findOrCreateFindHostView();
    }

    @Override
    public void openCreateUserUi(String userFbId) {
        mMainMvpController.findOrCreateCreateUserView(userFbId);
    }

    @Override
    public void openLoginUi() {
        mMainMvpController.findOrCreateLoginView();
    }

    @Override
    public void openCheckOutSuccessUi() {

    }

    @Override
    public void popBackStackUi() {
        getSupportFragmentManager().popBackStack();
    }

    /* ------------------------------------------------------------------------------------------ */

    @Override
    public Looking4RoomFragment findHomeView() {
        return mMainMvpController.findOrCreateLookingForRoomView();
    }

    @Override
    public CourtsMapFragment findMapView() {
        return mMainMvpController.findOrCreateMapView();
    }

    @Override
    public RankPlayerFragment findRankPlayerView() {
        return mMainMvpController.findOrCreateRankPlayerView();
    }

    @Override
    public RankRefereeFragment findRankRefereeView() {
        return mMainMvpController.findOrCreateRankRefereeView();
    }

    @Override
    public void showMessageDialogUi(int type) {
        if (mMessageDialog == null) {

            mMessageDialog = new MessageDialog();
            mMessageDialog.setMessage(type);
            mMessageDialog.show(getSupportFragmentManager(), "");

        } else if (!mMessageDialog.isAdded()) {

            mMessageDialog.setMessage(type);
            mMessageDialog.show(getSupportFragmentManager(), "");
        }
    }

    @Override
    public void showToastUi(String message) {

    }

    @Override
    public void hideToolbarUi() {
        mToolbar.setVisibility(View.GONE);
    }

    @Override
    public void showToolbarUi() {
        mToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBottomNavigationUi() {
        mBottomNavigation.setVisibility(View.GONE);
    }

    @Override
    public void showBottomNavigationUi() {
        mBottomNavigation.setVisibility(View.VISIBLE);
    }

    @Override
    public void setToolbarTitleUi(String title) {

        mToolbarTitle.setVisibility(View.VISIBLE);
        mToolbarTitle.setText(title);

    }

    @Override
    public void closeDrawerUi() {

    }

    @Override
    public void showDrawerUserUi() {

    }

    @Override
    public void onBackPressed() {
        if (mPresenter.disableBackKey() && mPresenter.isGamingNow()) {
            mPresenter.showErrorToast("比賽尚未結束\n請勿離場！！", true);

        } else if (mPresenter.disableBackKey() && mPresenter.have2Comment()) {
            mPresenter.showErrorToast("評論裁判後\n方可離場！！", true);

        } else if (mPresenter.disableBackKey()) {
            // do nothing
        } else if (mPresenter.openingWant2CreateRoom()) {
            super.onBackPressed();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "離開請再按一次 ' 返回 ' ", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }

    }

    @Override
    public void showActivityBackgroundWhenLandScape() {
        mView.setBackgroundResource(R.drawable.street);
    }

    @Override
    public void showActivityBackgroundWhenPortrait() {
        mView.setBackgroundResource(R.drawable.wheel_dunk_28);
//        AnimationDrawable drawable = (AnimationDrawable) mView.getBackground();
//        drawable.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("Kerry", "MainActivity onResume: ");
        mPresenter.getDeviceCurrentLocation();
        mPresenter.setLocationHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.getUserInfoWhenGetOutOfApp();
            mPresenter.removeHandler();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Kerry", "MainActivity onStop: ");
        if (mPresenter != null) {
            mPresenter.getUserInfoWhenGetOutOfApp();
            mPresenter.removeHandler();
        }
    }
}

