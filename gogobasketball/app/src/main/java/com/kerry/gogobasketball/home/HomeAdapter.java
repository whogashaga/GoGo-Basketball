package com.kerry.gogobasketball.home;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.kerry.gogobasketball.MainMvpController.MAP;
import static com.kerry.gogobasketball.MainMvpController.ROOMS;

public class HomeAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private HomeContract.Presenter mPresenter;
    private String[] mItemTypes = new String[]{ROOMS, MAP};

    public HomeAdapter(FragmentManager fragmentManager, HomeContract.Presenter presenter) {
        super(fragmentManager);
        mFragmentManager = fragmentManager;
        mPresenter = presenter;
    }

    @Override
    public Fragment getItem(int i) {
        switch (mItemTypes[i]) {
            case ROOMS:
                return mPresenter.findRoomsView();

            case MAP:
            default:
                return mPresenter.findMapView();

        }
    }

    @Override
    public int getCount() {
        return mItemTypes.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mItemTypes[position];
    }

    public String getItemType(int position) {
        return mItemTypes[position];
    }
}
