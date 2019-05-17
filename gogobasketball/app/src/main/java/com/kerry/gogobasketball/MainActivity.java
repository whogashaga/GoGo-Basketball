package com.kerry.gogobasketball;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.home.item.Looking4RoomFragment;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.rank.player.RankPlayerFragment;
import com.kerry.gogobasketball.rank.referee.RankRefereeFragment;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.UserManager;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends BaseActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    private View mView;
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
        init();
        mView = this.findViewById(R.id.layout_main);
        mView.setBackgroundResource(R.drawable.wheel_dunk_28);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UserManager.getInstance().getFbCallbackManager().onActivityResult(requestCode, resultCode, data);
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

    }

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
    public void switchHotsUiInitiative() {
        mBottomNavigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public void switchProfileUiInitiative() {
        mBottomNavigation.setSelectedItemId(R.id.navigation_profile);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void openHomeByBackStack() {
        getFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
    public void openUserDetailUi(String userId) {
        mMainMvpController.findOrCreateUserDetailView(userId);
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
    public void openInstructionUi() {
        mMainMvpController.findOrCreateInstructionView();
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(Constants.TAG, "MainActivity onResume: ");
        mPresenter.getDeviceCurrentLocation();
        mPresenter.setLocationHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            Log.w(Constants.TAG, "MainActivity onDestroy: ");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "MainActivity onStop: ");
        if (mPresenter != null) {
            mPresenter.getUserInfoWhenGetOutOfApp();
            mPresenter.removeHandler();
        }
    }
}

