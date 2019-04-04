package com.kerry.gogobasketball;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.kerry.gogobasketball.friends.FriendFragment;
import com.kerry.gogobasketball.friends.FriendPresenter;
import com.kerry.gogobasketball.home.HomeFragment;
import com.kerry.gogobasketball.home.HomePresenter;
import com.kerry.gogobasketball.home.item.LookingForRoomFragment;
import com.kerry.gogobasketball.home.item.LookingForRoomPresenter;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.home.map.CourtsMapPresenter;
import com.kerry.gogobasketball.profile.ProfileFragment;
import com.kerry.gogobasketball.profile.ProfilePresenter;
import com.kerry.gogobasketball.rank.RankFragment;
import com.kerry.gogobasketball.rank.RankPresenter;
import com.kerry.gogobasketball.util.ActivityUtils;
import com.kerry.gogobasketball.waiting4join.Waiting4JoinFragment;
import com.kerry.gogobasketball.waiting4join.Waiting4JoinPresenter;
import com.kerry.gogobasketball.want2create.Want2CreateRoomFragment;
import com.kerry.gogobasketball.want2create.Want2CreateRoomPresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainMvpController {

    private final FragmentActivity mActivity;
    private MainPresenter mMainPresenter;

    private HomePresenter mHomePresenter;
    private FriendPresenter mFriendPresenter;
    private RankPresenter mRankPresenter;
    private ProfilePresenter mProfilePresenter;

    private Want2CreateRoomPresenter mWant2CreateRoomPresenter;

    private LookingForRoomPresenter mLookingForRoomPresenter;
    private CourtsMapPresenter mCourtsMapPresenter;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            HOME, FRIEND, RANK, PROFILE, WANT2CREATEROOM, WAITING4JOIN
    })
    public @interface FragmentType {}
    static final String HOME    = "HOME";
    static final String FRIEND = "FRIEND";
    static final String RANK    = "RANK";
    static final String PROFILE = "PROFILE";
    static final String WANT2CREATEROOM = "WANT2CREATEROOM";
    static final String WAITING4JOIN = "WAITING4JOIN";


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
     *  Friends View
     */
    void findOrCreateFriendsView() {

        FriendFragment friendsFragment = findOrCreateFriendFragment();

        if (mFriendPresenter == null) {
            mFriendPresenter = new FriendPresenter(friendsFragment);
            mMainPresenter.setFriendPresenter(mFriendPresenter);
            friendsFragment.setPresenter(mMainPresenter);
        }
    }

    /**
     *  Rank View
     */
    void findOrCreateRankView() {

        RankFragment rankFragment = findOrCreateRankFragment();

        if (mRankPresenter == null) {
            mRankPresenter = new RankPresenter(rankFragment);
            mMainPresenter.setRankPresenter(mRankPresenter);
            rankFragment.setPresenter(mMainPresenter);
        }
    }

    /**
     *  Profile View
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

    /**
     * Want2CreateRoom View
     */
    void findOrCreateWant2CreateRoomView() {

        Want2CreateRoomFragment want2CreateRoomFragment = createWant2CreateRoomFragment();

        mWant2CreateRoomPresenter = new Want2CreateRoomPresenter(want2CreateRoomFragment);

        mMainPresenter.setWant2CreateRoomPresenter(mWant2CreateRoomPresenter);
        want2CreateRoomFragment.setPresenter(mMainPresenter);
    }

    /**
     * Waiting4Join View
     */
//    void findOrCreateWaiting4JoinView() {
//
//        Waiting4JoinFragment waiting4JoinFragment = createWaiting4JoinFragment();
//
//        mWant2CreateRoomPresenter = new Waiting4JoinPresenter(waiting4JoinFragment);
//
//        mMainPresenter.setWaiting4JoinPresenter(mWant2CreateRoomPresenter);
//        waiting4JoinFragment.setPresenter(mMainPresenter);
//    }

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
     * Friend Fragment
     * @return FriendFragment
     */
    @NonNull
    private FriendFragment findOrCreateFriendFragment() {

        FriendFragment friendsFragment =
                (FriendFragment) getFragmentManager().findFragmentByTag(FRIEND);
        if (friendsFragment == null) {
            // Create the fragment
            friendsFragment = FriendFragment.newInstance();
        }

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), friendsFragment, FRIEND);

        return friendsFragment;
    }

    /**
     * Rank Fragment
     * @return RankFragment
     */
    @NonNull
    private RankFragment findOrCreateRankFragment() {

        RankFragment rankFragment =
                (RankFragment) getFragmentManager().findFragmentByTag(RANK);
        if (rankFragment == null) {
            // Create the fragment
            rankFragment = RankFragment.newInstance();
        }

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), rankFragment, RANK);

        return rankFragment;
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
     * Want2CreateRoom Fragment
     * @return FaqFragment
     */
    @NonNull
    private Want2CreateRoomFragment createWant2CreateRoomFragment() {

        Want2CreateRoomFragment want2CreateRoomFragment = Want2CreateRoomFragment.newInstance();

        ActivityUtils.addFragmentByTag(
                getFragmentManager(), want2CreateRoomFragment, WANT2CREATEROOM);

        return want2CreateRoomFragment;
    }

    /**
     * Waiting4Join Fragment
     * @return FaqFragment
     */
    @NonNull
//    private Waiting4JoinFragment createWaiting4JoinFragment() {
//
//        Waiting4JoinFragment waiting4JoinFragmentFragment = Waiting4JoinFragment.newInstance();
//
//        ActivityUtils.addFragmentByTag(
//                getFragmentManager(), waiting4JoinFragmentFragment, WAITING4JOIN);
//
//        return waiting4JoinFragmentFragment;
//    }

    /* ------------------------------------------------------------------------------------------ */

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
