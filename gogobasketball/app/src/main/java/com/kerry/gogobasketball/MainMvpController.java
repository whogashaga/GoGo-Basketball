package com.kerry.gogobasketball;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.kerry.gogobasketball.home.HomeFragment;
import com.kerry.gogobasketball.home.HomePresenter;
import com.kerry.gogobasketball.home.item.LookingForRoomFragment;
import com.kerry.gogobasketball.home.item.LookingForRoomPresenter;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.home.map.CourtsMapPresenter;
import com.kerry.gogobasketball.profile.ProfileFragment;
import com.kerry.gogobasketball.profile.ProfilePresenter;
import com.kerry.gogobasketball.util.ActivityUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainMvpController {

    private final FragmentActivity mActivity;
    private MainPresenter mMainPresenter;

    private HomePresenter mHomePresenter;
    private ProfilePresenter mProfilePresenter;

    private LookingForRoomPresenter mLookingForRoomPresenter;
    private CourtsMapPresenter mCourtsMapPresenter;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            HOME, FRIEND, RANK, PROFILE
    })
    public @interface FragmentType {}
    static final String HOME    = "HOME";
    static final String FRIEND = "FRIEND";
    static final String RANK    = "RANK";
    static final String PROFILE = "PROFILE";


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            ROOMS, MAP
    })
    public @interface HomeItem {}
    public static final String ROOMS       = "ROOMS";
    public static final String MAP         = "MAP";

    private MainMvpController(@NonNull FragmentActivity activity) {
        mActivity = activity;
    }





    /**
     * Home Fragment View
     */
    void findOrCreateHomeView() {

        HomeFragment homeFragment = findOrCreateHomeFragment();

        if (mHomePresenter == null) {
            mHomePresenter = new HomePresenter(homeFragment);
            mMainPresenter.setHomePresenter(mHomePresenter);
            homeFragment.setPresenter(mMainPresenter);
        }
    }

    /**
     * Home Profile View
     */
    void findOrCreateProfileView() {

        ProfileFragment profileFragment = findOrCreateProfileFragment();

        if (mProfilePresenter == null) {
            mProfilePresenter = new ProfilePresenter(profileFragment);
            mMainPresenter.setProfilePresenter(mProfilePresenter);
            profileFragment.setPresenter(mMainPresenter);
        }
    }


    /**
     * LookingForRooms View
     * @return CatalogItemFragment: Rooms Fragment
     */
    LookingForRoomFragment findOrCreateLookingForRoomView() {

        LookingForRoomFragment fragment = findOrCreateRoomsItemFragment(ROOMS);

        mLookingForRoomPresenter = new LookingForRoomPresenter(fragment);
        fragment.setPresenter(mMainPresenter);
        mMainPresenter.setLookingForRoomPresenter(mLookingForRoomPresenter);

        return fragment;
    }

    /**
     * LookingForRooms View
     * @return CatalogItemFragment: Rooms Fragment
     */
    CourtsMapFragment findOrCreateMapView() {

        CourtsMapFragment fragment = findOrCreateMapItemFragment(MAP);

        mCourtsMapPresenter = new CourtsMapPresenter(fragment);
        fragment.setPresenter(mMainPresenter);
        mMainPresenter.setCourtsMapPresenter(mCourtsMapPresenter);

        return fragment;
    }

    /* ------------------------------------------------------------------------------------------ */


    /**
     * Home Fragment
     * @return HomeFragment
     */
    @NonNull
    private HomeFragment findOrCreateHomeFragment() {

        HomeFragment homeFragment =
                (HomeFragment) getFragmentManager().findFragmentByTag(HOME);
        if (homeFragment == null) {
            // Create the fragment
            homeFragment = HomeFragment.newInstance();
        }

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), homeFragment, HOME);

        return homeFragment;
    }

    /**
     * Profile Fragment
     * @return ProfileFragment
     */
    @NonNull
    private ProfileFragment findOrCreateProfileFragment() {

        ProfileFragment profileFragment =
                (ProfileFragment) getFragmentManager().findFragmentByTag(PROFILE);
        if (profileFragment == null) {
            // Create the fragment
            profileFragment = ProfileFragment.newInstance();
        }

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), profileFragment, PROFILE);

        return profileFragment;
    }

    /**
     * Home Item Fragment: Map
     * @param itemType: @HomeItem
     * @return LookingForRoomFragment
     */
    @NonNull
    private LookingForRoomFragment findOrCreateRoomsItemFragment(@HomeItem String itemType) {

        LookingForRoomFragment fragment =
                (LookingForRoomFragment) (getFragmentManager().findFragmentByTag(HOME))
                        .getChildFragmentManager().findFragmentByTag(itemType);
        if (fragment == null) {
            // Create the fragment
            fragment = LookingForRoomFragment.newInstance();
        }

        return fragment;
    }


    /**
     * Home Item Fragment: Map
     * @param itemType: @HomeItem
     * @return CourtsMapFragment
     */
    @NonNull
    private CourtsMapFragment findOrCreateMapItemFragment(@HomeItem String itemType) {

        CourtsMapFragment fragment =
                (CourtsMapFragment) (getFragmentManager().findFragmentByTag(HOME))
                        .getChildFragmentManager().findFragmentByTag(itemType);
        if (fragment == null) {
            // Create the fragment
            fragment = CourtsMapFragment.newInstance();
        }

        return fragment;
    }

    /**
     * Creates a controller.
     * @param activity the context activity
     * @return a MainMvpController
     */
    static MainMvpController create(@NonNull FragmentActivity activity) {
        checkNotNull(activity);
        MainMvpController mainMvpController = new MainMvpController(activity);
        mainMvpController.createMainPresenter();
        return mainMvpController;
    }

    /**
     * Create Main Presenter
     * @return MainPresenter
     */
    private MainPresenter createMainPresenter() {
        mMainPresenter = new MainPresenter((MainActivity) mActivity);
        return mMainPresenter;
    }

    private FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }
}
