package com.kerry.gogobasketball.rank;

import static com.kerry.gogobasketball.MainMvpController.PLAYER;
import static com.kerry.gogobasketball.MainMvpController.REFEREE;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RankAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private RankContract.Presenter mPresenter;
    private String[] mItemTypes = new String[]{PLAYER, REFEREE};


    public RankAdapter(FragmentManager fragmentManager, RankContract.Presenter presenter) {
        super(fragmentManager);
        mFragmentManager = fragmentManager;
        mPresenter = presenter;
    }

    @Override
    public Fragment getItem(int i) {
        switch (mItemTypes[i]) {
            case PLAYER:
                return mPresenter.findPlayerRankView();

            case REFEREE:
            default:
                return mPresenter.findRefereeRankView();

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

}
