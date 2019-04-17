package com.kerry.gogobasketball.home;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kerry.gogobasketball.MainMvpController;
import com.kerry.gogobasketball.R;
import com.kerry.gogobasketball.home.HomeContract;

public class HomeFragment extends Fragment implements HomeContract.View {

    private HomeContract.Presenter mPresenter;
    private HomeAdapter mHomeAdapter;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public HomeFragment(){}

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeAdapter = new HomeAdapter(getChildFragmentManager(), mPresenter);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.showToolbarAndBottomNavigation();
//        mTabLayout.setupWithViewPager(mViewPager);
//        mViewPager.setAdapter(mHomeAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mTabLayout = root.findViewById(R.id.tabs_home);

        mViewPager = root.findViewById(R.id.viewpager_home);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.showToolbarAndBottomNavigation();
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mHomeAdapter);
//        mViewPager.addOnPageChangeListener(
//                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }

}
