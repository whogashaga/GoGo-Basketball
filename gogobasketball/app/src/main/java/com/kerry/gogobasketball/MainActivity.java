package com.kerry.gogobasketball;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kerry.gogobasketball.home.item.LookingForRoomFragment;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends BaseActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private BottomNavigationView mBottomNavigation;
    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private ImageView mToolbarLogo;
    //    private LoginDialog mLoginDialog;
//    private MessageDialog mMessageDialog;
    private View mBadge;
    private ImageView mDrawerUserImage;
    private TextView mDrawerUserName;
    private TextView mDrawerUserInfo;
    private MainMvpController mMainMvpController;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);

        mMainMvpController = MainMvpController.create(this);
        mPresenter.openHome();

        setToolbar();
        setBottomNavigation();
        setDrawerLayout();
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
        mToolbarLogo = mToolbar.findViewById(R.id.image_toolbar_logo);
    }

    /**
     * plugin: BottomNavigationViewEx.
     */
    private void setBottomNavigation() {

        mBottomNavigation = findViewById(R.id.bottom_navigation_main);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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

    /**
     * Set Drawer
     */
    private void setDrawerLayout() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.layout_main);
        mDrawerLayout.setFitsSystemWindows(true);
        mDrawerLayout.setClipToPadding(false);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                mPresenter.onDrawerOpened();
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        // nav view header
//        mDrawerUserImage = navigationView.getHeaderView(0).findViewById(R.id.image_drawer_avatar);
//        mDrawerUserImage.setOutlineProvider(new ProfileAvatarOutlineProvider());
//        mDrawerUserImage.setOnClickListener(v -> mPresenter.onClickDrawerAvatar());
//
//        mDrawerUserName = navigationView.getHeaderView(0).findViewById(R.id.image_drawer_name);
//
//        mDrawerUserInfo = navigationView.getHeaderView(0).findViewById(R.id.image_drawer_info);
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

                mPresenter.updateToolbar("");
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

        String string = "";

        switch (item.getItemId()) {

            case R.id.nav_awaiting_payment:
                string = getString(R.string.awaiting_payment);
                break;
            case R.id.nav_awaiting_shipment:
                string = getString(R.string.awaiting_shipment);
                break;
            case R.id.nav_shipped:
                string = getString(R.string.shipped);
                break;
            case R.id.nav_awaiting_review:
                string = getString(R.string.awaiting_review);
                break;
            case R.id.nav_exchange:
                string = getString(R.string.exchange);
                break;
            default:
        }

        Toast.makeText(this, getString(R.string._coming_soon, string), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void openHomeUi() {
        mMainMvpController.findOrCreateHomeView();
    }

    @Override
    public void openProfileUi() {
        mMainMvpController.findOrCreateProfileView();
    }

    @Override
    public void openLoginUi(int loginFrom) {

    }

    @Override
    public void openCheckOutSuccessUi() {

    }

    @Override
    public LookingForRoomFragment findHomeView() {
        return mMainMvpController.findOrCreateLookingForRoomView();
    }

    @Override
    public CourtsMapFragment findMapView() {
        return mMainMvpController.findOrCreateMapView();
    }

    @Override
    public void showToastUi(String message) {

    }

    @Override
    public void hideToolbarUi() {

    }

    @Override
    public void showToolbarUi() {

    }

    @Override
    public void hideBottomNavigationUi() {

    }

    @Override
    public void showBottomNavigationUi() {

    }

    @Override
    public void setToolbarTitleUi(String title) {
        if ("".equals(title)) {

            mToolbarTitle.setVisibility(View.GONE);
            mToolbarLogo.setVisibility(View.VISIBLE);

        } else {

            if (title.equals(getString(R.string.payment))) {
                mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mToolbar.setNavigationIcon(R.drawable.toolbar_back);
                mActionBarDrawerToggle.setToolbarNavigationClickListener(v -> onBackPressed());
            } else {
                mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                mToolbar.setNavigationIcon(R.drawable.toolbar_menu);
                mActionBarDrawerToggle.setToolbarNavigationClickListener(null);
            }

            mToolbarLogo.setVisibility(View.GONE);
            mToolbarTitle.setVisibility(View.VISIBLE);
            mToolbarTitle.setText(title);
        }
    }

    @Override
    public void closeDrawerUi() {

    }

    @Override
    public void showDrawerUserUi() {

    }






}

