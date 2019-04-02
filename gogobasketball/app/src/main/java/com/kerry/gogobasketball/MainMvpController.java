package com.kerry.gogobasketball;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.kerry.gogobasketball.home.item.LookingForRoomFragment;
import com.kerry.gogobasketball.home.item.LookingForRoomPresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainMvpController {

    private final FragmentActivity mActivity;
    private MainPresenter mMainPresenter;



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


    private LookingForRoomPresenter mLookingForRoomPresenter;

    /**
     * LookingForRooms View
     * @return CatalogItemFragment: Women Fragment
     */
    LookingForRoomFragment findOrCreateLookingForRoomView() {

        LookingForRoomFragment fragment = findOrCreateRoomsItemFragment(ROOMS);

        mLookingForRoomPresenter = new LookingForRoomPresenter(fragment);
        fragment.setPresenter(mMainPresenter);
        fragment.setItemType(ROOMS);
        mMainPresenter.setLookingForRoomPresenter(mLookingForRoomPresenter);

        return fragment;
    }

    /**
     * HomeFragment: Room, Map
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
